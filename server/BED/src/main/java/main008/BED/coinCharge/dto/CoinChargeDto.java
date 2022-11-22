package main008.BED.coinCharge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main008.BED.coinCharge.entity.CoinCharge;

public class CoinChargeDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        private CoinCharge.ChargeAmount chargeAmount;
    }
}
