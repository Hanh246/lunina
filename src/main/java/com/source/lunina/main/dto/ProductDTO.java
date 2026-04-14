package com.source.lunina.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long categoryId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CategoriesDTO category;
}
