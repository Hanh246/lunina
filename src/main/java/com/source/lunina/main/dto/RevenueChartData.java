package com.source.lunina.main.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevenueChartData {
    private String month; // T11, T12, T1...
    private Double revenue;
    private Long orderCount;
}
