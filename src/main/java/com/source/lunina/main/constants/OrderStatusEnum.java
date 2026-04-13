package com.source.lunina.main.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    WAITING(0, "Đang đặt"),
    COMPLETED(1, "Đã nhận"),
    CANCEL(2, "Đã hủy");

    private final int value;
    private final String label;

    OrderStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @JsonValue
    public int toValue() {
        return value;
    }

    public static OrderStatusEnum fromValue(int value) {
        for (var s : OrderStatusEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        throw new IllegalArgumentException("Không có giá trị: " + value);
    }
}
