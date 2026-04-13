package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.CartItemDTO;
import com.source.lunina.main.entity.CartItems;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemMapperPlugin extends AbstractMapperPlugin<CartItems, CartItemDTO, Long> {

    public CartItemMapperPlugin(ModelMapper mapper) {
        super(CartItems.class, CartItemDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
