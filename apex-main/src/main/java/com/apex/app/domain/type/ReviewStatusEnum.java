package com.apex.app.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewStatusEnum {

    FINISHED((byte) 0, "finished"),
    ONGOING((byte) 1, "ongoing"),
    PREPARING((byte) 2, "preparing");

    private final byte value;

    private final String desc;
}
