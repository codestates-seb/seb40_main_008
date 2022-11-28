package main008.BED.contents.entity;

/**
 * enum response Dto
 */
public class EnumValue {
    private String key;
    private String value;

    public EnumValue(EnumModel enumModel) {
        key = enumModel.getKey();
        value = enumModel.getValue();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
