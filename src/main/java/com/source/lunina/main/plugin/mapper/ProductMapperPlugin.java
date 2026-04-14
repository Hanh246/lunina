package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.ProductDTO;
import com.source.lunina.main.entity.Categories;
import com.source.lunina.main.entity.Products;
import com.source.lunina.main.repository.ICategoriesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapperPlugin extends AbstractMapperPlugin<Products, ProductDTO, Long> {

    private final ICategoriesRepository categoryRepository;
    public ProductMapperPlugin(ModelMapper mapper,
                               ICategoriesRepository categoryRepository) {
        super(Products.class, ProductDTO.class, Long.class, mapper);
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

    @Override
    public Products toModel(ProductDTO dto) {
        Products model = super.toModel(dto);

        // Fix lỗi Transient ở đây
        if (dto.getCategoryId() != null) {
            Categories cat = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
            model.setCategory(cat);
        }

        return model;
    }
}
