package com.retailx.CommerceEngine.model.dto;

import com.retailx.CommerceEngine.model.ReviewRating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {
    private Long productId;
    private Long customerId;
    private String comment;
    private ReviewRating rating;
}
