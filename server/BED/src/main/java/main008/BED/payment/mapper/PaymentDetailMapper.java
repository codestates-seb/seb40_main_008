package main008.BED.payment.mapper;

import main008.BED.payment.dto.PaymentDetailDto;
import main008.BED.payment.entity.PaymentDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentDetailMapper {

    PaymentDetail payPostToEntity(PaymentDetailDto.PayPost post);

    PaymentDetailDto.Response entityToResponse(PaymentDetail paymentDetail);
}
