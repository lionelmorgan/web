package com.spider.services;

import com.spider.models.Customers;
import com.spider.repos.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepo customersRepo;

    public Customers createCustomer(Customers customer) {
        return customersRepo.save(customer);
    }

    public Optional<Customers> getCustomerById(Long customerId) {
        return customersRepo.findById(customerId);
    }

    public Optional<Customers> getCustomerByEmail(String email) {
        return customersRepo.findByEmail(email);
    }

    public List<Customers> getAllCustomers() {
        return customersRepo.findAll();
    }

    public Customers updateCustomer(Long customerId, Customers customerDetails) {
        if (customersRepo.existsById(customerId)) {
            customerDetails.setCustomerId(customerId);
            return customersRepo.save(customerDetails);
        }
        return null;
    }

    public boolean deleteCustomer(Long customerId) {
        if (customersRepo.existsById(customerId)) {
            customersRepo.deleteById(customerId);
            return true;
        }
        return false;
    }
}
