package com.example.finalexam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private Long customerNumber;

    private String name;

    private double initialDeposit;

    private int years;

    private String savingsType;

}
