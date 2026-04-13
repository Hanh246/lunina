package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.main.dto.CartItemDTO;
import com.source.lunina.main.entity.CartItems;
import com.source.lunina.main.plugin.crud.CartItemCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart-item")
public class CartItemController extends BaseController<CartItems, CartItemDTO, Long, PaginationSearchDTO> {

    public CartItemController(CartItemCrudPlugin crudPlugin) {
        super(crudPlugin, CartItems.class);
    }
}
