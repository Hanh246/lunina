package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.ProductVariantDTO;
import com.source.lunina.main.entity.ProductVariants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductVariantMapperPlugin extends AbstractMapperPlugin<ProductVariants, ProductVariantDTO, Long> {

    public ProductVariantMapperPlugin(ModelMapper mapper) {
        super(ProductVariants.class, ProductVariantDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
