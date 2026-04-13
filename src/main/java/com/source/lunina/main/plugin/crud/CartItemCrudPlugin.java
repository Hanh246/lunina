package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.CartItemDTO;
import com.source.lunina.main.entity.CartItems;
import com.source.lunina.main.repository.ICartItemRepository;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class CartItemCrudPlugin extends AbstractCrudPlugin<CartItems, CartItemDTO, Long, PaginationSearchDTO> {

    protected CartItemCrudPlugin(ICartItemRepository repository,
                                 PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, CartItems.class);
    }
}
