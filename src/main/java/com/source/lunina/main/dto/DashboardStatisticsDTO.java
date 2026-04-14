package com.source.lunina.main.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardStatisticsDTO {
    // 4 Card phía trên
    private Double totalRevenue;      // Doanh thu (0 đ)
    private Long totalOrders;         // Tổng đơn hàng (3)
    private Long processingOrders;    // Đang xử lý (2)
    private Long totalCustomers;      // Khách hàng (3)

    // Biểu đồ doanh thu 6 tháng
    private List<RevenueChartData> revenueChart;

    // Sản phẩm bán chạy
    private List<TopProductDTO> topProducts;
}
