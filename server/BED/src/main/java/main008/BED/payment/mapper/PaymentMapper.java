package main008.BED.payment.mapper;

import main008.BED.payment.dto.PaymentDto;
import main008.BED.payment.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment postToEntity(PaymentDto.Post post);

    Payment patchToEntity(PaymentDto.Patch patch);
}
