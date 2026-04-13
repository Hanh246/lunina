package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.main.dto.OrderDetailDTO;
import com.source.lunina.main.entity.OrderDetail;
import com.source.lunina.main.plugin.crud.OrderDetailCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-detail")
public class OrderDetailController extends BaseController<OrderDetail, OrderDetailDTO, Long, PaginationSearchDTO> {

    public OrderDetailController(OrderDetailCrudPlugin crudPlugin) {
        super(crudPlugin, OrderDetail.class);
    }
}
