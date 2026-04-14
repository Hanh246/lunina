package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.constants.OrderStatusEnum;
import com.source.lunina.main.dto.OrderDTO;
import com.source.lunina.main.dto.UserDTO;
import com.source.lunina.main.entity.Orders;
import com.source.lunina.main.repository.IOrderRepository;
import com.source.lunina.main.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderCrudPlugin extends AbstractCrudPlugin<Orders, OrderDTO, Long, PaginationSearchDTO> {

    private final UserService userService;

    protected OrderCrudPlugin(IOrderRepository repository,
                              PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry,
                              UserService userService) {
        super(repository, pluginRegistry, Orders.class);
        this.userService = userService;
    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO dto) throws RuntimeException {
        Orders model = plugin.toModel(dto);
        UserDTO userDTO = userService.getUserById(dto.getUserId());

        userDTO.setTotalSpending(userDTO.getTotalSpending() + dto.getTotalAmount());
        userDTO.setPoints(userDTO.getTotalSpending() / 10000);
        userService.updateUser(dto.getUserId(), userDTO);
        model.setUser(userService.toModel(userDTO));

        Orders savedModel = repository.save(model);
        return plugin.toDto(savedModel);
    }

    @Override
    @Transactional
    public OrderDTO update(Long id, OrderDTO dto) {
        Orders existingModel = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));

        UserDTO userDTO = userService.toDto(existingModel.getUser());
        userDTO.setTotalSpending(userDTO.getTotalSpending() + (dto.getTotalAmount()) - (existingModel.getTotalAmount()));
        userDTO.setPoints(userDTO.getTotalSpending() / 10000);
        userService.updateUser(dto.getUserId(), userDTO);

        Orders updatedModel = plugin.updateModel(existingModel, dto);
        Orders savedModel = repository.save(updatedModel);
        return plugin.toDto(savedModel);
    }
}
