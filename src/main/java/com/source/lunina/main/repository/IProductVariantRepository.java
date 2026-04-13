package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.ProductVariants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductVariantRepository extends AbstractCrudRepository<ProductVariants, Long> {
    @Query("""
            SELECT u
            FROM ProductVariants u
            WHERE u.product.id = :pid
            AND u.deleted = false
            """)
    List<ProductVariants> findByProductID(Long pid);
}
