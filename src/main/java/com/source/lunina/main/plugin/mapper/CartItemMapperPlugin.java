package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.CartItemDTO;
import com.source.lunina.main.entity.CartItems;
import com.source.lunina.main.entity.ProductVariants;
import com.source.lunina.main.entity.Products;
import com.source.lunina.main.repository.IProductRepository;
import com.source.lunina.main.repository.IProductVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemMapperPlugin extends AbstractMapperPlugin<CartItems, CartItemDTO, Long> {

    private final IProductRepository productRepository;
    private final IProductVariantRepository productVariantRepository;
    public CartItemMapperPlugin(ModelMapper mapper,
                                IProductRepository productRepository,
                                IProductVariantRepository productVariantRepository) {
        super(CartItems.class, CartItemDTO.class, Long.class, mapper);
        this.productRepository = productRepository;
        this.productVariantRepository = productVariantRepository;
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

    @Override
    public CartItems toModel(CartItemDTO dto) {
        CartItems model = super.toModel(dto);

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

        return model;
    }
}
