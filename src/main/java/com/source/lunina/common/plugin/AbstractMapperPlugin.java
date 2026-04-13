package com.source.lunina.common.plugin;

import com.source.lunina.common.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class AbstractMapperPlugin<MODEL extends BaseEntity, DTO, ID>
        implements IMapperPluginDetail<MODEL, DTO, ID> {

    private final Class<MODEL> modelClass;
    private final Class<DTO> dtoClass;
    private final Class<ID> idClass;

    protected final ModelMapper modelMapper;


    @Override
    public boolean supports(Class<?> delimiter) {
        return modelClass.equals(delimiter);
    }

    @Override
    public DTO toDto(MODEL model) {
        if (model == null)
            return null;
        return modelMapper.map(model, dtoClass);
    }

    @Override
    public MODEL toModel(DTO dto) {
        if (dto == null)
            return null;
        var model = modelMapper.map(dto, modelClass);
        performCustomUpdate(model, dto);
        return model;
    }

    @Override
    public MODEL updateModel(MODEL existingModel, DTO dto) {
        if (dto == null)
            return existingModel;

        var id = existingModel.getId();
        modelMapper.map(dto, existingModel);
        existingModel.setId(id);
        // Perform any custom update logic
        performCustomUpdate(existingModel, dto);

        return existingModel;
    }

    protected void performCustomUpdate(MODEL existingModel, DTO dto) {
        // Default implementation does nothing
        // Override in specific plugins for custom logic
    }

    protected void configureModelMapper() {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true);
    }
}
