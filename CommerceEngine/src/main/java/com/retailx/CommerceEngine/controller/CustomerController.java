package com.retailx.CommerceEngine.controller;

import com.retailx.CommerceEngine.model.Customer;
import com.retailx.CommerceEngine.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/enroll")
    public ResponseEntity enroll(@RequestBody Customer customer) {
        Customer enrolled = customerService.enrollCustomer(customer);
        return ResponseEntity.ok("Customer Enrolled!");    }

    @PutMapping("/{customerId}/update/contact")
    public ResponseEntity updatePhone(@PathVariable Long customerId, @RequestBody String newNumber) {
        Customer updatedCustomer = customerService.updatePhoneNo(customerId, newNumber);
        return ResponseEntity.ok("Contact Updated Successfully");
    }
    @GetMapping("/find/{customerId}")
    public Customer searchById(@PathVariable Long customerId){
        Customer retrievedCustomer = customerService.getCustomerById(customerId);
        return retrievedCustomer;
    }
    @DeleteMapping("/delete/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        Customer retrievedCustomer =customerService.deleteCustomer(customerId);
    }

}
