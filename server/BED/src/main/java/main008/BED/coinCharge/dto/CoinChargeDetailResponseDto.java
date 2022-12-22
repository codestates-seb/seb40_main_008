package main008.BED.coinCharge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CoinChargeDetailResponseDto {

    private int chargeAmount;
    private Boolean paySuccess;
    private ZonedDateTime approvedAt;
    private int cancelAmount;
    private Boolean refund;
    private String canceled_at;
}
