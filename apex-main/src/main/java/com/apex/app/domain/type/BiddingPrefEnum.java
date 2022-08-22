package com.apex.app.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BiddingPrefEnum {
    CONFLICT((byte) 0, "conflict"),
    NO((byte) 1, "no"),
    MAYBE((byte) 2, "maybe"),
    YES((byte) 3, "yes");

    private final byte value;

    private final String desc;

    public static BiddingPrefEnum parseValue(byte value) {
        for (BiddingPrefEnum enumName : BiddingPrefEnum.values()) {
            if (enumName.getValue() == value) {
                return enumName;
            }
        }
        return null;
    }
}
