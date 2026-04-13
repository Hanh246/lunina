package com.source.lunina.main.controller;

import com.source.lunina.common.controller.BaseController;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.main.dto.CategoriesDTO;
import com.source.lunina.main.entity.Categories;
import com.source.lunina.main.plugin.crud.CategoriesCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoriesController extends BaseController<Categories, CategoriesDTO, Long, PaginationSearchDTO> {

    public CategoriesController(CategoriesCrudPlugin crudPlugin) {
        super(crudPlugin, Categories.class);
    }
}
