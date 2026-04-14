package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.OrderDetailDTO;
import com.source.lunina.main.entity.OrderDetail;
import com.source.lunina.main.entity.Orders;
import com.source.lunina.main.entity.ProductVariants;
import com.source.lunina.main.entity.Products;
import com.source.lunina.main.repository.IOrderRepository;
import com.source.lunina.main.repository.IProductRepository;
import com.source.lunina.main.repository.IProductVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailMapperPlugin extends AbstractMapperPlugin<OrderDetail, OrderDetailDTO, Long> {

    private final IProductRepository productRepository;
    private final IProductVariantRepository productVariantRepository;
    private final IOrderRepository orderRepository;
    public OrderDetailMapperPlugin(ModelMapper mapper,
                                   IProductRepository productRepository,
                                   IProductVariantRepository productVariantRepository,
                                   IOrderRepository orderRepository) {
        super(OrderDetail.class, OrderDetailDTO.class, Long.class, mapper);
        this.productRepository = productRepository;
        this.productVariantRepository = productVariantRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

    @Override
    public OrderDetail toModel(OrderDetailDTO dto) {
        OrderDetail model = super.toModel(dto);

        // Fix lỗi Transient ở đây
        if (dto.getProductId() != null) {
            Products pro = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            model.setProduct(pro);
        }

        if (dto.getVariantId() != null) {
            ProductVariants pro = productVariantRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            model.setVariant(pro);
        }

        if (dto.getOrderId() != null) {
            Orders pro = orderRepository.findById(dto.getOrderId())
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            model.setOrder(pro);
        }

        return model;
    }

    @Override
    public OrderDetailDTO toDto(OrderDetail model) {
        if (model == null) return null;

        // Gọi hàm toDto của lớp cha để map các trường cơ bản
        OrderDetailDTO dto = super.toDto(model);

        // Gán userId thủ công từ quan hệ ManyToOne
        if (model.getOrder() != null) {
            dto.setOrderId(model.getOrder().getId());
        }

        return dto;
    }
}
