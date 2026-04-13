package com.source.lunina.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.source.lunina.main.constants.RankEnum;
import com.source.lunina.main.entity.Categories;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;
    private String description;
    private Double basePrice;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
}
