package main008.BED.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.dto.ContentsDto;
import org.hibernate.validator.constraints.Range;

import java.util.List;

public class PaymentDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @Range(max= 50000, message = "50,000원 이하로 입력해주세요.")
        private Integer price;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private ContentsDto.Response contents;
        private List<PaymentDetailDto.Response> paymentDetails;
    }

}
