package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.OrderDTO;
import com.source.lunina.main.entity.Orders;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapperPlugin extends AbstractMapperPlugin<Orders, OrderDTO, Long> {

    public OrderMapperPlugin(ModelMapper mapper) {
        super(Orders.class, OrderDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

    @Override
    public OrderDTO toDto(Orders model) {
        if (model == null) return null;

        // Gọi hàm toDto của lớp cha để map các trường cơ bản
        OrderDTO dto = super.toDto(model);

        // Gán userId thủ công từ quan hệ ManyToOne
        if (model.getUser() != null) {
            dto.setUserId(model.getUser().getId());
        }

        return dto;
    }
}
