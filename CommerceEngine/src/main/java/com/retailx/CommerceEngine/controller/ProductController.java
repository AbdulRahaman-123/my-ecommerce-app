package com.retailx.CommerceEngine.controller;

import com.retailx.CommerceEngine.model.Product;
import com.retailx.CommerceEngine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/onboard")
    public ResponseEntity onboard(@RequestBody Product product){
        Product saved = productService.onboardProduct(product);
        return ResponseEntity.ok("Product Onboarded");
    }
    @PutMapping("/{productId}/update/stock")    //TODO
    public ResponseEntity stockUp(@PathVariable Long productId, @RequestBody Long quantity){
        Product updatedProduct = productService.stockUpProduct(productId, quantity);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{productId}/delete")
    public ResponseEntity delete(@PathVariable Long productId){
        Product dltProduct = productService.deleteProduct(productId);
        return ResponseEntity.ok(dltProduct);
    }
    @GetMapping("/get/{productId}")
    public ResponseEntity getProduct(@PathVariable Long productId){
        Optional<Product> getProduct = productService.getProductById(productId);
//                .orElseThrow(()-> new IllegalArgumentException("No product found"));
        return ResponseEntity.ok(getProduct);
    }
    @GetMapping("/get/all")
    public ResponseEntity getAll(){
        List<Product> allProd = productService.getAllProducts();
        return ResponseEntity.ok(allProd);
    }
    @PutMapping("{productId}/update/price")
    public ResponseEntity updatePrice(@PathVariable Long productId, @RequestBody Double newPrice){
        productService.updateProductPrice(productId, newPrice);
        return ResponseEntity.ok("Product price updated");
    }
}
