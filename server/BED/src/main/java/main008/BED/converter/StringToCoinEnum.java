package main008.BED.converter;

import main008.BED.coinCharge.entity.CoinCharge;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCoinEnum implements Converter<String, CoinCharge.ChargeAmount> {

    @Override
    public CoinCharge.ChargeAmount convert(String source) {
        return CoinCharge.ChargeAmount.valueOf(source.toUpperCase());
    }
}
