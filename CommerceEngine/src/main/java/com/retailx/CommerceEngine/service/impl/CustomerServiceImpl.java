package com.retailx.CommerceEngine.service.impl;

import com.retailx.CommerceEngine.model.Customer;
import com.retailx.CommerceEngine.model.CustomerAddress;
import com.retailx.CommerceEngine.repository.CustomerRepository;
import com.retailx.CommerceEngine.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer enrollCustomer(Customer customer) {

        String emailInput = customer.getEmail();
        if (!emailInput.contains("@")){
            throw new IllegalArgumentException("Enter a valid email");
        }
        Customer saved = customerRepository.save(customer);
        return saved;
    }

    @Override
    public Customer updatePhoneNo(Long customer_id, String mobileNumber) {  //TODO
        if (customer_id == null){
            throw new IllegalArgumentException("Enter customer ID");
        }
        if (mobileNumber == null){
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (mobileNumber.matches("^(\\+\\d{1,3}[-\\s.]?)?\\d{10}$")){   //use 'matches' for full regex validation
            throw new IllegalArgumentException("Invalid mobile number");
        }
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(()-> new IllegalArgumentException("Customer not found"));
        //set the mobile number to the customer
        customer.setMobileNumber(mobileNumber);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(()-> new IllegalArgumentException("Custer with ID: "+customerId+" not found"));
    }

    @Override
    public Customer deleteCustomer(Long customerId) {
        Customer member = customerRepository.findById(customerId)
                .orElseThrow(()-> new IllegalArgumentException("Custer with ID: "+customerId+" not found"));
        customerRepository.delete(member);
        return member;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer updateCustomerAddress(Long customerId, CustomerAddress newAddress) {
        if (customerId == null || newAddress == null) {
            throw new IllegalArgumentException("Customer ID and new address are required");
        }
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new IllegalArgumentException("Custer with ID: "+customerId+" not found"));
        customer.setC_address(newAddress);
        return customerRepository.save(customer);
    }
}
