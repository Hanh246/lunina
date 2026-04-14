package com.source.lunina.main.plugin.crud;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.plugin.AbstractCrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import com.source.lunina.main.dto.OrderDetailDTO;
import com.source.lunina.main.dto.ProductVariantDTO;
import com.source.lunina.main.entity.OrderDetail;
import com.source.lunina.main.repository.IOrderDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OrderDetailCrudPlugin extends AbstractCrudPlugin<OrderDetail, OrderDetailDTO, Long, PaginationSearchDTO> {

    private final ProductVariantCrudPlugin productVariantCrudPlugin;
    private final IOrderDetailRepository repository;
    protected OrderDetailCrudPlugin(IOrderDetailRepository repository,
                                    PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry,
                                    ProductVariantCrudPlugin productVariantCrudPlugin) {
        super(repository, pluginRegistry, OrderDetail.class);
        this.repository = repository;
        this.productVariantCrudPlugin = productVariantCrudPlugin;
    }

    public List<OrderDetailDTO> findByOrderID(Long orderId) {
        return repository.findByOrderID(orderId).stream().map(plugin::toDto).toList();
    }

    @Override
    @Transactional
    public OrderDetailDTO create(OrderDetailDTO dto) throws RuntimeException {
        ProductVariantDTO variantDTO = productVariantCrudPlugin.read(dto.getVariant().getId())
                .orElseThrow(() -> new RuntimeException("Biến thể sản phẩm không tồn tại!"));

        if (variantDTO.getStock() < dto.getQuantity()) {
            throw new RuntimeException("Số lượng sản phẩm trong kho không đủ (Hiện còn: " + variantDTO.getStock() + ")");
        }
        variantDTO.setStock(variantDTO.getStock() - dto.getQuantity());
        productVariantCrudPlugin.update(dto.getVariant().getId(), variantDTO);

       return super.create(dto);
    }

    @Override
    @Transactional
    public OrderDetailDTO update(Long id, OrderDetailDTO dto) {
        // 1. Lấy OrderDetail cũ từ DB
        OrderDetail existingDetail = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy chi tiết đơn hàng id: " + id));

        Long oldVariantId = existingDetail.getVariant().getId();
        Long newVariantId = dto.getVariant().getId();
        int oldQty = existingDetail.getQuantity();
        int newQty = dto.getQuantity();

        // TRƯỜNG HỢP 1: Cùng một loại sản phẩm nhưng thay đổi số lượng
        if (oldVariantId.equals(newVariantId)) {
            int diff = newQty - oldQty;
            if (diff != 0) {
                ProductVariantDTO variant = productVariantCrudPlugin.read(newVariantId)
                        .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

                if (diff > variant.getStock()) {
                    throw new RuntimeException("Kho không đủ hàng (Hiện còn: " + variant.getStock() + ")");
                }

                variant.setStock(variant.getStock() - diff);
                productVariantCrudPlugin.update(newVariantId, variant);
            }
        }
        // TRƯỜNG HỢP 2: Đổi hẳn sang một sản phẩm (Variant) khác
        else {
            // Trả lại kho cho sản phẩm cũ
            ProductVariantDTO oldVariant = productVariantCrudPlugin.read(oldVariantId)
                    .orElseThrow(() -> new RuntimeException("Sản phẩm cũ không tồn tại"));
            oldVariant.setStock(oldVariant.getStock() + oldQty);
            productVariantCrudPlugin.update(oldVariantId, oldVariant);

            // Trừ kho ở sản phẩm mới
            ProductVariantDTO newVariant = productVariantCrudPlugin.read(newVariantId)
                    .orElseThrow(() -> new RuntimeException("Sản phẩm mới không tồn tại"));
            if (newVariant.getStock() < newQty) {
                throw new RuntimeException("Sản phẩm mới không đủ tồn kho");
            }
            newVariant.setStock(newVariant.getStock() - newQty);
            productVariantCrudPlugin.update(newVariantId, newVariant);
        }

        // 2. Cập nhật thông tin OrderDetail
        OrderDetail updatedModel = plugin.updateModel(existingDetail, dto);
        OrderDetail savedModel = repository.save(updatedModel);

        return plugin.toDto(savedModel);
    }
}
