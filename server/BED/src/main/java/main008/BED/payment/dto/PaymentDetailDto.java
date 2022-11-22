package main008.BED.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PaymentDetailDto {

    @Getter
    @AllArgsConstructor
    public static class PayPost {

        private Boolean payed;
    }
}
