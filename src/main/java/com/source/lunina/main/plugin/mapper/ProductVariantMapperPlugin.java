package com.source.lunina.main.plugin.mapper;

import com.source.lunina.common.plugin.AbstractMapperPlugin;
import com.source.lunina.main.dto.ProductVariantDTO;
import com.source.lunina.main.entity.ProductVariants;
import com.source.lunina.main.entity.Products;
import com.source.lunina.main.repository.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductVariantMapperPlugin extends AbstractMapperPlugin<ProductVariants, ProductVariantDTO, Long> {

    private final IProductRepository productRepository;
    public ProductVariantMapperPlugin(ModelMapper mapper,
                                      IProductRepository productRepository) {
        super(ProductVariants.class, ProductVariantDTO.class, Long.class, mapper);
        this.productRepository = productRepository;
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

    @Override
    public ProductVariants toModel(ProductVariantDTO dto) {
        ProductVariants model = super.toModel(dto);

        // Fix lỗi Transient ở đây
        if (dto.getProductId() != null) {
            Products pro = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            model.setProduct(pro);
        }

        return model;
    }

    @Override
    public ProductVariantDTO toDto(ProductVariants model) {
        if (model == null) return null;

        // Gọi hàm toDto của lớp cha để map các trường cơ bản
        ProductVariantDTO dto = super.toDto(model);

        // Gán userId thủ công từ quan hệ ManyToOne
        if (model.getProduct() != null) {
            dto.setProductId(model.getProduct().getId());
        }

        return dto;
    }
}
