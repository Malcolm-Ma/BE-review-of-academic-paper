package com.apex.app.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewStatusEnum {
    FINISHED((byte) 4, "finished"),
    REVIEWING((byte) 3, "reviewing"),
    BIDDING((byte) 2, "bidding"),
    COLLECTING((byte) 1, "collecting"),
    PREPARING((byte) 0, "preparing");

    private final byte value;

    private final String desc;
}
