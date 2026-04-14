package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.constants.OrderStatusEnum;
import com.source.lunina.main.dto.TopProductDTO;
import com.source.lunina.main.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IOrderRepository extends AbstractCrudRepository<Orders, Long> {
    @Query("""
            SELECT o
            FROM Orders o
            WHERE o.user.id = :uid
            AND o.deleted = false
            """)
    Page<Orders> findByUserID(Pageable pageable, Long uid);

    // 1. Tính tổng doanh thu (Chỉ tính các đơn đã hoàn thành)
    @Query("SELECT SUM(o.totalAmount) FROM Orders o WHERE o.status = :status AND o.deleted = false")
    Double sumTotalAmountByStatus(OrderStatusEnum status);

    // 2. Đếm số đơn theo trạng thái (Cho card "Đang xử lý")
    Long countByStatusAndDeletedFalse(OrderStatusEnum status);

    // 3. Query lấy dữ liệu biểu đồ 6 tháng gần nhất
    // Trả về: [Tháng, Năm, Tổng doanh thu, Tổng số đơn]
    @Query("""
            SELECT MONTH(o.orderDate), YEAR(o.orderDate), SUM(o.totalAmount), COUNT(o.id)
            FROM Orders o
            WHERE o.orderDate >= :startDate AND o.deleted = false
            GROUP BY YEAR(o.orderDate), MONTH(o.orderDate)
            ORDER BY YEAR(o.orderDate) ASC, MONTH(o.orderDate) ASC
            """)
    List<Object[]> getRevenueByMonthRaw(LocalDateTime startDate);

    // 4. Lấy top 5 sản phẩm bán chạy nhất
    @Query("""
            SELECT new com.source.lunina.main.dto.TopProductDTO(
                p.name,
                SUM(od.quantity), 
                SUM(od.unitPrice * od.quantity)
            )
            FROM OrderDetail od 
            JOIN od.product p
            WHERE od.order.status = 'COMPLETED'
            GROUP BY p.id, p.name
            ORDER BY SUM(od.quantity) DESC
            """)
    List<TopProductDTO> findTopSellingProducts(Pageable pageable);
}
