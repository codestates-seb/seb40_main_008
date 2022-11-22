package main008.BED.coinCharge.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.dto.CoinChargeDto;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.coinCharge.mapper.CoinChargeDetailMapper;
import main008.BED.coinCharge.mapper.CoinChargeMapper;
import main008.BED.coinCharge.service.CoinChargeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class CoinChargeController {

    private final CoinChargeService coinChargeService;
    private final CoinChargeMapper coinChargeMapper;
    private final CoinChargeDetailMapper coinChargeDetailMapper;

    /*
    코인 충전
    */
    @PostMapping("/auth/{users-id}/coincharge")
    public ResponseEntity coinCharge(@RequestParam("chargeAmount") CoinCharge.ChargeAmount chargeAmount,
                                     @PathVariable("users-id") @Positive Long usersId) {

        CoinChargeDto.Post post = new CoinChargeDto.Post(chargeAmount);
        CoinCharge coinCharge = coinChargeService.charging(usersId, coinChargeMapper.postToEntity(post));

        return new ResponseEntity<>("Success to charge your coin.", HttpStatus.OK);
    }

    /*
    충전 내역 조회
    */
    @GetMapping("/auth/{users-id}/coincharge")
    public ResponseEntity getCoinCharge(@PathVariable("users-id") @Positive Long usersId) {

        return new ResponseEntity(
                coinChargeDetailMapper.entityToResponses(
                        coinChargeService.getCoinChargeDetail(usersId)), HttpStatus.OK);
    }
}
