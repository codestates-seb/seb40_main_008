package main008.BED.coinCharge.mapper;

import main008.BED.coinCharge.dto.CoinChargeDto;
import main008.BED.coinCharge.entity.CoinCharge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoinChargeMapper {

    CoinCharge postToEntity(CoinChargeDto.Post post);
}
