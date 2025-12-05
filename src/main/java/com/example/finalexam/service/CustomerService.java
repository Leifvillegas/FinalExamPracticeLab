package com.example.finalexam.service;

import com.example.finalexam.model.Customer;
import com.example.finalexam.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;

    @Autowired
    public CustomerService(CustomerRepository customerRepo) {

        this.customerRepo = customerRepo;

    }

    public List<Customer> findAll() {

        return customerRepo.findAll();

    }

    public Customer findById(Long id) {

        return customerRepo.findById(id).orElse(null);

    }

    public boolean customerNumberExists(Long customerNumber) {

        return customerRepo.existsByCustomerNumber(customerNumber);

    }

    public Customer save(Customer customer) {

        return customerRepo.save(customer);

    }

    public void delete(Long id) {

        customerRepo.deleteById(id);

    }


    //for compound interest values

    public List<Double> computeProjection(double initialDeposit, int years, String savingsType) {

        double rate = "DE_LUXE".equalsIgnoreCase(savingsType) ? 0.15 : 0.10;

        List<Double> results = new ArrayList<>();
        double amount = initialDeposit;

        for (int i = 1; i <= years; i++) {

            amount = amount * (1 + rate);

            results.add(amount);

        }

        return results;

    }

}
