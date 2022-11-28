package main008.BED.coinCharge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class CoinChargeDetailCancelResponseDto {

    private int cancelAmount;
    private String canceled_at;
}
