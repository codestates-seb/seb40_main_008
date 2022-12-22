package main008.BED.coinCharge.mapper;

import main008.BED.coinCharge.dto.CoinChargeDetailCancelResponseDto;
import main008.BED.coinCharge.dto.CoinChargeDetailDto;
import main008.BED.coinCharge.dto.CoinChargeDetailResponseDto;
import main008.BED.coinCharge.entity.CoinChargeDetail;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CoinChargeDetailMapper {

    default List<CoinChargeDetailResponseDto> entityToResponses(List<CoinChargeDetail> coinChargeDetails) {

        return coinChargeDetails.stream()
                .map(coinChargeDetail -> CoinChargeDetailResponseDto.builder()
                        .approvedAt(coinChargeDetail.getApprovedAt())
                        .chargeAmount(coinChargeDetail.getChargeAmount())
                        .paySuccess(coinChargeDetail.getPaySuccess())
                        .cancelAmount(coinChargeDetail.getCancelAmount())
                        .canceled_at(coinChargeDetail.getCanceled_at())
                        .refund(coinChargeDetail.getRefund())
                        .build())
                .collect(Collectors.toList());
    }
    default CoinChargeDetailCancelResponseDto cancelToResponse(CoinChargeDetailDto.KakaoCancelResponse kakaoCancelResponse) {

        return new CoinChargeDetailCancelResponseDto(
                kakaoCancelResponse.getAmount().getTotal(),
                kakaoCancelResponse.getCanceled_at()
        );
    }
}
