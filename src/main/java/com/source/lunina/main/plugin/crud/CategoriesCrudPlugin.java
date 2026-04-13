package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.CategoriesDTO;
import com.source.lunina.main.entity.Categories;
import com.source.lunina.main.repository.ICategoriesRepository;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class CategoriesCrudPlugin extends AbstractCrudPlugin<Categories, CategoriesDTO, Long, PaginationSearchDTO> {

    protected CategoriesCrudPlugin(ICategoriesRepository repository,
                                   PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, Categories.class);
    }
}
