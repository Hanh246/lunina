package com.source.lunina.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.source.lunina.main.constants.RankEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private LocalDateTime orderDate;
    private String deliveryAddress;
    private Double totalAmount; // Tổng tiền sau khi đã trừ chiết khấu hạng thẻ
    private Double discountApplied;
}
