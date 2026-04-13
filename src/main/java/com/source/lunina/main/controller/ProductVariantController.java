package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.dto.response.BaseResponse;
import com.source.lunina.main.dto.ProductVariantDTO;
import com.source.lunina.main.entity.ProductVariants;
import com.source.lunina.main.plugin.crud.ProductVariantCrudPlugin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-variant")
public class ProductVariantController extends BaseController<ProductVariants, ProductVariantDTO, Long, PaginationSearchDTO> {

    private final ProductVariantCrudPlugin crudPlugin;
    public ProductVariantController(ProductVariantCrudPlugin crudPlugin) {
        super(crudPlugin, ProductVariants.class);
        this.crudPlugin = crudPlugin;
    }

    @GetMapping("/get-by-productid")
    public ResponseEntity<BaseResponse<List<ProductVariantDTO>>> getProductVariantsByProductId(@RequestParam Long productId) {
        List<ProductVariantDTO> data = crudPlugin.findAllByProductId(productId);

        if (data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.<List<ProductVariantDTO>>builder()
                            .success(false)
                            .message("Không tìm thấy sản phẩm")
                            .build());
        }

        return ResponseEntity.ok(BaseResponse.<List<ProductVariantDTO>>builder()
                .success(true)
                .data(data)
                .build());
    }
}
