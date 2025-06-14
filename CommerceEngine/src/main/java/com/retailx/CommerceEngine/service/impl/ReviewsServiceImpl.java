package com.retailx.CommerceEngine.service.impl;

import com.retailx.CommerceEngine.model.Customer;
import com.retailx.CommerceEngine.model.Product;
import com.retailx.CommerceEngine.model.Reviews;
import com.retailx.CommerceEngine.model.dto.ReviewRequestDTO;
import com.retailx.CommerceEngine.repository.CustomerRepository;
import com.retailx.CommerceEngine.repository.ProductRepository;
import com.retailx.CommerceEngine.repository.ReviewsRepository;
import com.retailx.CommerceEngine.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Reviews addReview(ReviewRequestDTO reviewRequestDTO) {   //The input Jason would be DTO
        Product product = productRepository.findById(reviewRequestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Customer customer = customerRepository.findById(reviewRequestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Reviews review = new Reviews();
        review.setComment(reviewRequestDTO.getComment());
        review.setRating(reviewRequestDTO.getRating());
        review.setReviewedAt(LocalDateTime.now());
        review.setProduct(product);
        review.setCustomer(customer);

        return reviewsRepository.save(review);
    }

    @Override
    public List<Reviews> getReviewsByProductId(Long productId) {
        return reviewsRepository.findByProductProductId(productId);
    }

    @Override
    public List<Reviews> getReviewsByCustomerId(Long customerId) {
        return reviewsRepository.findByCustomer_CustomerId(customerId);
    }
}
