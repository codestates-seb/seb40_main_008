package main008.BED.coinCharge.dto;

import lombok.*;
import main008.BED.coinCharge.entity.CoinCharge;

import java.util.List;

public class CoinChargeDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private CoinCharge.ChargeAmount chargeAmount;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private int totalCoin;
        private List<CoinChargeDetailResponseDto> coinChargeDetails;
    }
}
