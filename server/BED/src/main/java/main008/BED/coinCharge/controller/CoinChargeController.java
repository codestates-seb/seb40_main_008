package main008.BED.coinCharge.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.dto.CoinChargeDetailDto;
import main008.BED.coinCharge.dto.CoinChargeDetailResponseDto;
import main008.BED.coinCharge.dto.CoinChargeDto;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.coinCharge.mapper.CoinChargeDetailMapper;
import main008.BED.coinCharge.mapper.CoinChargeMapper;
import main008.BED.coinCharge.service.CoinChargeService;
import main008.BED.dto.SingleResponseDto;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.service.UserPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/coincharge")
@RequiredArgsConstructor
@Validated
public class CoinChargeController {

    private final CoinChargeService coinChargeService;
    private final CoinChargeMapper coinChargeMapper;
    private final CoinChargeDetailMapper coinChargeDetailMapper;
    private final UserPageService userPageService;

    /**
     * 코인 충전
     * @param post
     * @param usersId
     * @return
     */
    @PostMapping("/{users-id}/ready")
    public ResponseEntity readyToCoinCharge(@RequestBody CoinChargeDto.Post post,
                                            @PathVariable("users-id") @Positive Long usersId) {

        CoinChargeDetailDto.KakaoReadyResponse readyToPay =
                coinChargeService.kakaoPayReady(usersId, coinChargeMapper.postToEntity(post));

        return new ResponseEntity<>(new SingleResponseDto<>(readyToPay), HttpStatus.OK);
    }

    /**
     * 결제성공
     */
    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken,
                                          @RequestParam("tid") String tid) {

        CoinChargeDetailDto.KakaoApproveResponse approveResponse = coinChargeService.kakaoApproveResponse(pgToken, tid);

        return new ResponseEntity<>(new SingleResponseDto<>(approveResponse), HttpStatus.OK);
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public void cancel() {
        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {
        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
    }

    /**
    * 충전 내역 조회
    */
    @GetMapping("/auth/{users-id}/coincharge")
    public ResponseEntity getCoinCharge(@PathVariable("users-id") @Positive Long usersId) {


        UserPage userPage = userPageService.findUserPage(usersId);

        List<CoinChargeDetailResponseDto> coinChargeDetailResponseDto =  coinChargeDetailMapper.entityToResponses(
                        coinChargeService.getCoinChargeDetail(usersId));

        return new ResponseEntity<>(coinChargeMapper.entityToResponse(userPage, coinChargeDetailResponseDto), HttpStatus.OK);
    }
}
