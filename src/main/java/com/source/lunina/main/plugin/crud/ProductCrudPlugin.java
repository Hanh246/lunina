package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.ProductDTO;
import com.source.lunina.main.entity.Products;
import com.source.lunina.main.repository.IProductRepository;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class ProductCrudPlugin extends AbstractCrudPlugin<Products, ProductDTO, Long, PaginationSearchDTO> {

    protected ProductCrudPlugin(IProductRepository repository,
                                PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, Products.class);
    }
}
