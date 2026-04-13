package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.OrderDetailDTO;
import com.source.lunina.main.entity.OrderDetail;
import com.source.lunina.main.repository.IOrderDetailRepository;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailCrudPlugin extends AbstractCrudPlugin<OrderDetail, OrderDetailDTO, Long, PaginationSearchDTO> {

    protected OrderDetailCrudPlugin(IOrderDetailRepository repository,
                                    PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, OrderDetail.class);
    }
}
