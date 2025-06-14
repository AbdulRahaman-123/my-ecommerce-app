package com.retailx.CommerceEngine.service.impl;

import com.retailx.CommerceEngine.model.Product;
import com.retailx.CommerceEngine.repository.ProductRepository;
import com.retailx.CommerceEngine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product onboardProduct(Product product) {    //Add a product to db
        if (product.getAvailableQuantity()<0 || product.getPrice()<0){
            throw new IllegalArgumentException("Invalid Data");
        }
        Product saved = productRepository.save(product);
        return saved;
    }

    @Override
    public Product stockUpProduct(Long productId, Long newQuantity) {   //Update the quantity by ID//TODO
        if (productId == null|| newQuantity==null|| newQuantity<=0){
            throw new IllegalArgumentException("Invalid product Id or quantity");
        }
        Product productUp = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("product with Id: "+productId+" not found"));
        Long updatedQty = productUp.getAvailableQuantity()+newQuantity;
        productUp.setAvailableQuantity(updatedQty);
        return productRepository.save(productUp);
    }

    @Override
    public Product deleteProduct(Long productId) { //Delete a product by ID
        if (productId == null){
            throw new IllegalArgumentException("Product Id cannot be null");
        }
        Product theProduct = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));
        productRepository.delete(theProduct);
        return theProduct;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {  //Get a product by ID
        if (productId == null){
            throw new IllegalArgumentException("Product Id cannot be null");
        }
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() { //Get all products
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void updateProductPrice(Long productId, Double newPrice) {
        if (productId == null|| newPrice == null){
            throw new IllegalArgumentException("ID or newPrice Cannot be null");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("No product found with ID: "+productId));
        product.setPrice(newPrice);
        productRepository.save(product);
    }
}
