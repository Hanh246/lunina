package com.source.lunina.main.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RankEnum {
    NORMAL(0, "Thường"),
    COPPER(1, "Đồng"),
    SILVER(2, "Bạc"),
    GOLD(4, "Vàng"),
    DIAMOND(3, "Kim cương");

    private final int value;
    private final String label;

    RankEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @JsonValue
    public int toValue() {
        return value;
    }

    public static RankEnum fromValue(int value) {
        for (var s : RankEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        throw new IllegalArgumentException("Không có giá trị: " + value);
    }
}
