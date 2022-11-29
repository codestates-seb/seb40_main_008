package main008.BED.coinCharge.service;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.dto.CoinChargeDetailDto;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.coinCharge.entity.CoinChargeDetail;
import main008.BED.coinCharge.repository.CoinChargeDetailRepository;
import main008.BED.coinCharge.repository.CoinChargeRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.repository.UserPageRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoinChargeService {

    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
    @Value("${application.open-api.admin-key}")
    private String admin_Key; // 서버 배포 시에는 이걸로 변경

    private final CoinChargeDetailRepository coinChargeDetailRepository;
    private final CoinChargeRepository coinChargeRepository;
    private final UserPageRepository userPageRepository;
    private final UsersRepository usersRepository;
    private CoinChargeDetailDto.KakaoReadyResponse kakaoReady;


    /**
     * 카카오페이 결제 준비
     * @param usersId : 구매 유저 식별자
     * @param coinChargePost : 구매 코인 금액
     * @return : 카톡에게 결제번호 및 url 받음
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CoinChargeDetailDto.KakaoReadyResponse kakaoPayReady(Long usersId, CoinCharge coinChargePost) {

        UserPage userPage = userPageRepository.findByUsersUsersId(usersId);

        CoinCharge coinCharge = coinChargeRepository.findByUserPageUserPageId(userPage.getUserPageId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COIN_CHARGE_NOT_FOUND));

        List<CoinChargeDetail> coinChargeDetails =
                coinChargeDetailRepository.findByCoinChargeCoinChargeId(coinCharge.getCoinChargeId())
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.DETAIL_NOT_FOUND));

        CoinChargeDetail coinChargeDetail = new CoinChargeDetail();
        coinChargeDetail.setChargeAmount(coinChargePost.getChargeAmount().getAmount());
        coinChargeDetail.setItemName(String.valueOf(coinChargePost.getChargeAmount()));
        coinChargeDetail.setQuantity(1);
        coinChargeDetail.setTax(0);
        coinChargeDetail.setCoinCharge(coinCharge);
        coinChargeDetail.setPaySuccess(false);
        coinChargeDetailRepository.save(coinChargeDetail);

        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", String.valueOf(coinChargeDetail.getCoinChargeDetailId()));
        parameters.add("partner_user_id", String.valueOf(usersId));
        parameters.add("item_name", coinChargeDetail.getItemName());
        parameters.add("quantity", String.valueOf(coinChargeDetail.getQuantity()));
        parameters.add("total_amount", String.valueOf(coinChargeDetail.getChargeAmount()));
        parameters.add("vat_amount", String.valueOf(coinChargeDetail.getTax()));
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8080/coincharge/success");
        parameters.add("cancel_url", "http://localhost:8080/coincharge/cancel");
        parameters.add("fail_url", "http://localhost:8080/coincharge/fail");

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                CoinChargeDetailDto.KakaoReadyResponse.class);

        if (kakaoReady != null) {

            coinChargeDetail.setTid(kakaoReady.getTid());
            coinChargeDetailRepository.save(coinChargeDetail);
            coinChargeDetails.add(coinChargeDetail);
            coinCharge.setCoinChargeDetails(coinChargeDetails);
            coinChargeRepository.save(coinCharge);
        }

        return kakaoReady;
    }

    /**
     * 결제 완료 승인
     */
    public CoinChargeDetailDto.KakaoApproveResponse ApproveResponse(String pgToken) {

        CoinChargeDetail coinChargeDetail =
                coinChargeDetailRepository.findByTid(kakaoReady.getTid())
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WRONG_TID));

        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", String.valueOf(coinChargeDetail.getCoinChargeDetailId()));
        parameters.add("partner_user_id", String.valueOf(coinChargeDetail.getCoinCharge().getUserPage().getUsers().getUsersId()));
        parameters.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        CoinChargeDetailDto.KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                CoinChargeDetailDto.KakaoApproveResponse.class);

        if (approveResponse != null) {

            // 결제금액이 같지 않은데 DB에 저장되는 버그 해결
            int payBefore = coinChargeDetail.getChargeAmount();
            int payAfter = approveResponse.getAmount().getTotal();

            if (payBefore != payAfter) {
                deleteDetail();
                throw new BusinessLogicException(ExceptionCode.AMOUNT_DIFFERENT);
            }

            coinChargeDetail.setApprovedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
            coinChargeDetail.setAid(approveResponse.getAid());
            coinChargeDetail.setPaySuccess(true);
            coinChargeDetailRepository.save(coinChargeDetail);

            charge(coinChargeDetail);

            return approveResponse;
        } else {
            throw new BusinessLogicException(ExceptionCode.STRANGE_APPROACH);
        }
    }

    /**
     * 결제 환불
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CoinChargeDetailDto.KakaoCancelResponse kakaoCancel(Long usersId, Long coinDetailDetailId) {

        UserPage userPage = userPageRepository.findByUsersUsersId(usersId);

        CoinCharge coinCharge = coinChargeRepository.findByUserPageUserPageId(userPage.getUserPageId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COIN_CHARGE_NOT_FOUND));

        CoinChargeDetail coinChargeDetail =
                coinChargeDetailRepository.findByCoinChargeCoinChargeIdAndCoinChargeDetailId(coinCharge.getCoinChargeId(), coinDetailDetailId)
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.DETAIL_NOT_FOUND));

        // 카카오페이 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", coinChargeDetail.getTid());
        parameters.add("cancel_amount", String.valueOf(coinChargeDetail.getChargeAmount()));
        parameters.add("cancel_tax_free_amount", "0");
        parameters.add("cancel_vat_amount", String.valueOf(coinChargeDetail.getTax()));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        CoinChargeDetailDto.KakaoCancelResponse cancelResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/cancel",
                requestEntity,
                CoinChargeDetailDto.KakaoCancelResponse.class);

        if (cancelResponse != null) {

            coinChargeDetail.setRefund(true);
            coinChargeDetail.setCancelAmount(cancelResponse.getAmount().getTotal());
            coinChargeDetail.setCanceled_at(cancelResponse.getCanceled_at());
            coinChargeDetailRepository.save(coinChargeDetail);

            coinMinus(coinChargeDetail);

            return cancelResponse;
        } else {
            throw new BusinessLogicException(ExceptionCode.CANCEL_FAILED);
        }
    }

    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }

    /**
     * 결제 완료 후 코인 충전
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void charge(CoinChargeDetail coinChargeDetail) {

        CoinCharge coinCharge = coinChargeDetail.getCoinCharge();
        Users users = usersRepository.findByUsersId(coinCharge.getUserPage().getUsers().getUsersId());

        users.setTotalCoin(users.getTotalCoin() + coinChargeDetail.getChargeAmount());
        usersRepository.save(users);
    }

    /**
     * 결제 취소 후 코인 환불
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void coinMinus(CoinChargeDetail coinChargeDetail) {

        CoinCharge coinCharge = coinChargeDetail.getCoinCharge();
        Users users = usersRepository.findByUsersId(coinCharge.getUserPage().getUsers().getUsersId());

        users.setTotalCoin(users.getTotalCoin() - coinChargeDetail.getChargeAmount());
        usersRepository.save(users);
    }

    /**
     * 결제 내역 조회
     */
    @Transactional(readOnly = true)
    public List<CoinChargeDetail> getCoinChargeDetail(Long usersId) {

        UserPage userPage = userPageRepository.findByUsersUsersId(usersId);

        CoinCharge coinCharge = coinChargeRepository.findByUserPageUserPageId(userPage.getUserPageId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COIN_CHARGE_NOT_FOUND));

        return coinChargeDetailRepository.findByCoinChargeCoinChargeId(coinCharge.getCoinChargeId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.DETAIL_NOT_FOUND));
    }

    /**
     * 결제 취소/중단 시 detail 삭제
     * 결제가 취소되었음에도 DB에 저장되는 버그 해결
     */
    public void deleteDetail() {

        CoinChargeDetail coinChargeDetail = coinChargeDetailRepository.findByTid(kakaoReady.getTid()).orElseThrow();

        coinChargeDetailRepository.delete(coinChargeDetail);
    }
}
