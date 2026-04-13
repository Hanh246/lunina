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
@Table(name = "ProductVariants")
@EqualsAndHashCode(callSuper = true)
public class ProductVariants extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    private String sizeName;  // Ví dụ: "S", "M", "L"
    private String colorName; // Ví dụ: "Đỏ", "Xanh", "Tím"

    private Double price;     // Giá bán riêng cho sự kết hợp này (nếu có)
    private Integer stock;    // Số lượng tồn kho cho đúng cặp Size-Màu này

    // Ảnh riêng cho màu đó (Shopee thường thay đổi ảnh khi bạn chọn màu)
    private String variantImageUrl;
}
