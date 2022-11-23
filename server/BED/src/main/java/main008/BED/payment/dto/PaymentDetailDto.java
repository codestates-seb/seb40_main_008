package main008.BED.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.users.dto.UsersDto;

public class PaymentDetailDto {

    @Getter
    @AllArgsConstructor
    public static class PayPost {

        private Boolean payed;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Boolean payed;
        private UsersDto.Response users;
    }
}
