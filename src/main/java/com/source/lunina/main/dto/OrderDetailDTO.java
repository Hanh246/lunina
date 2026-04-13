package com.source.lunina.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.source.lunina.main.constants.RankEnum;
import com.source.lunina.main.entity.ProductVariants;
import com.source.lunina.main.entity.Products;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private ProductDTO product;

    private ProductVariantDTO variant;

    private Integer quantity;
    private Double unitPrice;
}
