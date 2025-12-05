package com.example.finalexam;

import com.example.finalexam.model.Customer;
import com.example.finalexam.repository.CustomerRepository;
import com.example.finalexam.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepo;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testCustomerNumberExists_ReturnsTrue() {
        Long id = 1L;
        when(customerRepo.existsByCustomerNumber(id)).thenReturn(true);

        boolean result = customerService.customerNumberExists(id);

        assertTrue(result);
        verify(customerRepo, times(1)).existsByCustomerNumber(id);
    }

    @Test
    void testCustomerNumberExists_ReturnsFalse() {
        Long id = 99L;
        when(customerRepo.existsByCustomerNumber(id)).thenReturn(false);

        boolean result = customerService.customerNumberExists(id);

        assertFalse(result);
        verify(customerRepo, times(1)).existsByCustomerNumber(id);
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setCustomerNumber(5L);
        customer.setName("Leif");
        customer.setSavingsType("Basic");
        customer.setInitialDeposit(100.0);
        customer.setYears(2);

        when(customerRepo.save(customer)).thenReturn(customer);

        Customer saved = customerService.save(customer);

        assertNotNull(saved);
        assertEquals("Leif", saved.getName());
        verify(customerRepo, times(1)).save(customer);
    }

    @Test
    void testFindAllCustomers() {
        Customer c1 = new Customer();
        c1.setCustomerNumber(1L);
        c1.setName("A");
        c1.setInitialDeposit(100.0);
        c1.setYears(1);
        c1.setSavingsType("Basic");

        Customer c2 = new Customer();
        c2.setCustomerNumber(2L);
        c2.setName("B");
        c2.setInitialDeposit(200.0);
        c2.setYears(2);
        c2.setSavingsType("Premium");

        when(customerRepo.findAll()).thenReturn(Arrays.asList(c1, c2));

        // adjust if your method name is different
        List<Customer> list = customerService.findAll();

        assertEquals(2, list.size());
        assertEquals("A", list.get(0).getName());
        assertEquals("B", list.get(1).getName());
        verify(customerRepo, times(1)).findAll();
    }
}
