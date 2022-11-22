package main008.BED.coinCharge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CoinChargeDetailResponseDto {

    private ZonedDateTime boughtAt;
    private int chargeAmount;
}
