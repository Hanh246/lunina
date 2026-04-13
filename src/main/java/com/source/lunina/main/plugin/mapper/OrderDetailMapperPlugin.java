package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.OrderDetailDTO;
import com.source.lunina.main.entity.OrderDetail;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailMapperPlugin extends AbstractMapperPlugin<OrderDetail, OrderDetailDTO, Long> {

    public OrderDetailMapperPlugin(ModelMapper mapper) {
        super(OrderDetail.class, OrderDetailDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
