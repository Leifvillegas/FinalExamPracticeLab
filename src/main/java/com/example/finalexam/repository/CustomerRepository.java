package com.example.finalexam.repository;

import com.example.finalexam.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByCustomerNumber(Long customerNumber);

}
