package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.CartItems;
import com.source.lunina.main.entity.Categories;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriesRepository extends AbstractCrudRepository<Categories, Long> {
}
