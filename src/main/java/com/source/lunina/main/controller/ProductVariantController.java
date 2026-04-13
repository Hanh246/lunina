package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.main.dto.ProductVariantDTO;
import com.source.lunina.main.entity.ProductVariants;
import com.source.lunina.main.plugin.crud.ProductVariantCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-variant")
public class ProductVariantController extends BaseController<ProductVariants, ProductVariantDTO, Long, PaginationSearchDTO> {

    public ProductVariantController(ProductVariantCrudPlugin crudPlugin) {
        super(crudPlugin, ProductVariants.class);
    }
}
