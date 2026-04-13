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
import org.apache.catalina.User;

@Data
@NoArgsConstructor
public class CartItemDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private User user;

    private Products product;

    private ProductVariants variant;

    private Integer quantity;
}
