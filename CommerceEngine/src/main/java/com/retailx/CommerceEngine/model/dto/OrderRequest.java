package com.retailx.CommerceEngine.model.dto;

import com.retailx.CommerceEngine.model.CustomerAddress;
import com.retailx.CommerceEngine.model.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderRequest {
    private Long customerId;
    private CustomerAddress shippingAddress;
    private List<OrderItemDTO> items;
    private Payment payment;
}
