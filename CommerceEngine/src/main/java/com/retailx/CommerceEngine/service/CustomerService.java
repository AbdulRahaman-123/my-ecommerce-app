package com.retailx.CommerceEngine.service;

import com.retailx.CommerceEngine.model.Customer;
import com.retailx.CommerceEngine.model.CustomerAddress;

import java.util.List;

public interface CustomerService {


    Customer enrollCustomer(Customer customer);

    Customer updatePhoneNo(Long customer_id, String mobileNumber);

    Customer getCustomerById(Long customerId);

    Customer deleteCustomer(Long customerId);

    List<Customer> getAllCustomers();

    Customer updateCustomerAddress(Long customerId, CustomerAddress newAddress);
}
