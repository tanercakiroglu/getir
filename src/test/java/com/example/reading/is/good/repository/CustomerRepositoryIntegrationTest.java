package com.example.reading.is.good.repository;

import com.example.reading.is.good.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private ICustomerRepository customerRepository;

    @Test
    public void given_CustomerSave_ThenReturnCustomer() {

      var customer=  Customer.builder()
                .username("abcd")
                .password("abc")
                .surname("def")
                .name("fgh")
                .build();

        var found = customerRepository.save(customer);

        assertEquals(found.getUsername(),customer.getUsername());
    }


}
