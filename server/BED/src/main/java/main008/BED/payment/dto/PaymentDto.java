package main008.BED.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

public class PaymentDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @Range(max= 50000, message = "50,000원 이하로 입력해주세요.")
        private Integer price;
    }

}
