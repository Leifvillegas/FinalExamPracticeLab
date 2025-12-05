package com.example.finalexam.controller;

import com.example.finalexam.model.Customer;
import com.example.finalexam.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//https://github.com/Leifvillegas/FinalExamPracticeLab.git

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;

    }

    // show table and buttons
    @GetMapping("/")
    public String listCustomers(@RequestParam(value = "error", required = false) String error, Model model) {

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("errorMessage", error);
        return "index";

    }

    // show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("customer", new Customer());
        return "form";

    }

    // handle add submit
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {

        if (customerService.customerNumberExists(customer.getCustomerNumber())) {

            return "redirect:/?error=Customer+number+already+exists";

        }

        customerService.save(customer);
        return "redirect:/";

    }

    // show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Customer customer = customerService.findById(id);
        if (customer == null) {

            return "redirect:/?error=Customer+not+found";

        }

        model.addAttribute("customer", customer);
        return "form";

    }

    // handle edit submit
    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {

        customer.setCustomerNumber(id);
        customerService.save(customer);
        return "redirect:/";

    }

    // delete customer
    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.delete(id);
        return "redirect:/";

    }

    // show projection table
    @GetMapping("/project/{id}")
    public String projection(@PathVariable Long id, Model model) {

        Customer customer = customerService.findById(id);
        if (customer == null) {

            return "redirect:/?error=Customer+not+found";

        }

        model.addAttribute("customer", customer);
        model.addAttribute("projection",
                customerService.computeProjection(
                        customer.getInitialDeposit(),
                        customer.getYears(),
                        customer.getSavingsType()
                )
        );

        return "projection";

    }

}
