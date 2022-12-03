package main008.BED.coinCharge.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.dto.CoinChargeDetailDto;
import main008.BED.coinCharge.dto.CoinChargeDetailResponseDto;
import main008.BED.coinCharge.dto.CoinChargeDto;
import main008.BED.coinCharge.mapper.CoinChargeDetailMapper;
import main008.BED.coinCharge.mapper.CoinChargeMapper;
import main008.BED.coinCharge.service.CoinChargeService;
import main008.BED.converter.StringToCoinEnum;
import main008.BED.dto.SingleResponseDto;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.service.UserPageService;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
//@SessionAttributes({"tid"}) // 세션에 저장된 값 사용 시 쓰는 어노테이션, session에 없을 시 model까지 훑어서 찾음
public class CoinChargeController {

    private final CoinChargeDetailMapper coinChargeDetailMapper;
    private final CoinChargeService coinChargeService;
    private final CoinChargeMapper coinChargeMapper;
    private final UserPageService userPageService;
    private final UsersService usersService;
    private final StringToCoinEnum stringToCoinEnum;

    /**
     * 코인 충전
     * @param
     * @return
     */
    @PostMapping("/auth/coincharge/ready")
    public ResponseEntity readyToCoinCharge(@RequestBody String chargeAmount,
                                            Principal principal) {

        CoinChargeDto.Post post = new CoinChargeDto.Post(stringToCoinEnum.convert(chargeAmount));

        CoinChargeDetailDto.KakaoReadyResponse readyToPay =
                coinChargeService.kakaoPayReady(principal, coinChargeMapper.postToEntity(post));

        return new ResponseEntity<>(new SingleResponseDto<>(readyToPay), HttpStatus.OK);
    }

    /**
     * 결제성공
     */
    @GetMapping("/coincharge/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

        CoinChargeDetailDto.KakaoApproveResponse approveResponse = coinChargeService.ApproveResponse(pgToken);

        return new ResponseEntity<>(new SingleResponseDto<>(approveResponse), HttpStatus.OK);
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/coincharge/cancel")
    public void cancel() {

        coinChargeService.deleteDetail();

        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
    }

    /**
     * 결제 실패
     */
    @GetMapping("/coincharge/fail")
    public void fail() {

        coinChargeService.deleteDetail();

        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
    }

    /**
    * 충전 내역 조회
    */
    @GetMapping("/auth/coincharge")
    public ResponseEntity getCoinCharge(Principal principal) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        UserPage userPage = userPageService.findUserPage(users.getUsersId());

        List<CoinChargeDetailResponseDto> coinChargeDetailResponseDto =
                coinChargeDetailMapper.entityToResponses(
                        coinChargeService.getCoinChargeDetail(users.getUsersId()));

        return new ResponseEntity<>(
                coinChargeMapper.entityToResponse(userPage, coinChargeDetailResponseDto)
                , HttpStatus.OK);
    }

    /**
     * 코인 환불
     * @param coinChargeDetailId
     * @return
     */
    @PostMapping("/auth/coincharge/{coin-charge-detail-id}")
    public ResponseEntity refundCoinCharge(@PathVariable("coin-charge-detail-id") @Positive Long coinChargeDetailId,
                                           Principal principal) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        CoinChargeDetailDto.KakaoCancelResponse kakaoCancelResponse =
                coinChargeService.kakaoCancel(users.getUsersId(), coinChargeDetailId);

        return new ResponseEntity<>(coinChargeDetailMapper.cancelToResponse(kakaoCancelResponse), HttpStatus.OK);
    }
}
