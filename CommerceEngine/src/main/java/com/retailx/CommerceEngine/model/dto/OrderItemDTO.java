package com.retailx.CommerceEngine.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long productId;
    private Integer orderQuantity;
}
