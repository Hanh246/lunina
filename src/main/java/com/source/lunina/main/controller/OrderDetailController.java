package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.dto.response.BaseResponse;
import com.source.lunina.main.dto.OrderDetailDTO;
import com.source.lunina.main.entity.OrderDetail;
import com.source.lunina.main.plugin.crud.OrderDetailCrudPlugin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-detail")
public class OrderDetailController extends BaseController<OrderDetail, OrderDetailDTO, Long, PaginationSearchDTO> {

    private final OrderDetailCrudPlugin crudPlugin;
    public OrderDetailController(OrderDetailCrudPlugin crudPlugin) {
        super(crudPlugin, OrderDetail.class);
        this.crudPlugin = crudPlugin;
    }

    @GetMapping("/get-by-orderid")
    public ResponseEntity<BaseResponse<List<OrderDetailDTO>>> findByOrderID(Long orderId) {
        var data = crudPlugin.findByOrderID(orderId);
        BaseResponse<List<OrderDetailDTO>> response = BaseResponse.<List<OrderDetailDTO>>builder()
                .success(true)
                .data(data)
                .message("Lấy danh sách chi tiết đơn hàng thành công")
                .build();

        return ResponseEntity.ok(response);
    }
}
