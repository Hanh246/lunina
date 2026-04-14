package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends AbstractCrudRepository<OrderDetail, Long> {
    @Query("""
            SELECT o
            FROM OrderDetail o
            WHERE o.order.id = :oid
            AND o.deleted = false
            """)
    List<OrderDetail> findByOrderID(Long oid);
}
