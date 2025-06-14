package com.retailx.CommerceEngine.controller;

import com.retailx.CommerceEngine.exception.CustomerNotFoundException;
import com.retailx.CommerceEngine.exception.ProductNotFoundException;
import com.retailx.CommerceEngine.model.Customer;
import com.retailx.CommerceEngine.model.CustomerAddress;
import com.retailx.CommerceEngine.model.Order;
import com.retailx.CommerceEngine.model.dto.OrderRequest;
import com.retailx.CommerceEngine.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequest orderRequest) throws CustomerNotFoundException, ProductNotFoundException {
        Order newOrder = orderService.createOrder(orderRequest);
        return newOrder;
    }
    @PutMapping("/status/cancel/{orderId}")
    public ResponseEntity cancelling(@PathVariable Long orderId ){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled successfully");
    }
    @PutMapping("/update/delivery/address/{orderId}")
    public ResponseEntity updateShippingAdd(@PathVariable Long orderId, @RequestBody CustomerAddress newAddress){
        orderService.updateShippingAddress(orderId, newAddress);
        return ResponseEntity.ok("Shipping address updated!");
    }
}
