package com.source.lunina.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Long userId;
    private LocalDateTime orderDate;
    private String deliveryAddress;
    private Double totalAmount; // Tổng tiền sau khi đã trừ chiết khấu hạng thẻ
    private Double discountApplied;
}
