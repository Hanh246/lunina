package com.source.lunina.main.entity;

import com.source.lunina.common.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Categories")
@EqualsAndHashCode(callSuper = true)
public class Categories extends BaseEntity {
    private String name;
    private String description;

    // Một Category có thể chứa danh sách nhiều Product
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Products> products;
}
