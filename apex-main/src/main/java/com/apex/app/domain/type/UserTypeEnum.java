package com.apex.app.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    DISABLED(0, "disabled"),
    MEMBER(1, "member"),
    MANAGER(2, "manager"),
    OWNER(3, "owner");

    private final Integer value;

    private final String desc;

}
