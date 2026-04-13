package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.main.dto.OrderDTO;
import com.source.lunina.main.entity.Orders;
import com.source.lunina.main.plugin.crud.OrderCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController<Orders, OrderDTO, Long, PaginationSearchDTO> {

    public OrderController(OrderCrudPlugin crudPlugin) {
        super(crudPlugin, Orders.class);
    }
}
