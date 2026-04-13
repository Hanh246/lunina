package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends AbstractCrudRepository<Orders, Long> {
}
