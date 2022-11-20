package fr.univcotedazur.vscf.components;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.exceptions.AlreadyExistingCustomerException;
import fr.univcotedazur.vscf.interfaces.CustomerFinder;
import fr.univcotedazur.vscf.interfaces.CustomerRegistration;
import fr.univcotedazur.vscf.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRegistryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRegistration registry;

    @Autowired
    private CustomerFinder finder;

    private String name = "John";
    private String creditCard = "credit card number";

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void unknownCustomer() {
        assertFalse(finder.findByName(name).isPresent());
    }

    @Test
    public void registerCustomer() throws Exception {
        Customer returned = registry.register(name, creditCard);
        Optional<Customer> customer = finder.findByName(name);
        assertTrue(customer.isPresent());
        Customer john = customer.get();
        assertEquals(john,returned);
        assertEquals(john,finder.findById(returned.getId()).get());
        assertEquals(name, john.getName());
        assertEquals(creditCard, john.getCreditCard());
    }

    @Test
    public void cannotRegisterTwice() throws Exception {
        registry.register(name, creditCard);
        Assertions.assertThrows( AlreadyExistingCustomerException.class, () -> {
            registry.register(name, creditCard);
        });
    }

}