package com.source.lunina.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVariantDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String sizeName;  // Ví dụ: "S", "M", "L"
    private String colorName; // Ví dụ: "Đỏ", "Xanh", "Tím"

    private Double price;     // Giá bán riêng cho sự kết hợp này (nếu có)
    private Integer stock;    // Số lượng tồn kho cho đúng cặp Size-Màu này

    // Ảnh riêng cho màu đó (Shopee thường thay đổi ảnh khi bạn chọn màu)
    private String variantImageUrl;
}
