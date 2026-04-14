package com.source.lunina.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Long orderId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long productId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProductDTO product;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long variantId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProductVariantDTO variant;

    private Integer quantity;
    private Double unitPrice;
}
