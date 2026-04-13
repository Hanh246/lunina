package com.source.lunina.main.entity;

import com.source.lunina.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "OrderDetail")
@EqualsAndHashCode(callSuper = true)
public class OrderDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariants variant;

    private Integer quantity;
    private Double unitPrice;

}
