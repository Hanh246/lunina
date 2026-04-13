package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.main.dto.ProductDTO;
import com.source.lunina.main.entity.Products;
import com.source.lunina.main.plugin.crud.ProductCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController<Products, ProductDTO, Long, PaginationSearchDTO> {

    public ProductController(ProductCrudPlugin crudPlugin) {
        super(crudPlugin, Products.class);
    }
}
