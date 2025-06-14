package com.retailx.CommerceEngine.repository;

import com.retailx.CommerceEngine.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
