package com.retailx.CommerceEngine.service;

import com.retailx.CommerceEngine.model.Reviews;
import com.retailx.CommerceEngine.model.dto.ReviewRequestDTO;

import java.util.List;

public interface ReviewsService {
    Reviews addReview(ReviewRequestDTO reviewRequestDTO);
    List<Reviews> getReviewsByProductId(Long productId);
    List<Reviews> getReviewsByCustomerId(Long customerId);
}
