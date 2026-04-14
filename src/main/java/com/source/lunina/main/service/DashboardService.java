package com.source.lunina.main.service;

import com.source.lunina.main.constants.OrderStatusEnum;
import com.source.lunina.main.dto.DashboardStatisticsDTO;
import com.source.lunina.main.dto.RevenueChartData;
import com.source.lunina.main.dto.TopProductDTO;
import com.source.lunina.main.repository.IOrderRepository;
import com.source.lunina.main.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;

    public DashboardStatisticsDTO getDashboardStats() {
        // Card 1: Tổng doanh thu từ đơn COMPLETED
        Double totalRevenue = orderRepository.sumTotalAmountByStatus(OrderStatusEnum.COMPLETED);

        // Card 2: Tổng đơn hàng (không tính đơn đã xóa)
        Long totalOrders = orderRepository.count();

        // Card 3: Đơn hàng đang chờ xác nhận/xử lý
        Long processingOrders = orderRepository.countByStatusAndDeletedFalse(OrderStatusEnum.WAITING);

        // Card 4: Tổng khách hàng
        Long totalCustomers = userRepository.count();

        // Biểu đồ: 6 tháng gần nhất
        List<RevenueChartData> chartData = getMonthlyRevenueStats();

        // Top sản phẩm: Lấy 5 sản phẩm đầu tiên
        List<TopProductDTO> topProducts = orderRepository.findTopSellingProducts(PageRequest.of(0, 5));

        return DashboardStatisticsDTO.builder()
                .totalRevenue(totalRevenue != null ? totalRevenue : 0.0)
                .totalOrders(totalOrders)
                .processingOrders(processingOrders)
                .totalCustomers(totalCustomers)
                .revenueChart(chartData)
                .topProducts(topProducts)
                .build();
    }

    private List<RevenueChartData> getMonthlyRevenueStats() {
        // Lấy mốc thời gian 6 tháng trước từ đầu tháng
        LocalDateTime sixMonthsAgo = LocalDateTime.now()
                .minusMonths(5)
                .withDayOfMonth(1)
                .withHour(0).withMinute(0).withSecond(0);

        List<Object[]> results = orderRepository.getRevenueByMonthRaw(sixMonthsAgo);
        List<RevenueChartData> chartData = new ArrayList<>();

        // Map kết quả từ DB vào DTO
        for (Object[] row : results) {
            chartData.add(RevenueChartData.builder()
                    .month("T" + row[0].toString()) // Định dạng "T1", "T2"... như UI
                    .revenue(((Double) row[2]) / 1000000.0) // Đổi sang đơn vị Triệu đồng như UI yêu cầu
                    .orderCount((Long) row[3])
                    .build());
        }
        return chartData;
    }
}
