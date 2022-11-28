package main008.BED.coinCharge.mapper;

import main008.BED.coinCharge.dto.CoinChargeDetailCancelResponseDto;
import main008.BED.coinCharge.dto.CoinChargeDetailDto;
import main008.BED.coinCharge.dto.CoinChargeDetailResponseDto;
import main008.BED.coinCharge.entity.CoinChargeDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoinChargeDetailMapper {

    List<CoinChargeDetailResponseDto> entityToResponses(List<CoinChargeDetail> coinChargeDetails);
    default CoinChargeDetailCancelResponseDto cancelToResponse(CoinChargeDetailDto.KakaoCancelResponse kakaoCancelResponse) {

        return new CoinChargeDetailCancelResponseDto(
                kakaoCancelResponse.getAmount().getTotal(),
                kakaoCancelResponse.getCanceled_at()
        );
    }
}
