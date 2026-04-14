package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationDTO;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.OrderDTO;
import com.source.lunina.main.dto.OrderFullDTO;
import com.source.lunina.main.dto.UserDTO;
import com.source.lunina.main.entity.Orders;
import com.source.lunina.main.entity.Users;
import com.source.lunina.main.repository.IOrderRepository;
import com.source.lunina.main.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderCrudPlugin extends AbstractCrudPlugin<Orders, OrderDTO, Long, PaginationSearchDTO> {

    private final UserService userService;
    private final IOrderRepository repository;
    private final OrderDetailCrudPlugin orderDetailCrudPlugin;
    protected OrderCrudPlugin(IOrderRepository repository,
                              PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry,
                              UserService userService,
                              OrderDetailCrudPlugin orderDetailCrudPlugin) {
        super(repository, pluginRegistry, Orders.class);
        this.userService = userService;
        this.repository = repository;
        this.orderDetailCrudPlugin = orderDetailCrudPlugin;
    }

    public Page<OrderDTO> findByUserID(PaginationDTO paginationDTO, Long uid) {
        Pageable pageable = paginationDTO.toPageRequest();

        return repository
                    .findByUserID(pageable, uid)
                    .map(plugin::toDto);
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
        // 1. Tìm đơn hàng cũ
        Orders existingModel = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        // 2. Luôn dùng User của đơn hàng gốc để tránh việc bị đổi userId trái phép
        Users userEntity = existingModel.getUser();
        UserDTO userDTO = userService.toDto(userEntity);

        // 3. Tính toán chênh lệch số tiền
        // Nếu đơn hàng cũ 100k, đơn mới 150k -> Cộng thêm 50k vào TotalSpending
        double amountDifference = dto.getTotalAmount() - existingModel.getTotalAmount();

        if (amountDifference != 0) {
            userDTO.setTotalSpending(userDTO.getTotalSpending() + amountDifference);
            // Tính điểm: ép kiểu về Long/Integer nếu cần
            userDTO.setPoints(userDTO.getTotalSpending() / 10000);

            userService.updateUser(userDTO.getId(), userDTO);
        }

        // 4. Cập nhật thông tin đơn hàng
        Orders updatedModel = plugin.updateModel(existingModel, dto);

        // Đảm bảo không bị thay đổi User gốc của đơn hàng
        updatedModel.setUser(userEntity);

        Orders savedModel = repository.save(updatedModel);
        return plugin.toDto(savedModel);
    }

    public OrderFullDTO createOrderFull(OrderFullDTO dto) {
        this.create(dto.getOrder());
        dto.getOrderDetails().forEach(orderDetailCrudPlugin::create);
        return dto;
    }
}
