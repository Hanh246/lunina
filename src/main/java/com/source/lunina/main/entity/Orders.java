package com.source.lunina.main.entity;

import com.source.lunina.common.entity.BaseEntity;
import com.source.lunina.main.constants.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Orders")
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private LocalDateTime orderDate;
    private String deliveryAddress;
    private Double totalAmount; // Tổng tiền sau khi đã trừ chiết khấu hạng thẻ
    private Double discountApplied; // Số tiền được giảm giá

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status; // DANG_DAT, DA_NHAN, DA_HUY

}
