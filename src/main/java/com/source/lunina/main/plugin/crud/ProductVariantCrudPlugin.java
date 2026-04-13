package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.ProductVariantDTO;
import com.source.lunina.main.entity.ProductVariants;
import com.source.lunina.main.repository.IProductVariantRepository;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class ProductVariantCrudPlugin extends AbstractCrudPlugin<ProductVariants, ProductVariantDTO, Long, PaginationSearchDTO> {

    protected ProductVariantCrudPlugin(IProductVariantRepository repository,
                                       PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, ProductVariants.class);
    }
}
