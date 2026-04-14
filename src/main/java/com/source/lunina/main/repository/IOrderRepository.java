package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends AbstractCrudRepository<Orders, Long> {
    @Query("""
            SELECT o
            FROM Orders o
            WHERE o.user.id = :uid
            AND o.deleted = false
            """)
    Page<Orders> findByUserID(Pageable pageable, Long uid);
}
