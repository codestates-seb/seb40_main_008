package main008.BED.converter;

import main008.BED.contents.entity.Contents;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryEnum implements Converter<String, Contents.Categories> {

    @Override
    public Contents.Categories convert(String source) {
        return Contents.Categories.valueOf(source.toUpperCase());
    }
}
