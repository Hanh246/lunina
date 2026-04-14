package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationDTO;
import com.source.lunina.common.dto.pagination.PaginationMetadata;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.dto.response.BaseResponse;
import com.source.lunina.common.dto.response.PaginationResponse;
import com.source.lunina.main.dto.OrderDTO;
import com.source.lunina.main.dto.OrderFullDTO;
import com.source.lunina.main.entity.Orders;
import com.source.lunina.main.plugin.crud.OrderCrudPlugin;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create-order")
    public ResponseEntity<BaseResponse<OrderFullDTO>> createOrder(@RequestBody OrderFullDTO orderDTO) {
        var data = crudPlugin.createOrderFull(orderDTO);
        BaseResponse<OrderFullDTO> response = BaseResponse.<OrderFullDTO>builder()
                .success(true)
                .data(data)
                .message("Tạo đơn hàng thành công")
                .build();

        return ResponseEntity.ok(response);
    }
}
