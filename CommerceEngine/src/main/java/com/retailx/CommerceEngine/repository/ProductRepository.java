package com.retailx.CommerceEngine.repository;

import com.retailx.CommerceEngine.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
