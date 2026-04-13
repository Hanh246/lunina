package com.source.lunina.main.entity;

import com.source.lunina.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Products")
@EqualsAndHashCode(callSuper = true)
public class Products extends BaseEntity {
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double basePrice; // Giá cơ bản
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    // Một sản phẩm có nhiều biến thể (nhiều màu, nhiều size)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVariants> variants;
}
