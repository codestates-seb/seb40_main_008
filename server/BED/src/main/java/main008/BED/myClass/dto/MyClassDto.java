package main008.BED.myClass.dto;

import lombok.*;
import main008.BED.payment.dto.PaymentDto;
import main008.BED.wish.dto.WishDto;

import java.util.List;

public class MyClassDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TakingClassResponse {

        List<PaymentDto.Response> payments;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class WishClassResponse {

        List<WishDto.Response> wishes;
    }
}
