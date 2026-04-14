package com.source.lunina.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderFullDTO {
    private OrderDTO order;
    private List<OrderDetailDTO> orderDetails;
}
