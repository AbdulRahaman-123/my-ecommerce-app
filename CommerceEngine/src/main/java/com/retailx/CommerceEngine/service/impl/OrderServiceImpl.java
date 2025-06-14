package com.retailx.CommerceEngine.service.impl;

import com.retailx.CommerceEngine.exception.CustomerNotFoundException;
import com.retailx.CommerceEngine.exception.ProductNotFoundException;
import com.retailx.CommerceEngine.exception.ProductOutOfStockException;
import com.retailx.CommerceEngine.model.*;
import com.retailx.CommerceEngine.model.dto.OrderItemDTO;
import com.retailx.CommerceEngine.model.dto.OrderRequest;
import com.retailx.CommerceEngine.repository.CustomerRepository;
import com.retailx.CommerceEngine.repository.OrderRepository;
import com.retailx.CommerceEngine.repository.ProductRepository;
import com.retailx.CommerceEngine.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order createOrder(OrderRequest orderRequest) /*throws CustomerNotFoundException, ProductNotFoundException, ProductOutOfStockException*/ {
        //validate customer with Id
        Optional<Customer> customerOptional = customerRepository.findById(orderRequest.getCustomerId());
        if (customerOptional.isEmpty()){
            throw new CustomerNotFoundException("Customer with ID: " +orderRequest.getCustomerId()+" is not found");
        }
        Customer customer = customerOptional.get();

        Order order = new Order();
        order.setCustomer(customer);
        order.setCustomerAddress(orderRequest.getShippingAddress());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        for (OrderItemDTO itemDTO : orderRequest.getItems()){   //validating the product with ID
            Optional<Product> productOptional = productRepository.findById(itemDTO.getProductId());
            if (productOptional.isEmpty()){
                throw new ProductNotFoundException("Product not found");
            }
            //if productId is found
            Product product = productOptional.get();
            OrderItem item = new OrderItem();   //Adding the product to the order

            //TODO: validate the requested quantity <= available quantity
            if (itemDTO.getOrderQuantity()>product.getAvailableQuantity()){
                throw new ProductOutOfStockException("Product : "+product.getProductName()+" is out of stock. Only "+itemDTO.getOrderQuantity()+ " left");
            }
            //Update the stock or Quantity of that product
            product.setAvailableQuantity(product.getAvailableQuantity() - itemDTO.getOrderQuantity());
            productRepository.save(product);


            item.setProduct(product);
            item.setOrderQuantity(itemDTO.getOrderQuantity());  //Adding requested quantity
            item.setOrder(order);

            order.getItems().add(item);
        }

        order.setPromisedDeliveryDate(LocalDate.now().plusDays(14));
        //TODO
        Double subTotal = 0.0;
        for (OrderItem item : order.getItems()){
            double itemTotal = item.getProduct().getPrice()*item.getOrderQuantity();

            subTotal += itemTotal;
        }
        double tax = subTotal * 0.08;
        double shippingCharges = subTotal>35.0 ? 0.0 : 7.00;
        double total = subTotal+tax+shippingCharges;
        order.setSubTotal(subTotal);
        order.setTax(tax);
        order.setShippingCharges(shippingCharges);
        order.setTotal(total);

        makePayment(orderRequest.getPayment());
        order.setPayment(orderRequest.getPayment());
        order.setCustomerAddress(orderRequest.getShippingAddress());
        Order saved = orderRepository.save(order);
        return saved;

    }

    private void makePayment(Payment payment) {

        String confirmationCode = "XYZ123";
        payment.setConfirmationCode(confirmationCode);
    }

    @Override
    public void cancelOrder(Long orderId) { //TODO
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()){
            throw new IllegalArgumentException("order with Id :"+orderId+" not found");
        }
        Order order = orderOptional.get();
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    @Override   //TODO
    public void updateShippingAddress(Long orderId, CustomerAddress newAddress) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        //Order Id Validation
        if (optionalOrder.isEmpty()){
            throw new IllegalArgumentException("Order with ID: "+orderId+" not found");
        }
        //Now if present
        Order order = optionalOrder.get();  //Get the required order for updation
        order.setCustomerAddress(newAddress);
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return List.of();
    }

    @Override
    public void markOrderAsPaid(Long orderId, Payment payment) {

    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {

    }
}
