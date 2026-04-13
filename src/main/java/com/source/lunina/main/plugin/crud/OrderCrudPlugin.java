package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.OrderDTO;
import com.source.lunina.main.entity.Orders;
import com.source.lunina.main.repository.IOrderRepository;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class OrderCrudPlugin extends AbstractCrudPlugin<Orders, OrderDTO, Long, PaginationSearchDTO> {

    protected OrderCrudPlugin(IOrderRepository repository,
                              PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, Orders.class);
    }
}
