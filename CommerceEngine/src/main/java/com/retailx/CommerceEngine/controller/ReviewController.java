package com.retailx.CommerceEngine.controller;

import com.retailx.CommerceEngine.model.Reviews;
import com.retailx.CommerceEngine.model.dto.ReviewRequestDTO;
import com.retailx.CommerceEngine.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewsService reviewsService;
    @PostMapping("/write")
    public ResponseEntity  addReview(@RequestBody ReviewRequestDTO reviewRequestDTO){
        Reviews reviews = reviewsService.addReview(reviewRequestDTO);
        return ResponseEntity.ok(reviewRequestDTO);
    }
    @GetMapping("/findby/product/{productId}")
    public ResponseEntity getByProductId(@PathVariable Long productId){
        List<Reviews> reviews = reviewsService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("/findby/customer/{customerId}")
    public ResponseEntity getByCustomerId(@PathVariable Long customerId){
        List<Reviews> reviews = reviewsService.getReviewsByCustomerId(customerId);
        return ResponseEntity.ok(reviews);
    }

}
