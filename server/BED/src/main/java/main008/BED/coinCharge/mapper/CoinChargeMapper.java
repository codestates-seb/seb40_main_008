package main008.BED.coinCharge.mapper;

import main008.BED.coinCharge.dto.CoinChargeDetailResponseDto;
import main008.BED.coinCharge.dto.CoinChargeDto;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.userPage.entity.UserPage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoinChargeMapper {

    CoinCharge postToEntity(CoinChargeDto.Post post);

    default CoinChargeDto.Response entityToResponse(UserPage userPage, List<CoinChargeDetailResponseDto> coinChargeDetailResponseDtos) {

        return new CoinChargeDto.Response(
                userPage.getUsers().getTotalCoin(),
                coinChargeDetailResponseDtos
        );
    }
}
