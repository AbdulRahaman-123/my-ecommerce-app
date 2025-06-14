package com.retailx.CommerceEngine.service;

import com.retailx.CommerceEngine.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product onboardProduct(Product product);

    Product deleteProduct(Long productId);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product stockUpProduct(Long productId, Long newQuantity);

    void updateProductPrice(Long productId, Double newPrice);
}
