package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.ProductVariantDTO;
import com.source.lunina.main.entity.ProductVariants;
import com.source.lunina.main.repository.IProductVariantRepository;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductVariantCrudPlugin extends AbstractCrudPlugin<ProductVariants, ProductVariantDTO, Long, PaginationSearchDTO> {

    private final IProductVariantRepository repository;
    protected ProductVariantCrudPlugin(IProductVariantRepository repository,
                                       PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, ProductVariants.class);
        this.repository = repository;
    }

    public List<ProductVariantDTO> findAllByProductId(Long productId) {
        return repository.findByProductID(productId).stream().map(plugin::toDto).toList();
    }
}
