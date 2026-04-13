package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.Categories;
import com.source.lunina.main.entity.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends AbstractCrudRepository<OrderDetail, Long> {
}
