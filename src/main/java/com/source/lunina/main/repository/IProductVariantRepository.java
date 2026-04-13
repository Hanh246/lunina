package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.ProductVariants;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductVariantRepository extends AbstractCrudRepository<ProductVariants, Long> {
}
