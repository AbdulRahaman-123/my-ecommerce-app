package com.retailx.CommerceEngine.repository;

import com.retailx.CommerceEngine.model.Reviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends CrudRepository<Reviews, Long> {
    List<Reviews> findByProductProductId(Long productProductId);

    List<Reviews> findByCustomer_CustomerId(Long customerCustomerId);
}
