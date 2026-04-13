package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.Products;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends AbstractCrudRepository<Products, Long> {
}
