package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.CategoriesDTO;
import com.source.lunina.main.entity.Categories;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriesMapperPlugin extends AbstractMapperPlugin<Categories, CategoriesDTO, Long> {

    public CategoriesMapperPlugin(ModelMapper mapper) {
        super(Categories.class, CategoriesDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
