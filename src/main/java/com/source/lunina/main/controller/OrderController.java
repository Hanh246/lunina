package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationDTO;
import com.source.lunina.common.dto.pagination.PaginationMetadata;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.dto.response.PaginationResponse;
import com.source.lunina.main.dto.OrderDTO;
import com.source.lunina.main.entity.Orders;
import com.source.lunina.main.plugin.crud.OrderCrudPlugin;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController<Orders, OrderDTO, Long, PaginationSearchDTO> {

    private final OrderCrudPlugin crudPlugin;
    public OrderController(OrderCrudPlugin crudPlugin) {
        super(crudPlugin, Orders.class);
        this.crudPlugin = crudPlugin;
    }

    @GetMapping("/get-by-uid")
    public ResponseEntity<PaginationResponse<List<OrderDTO>>> findAll(@Valid @ParameterObject PaginationDTO paginationDTO, @ParameterObject Long uid) {
        var data = crudPlugin.findByUserID(paginationDTO, uid);
        return ResponseEntity
                .ok(PaginationResponse.<List<OrderDTO>>builder()
                        .metadata(
                                new PaginationMetadata(
                                        paginationDTO.getPage(),
                                        paginationDTO.getSize(),
                                        data.getTotalElements(),
                                        data.getTotalPages()
                                )
                        )
                        .success(true)
                        .data(data.toList())
                        .build());
    }
}
