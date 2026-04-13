package com.source.lunina.main.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN(1, "Quản trị viên"),
    CUSTOMER(2, "Khác hàng"),;

    private final int value;
    private final String label;

    RoleEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @JsonValue
    public int toValue() {
        return value;
    }

    public static RoleEnum fromValue(int value) {
        for (var s : RoleEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        throw new IllegalArgumentException("Không có giá trị: " + value);
    }
}
