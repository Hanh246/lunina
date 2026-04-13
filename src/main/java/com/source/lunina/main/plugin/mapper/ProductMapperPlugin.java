package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.ProductDTO;
import com.source.lunina.main.entity.Products;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapperPlugin extends AbstractMapperPlugin<Products, ProductDTO, Long> {

    public ProductMapperPlugin(ModelMapper mapper) {
        super(Products.class, ProductDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
