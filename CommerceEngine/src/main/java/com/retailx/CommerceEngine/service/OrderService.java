package com.retailx.CommerceEngine.service;

import com.retailx.CommerceEngine.exception.CustomerNotFoundException;
import com.retailx.CommerceEngine.exception.ProductNotFoundException;
import com.retailx.CommerceEngine.exception.ProductOutOfStockException;
import com.retailx.CommerceEngine.model.CustomerAddress;
import com.retailx.CommerceEngine.model.Order;
import com.retailx.CommerceEngine.model.Payment;
import com.retailx.CommerceEngine.model.dto.OrderRequest;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest orderRequest) throws CustomerNotFoundException, ProductNotFoundException, ProductOutOfStockException;

    void cancelOrder(Long orderId);

    void updateShippingAddress(Long orderId, CustomerAddress newAddress);

    Order getOrderById(Long orderId);

    List<Order> getOrdersByCustomerId(Long customerId);

    void markOrderAsPaid(Long orderId, Payment payment);
    void updateOrderStatus(Long orderId, String status);
}
