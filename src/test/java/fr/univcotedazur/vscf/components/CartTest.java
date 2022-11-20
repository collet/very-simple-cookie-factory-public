package fr.univcotedazur.vscf.components;

import fr.univcotedazur.vscf.entities.Cookies;
import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.entities.Item;
import fr.univcotedazur.vscf.exceptions.AlreadyExistingCustomerException;
import fr.univcotedazur.vscf.exceptions.EmptyCartException;
import fr.univcotedazur.vscf.exceptions.NegativeQuantityException;
import fr.univcotedazur.vscf.interfaces.CartModifier;
import fr.univcotedazur.vscf.interfaces.CartProcessor;
import fr.univcotedazur.vscf.interfaces.CustomerFinder;
import fr.univcotedazur.vscf.interfaces.CustomerRegistration;
import fr.univcotedazur.vscf.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CartTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRegistration registry;

    @Autowired
    private CustomerFinder finder;

    @Autowired
    private CartModifier cart;

    @Autowired
    private CartProcessor processor;

    private Customer john;

    @BeforeEach
    void setUp() throws AlreadyExistingCustomerException {
        customerRepository.deleteAll();
        registry.register("John", "credit card number");
        john = finder.findByName("John").get();
    }

    @Test
    public void emptyCartByDefault() {
        assertEquals(0,processor.contents(john).size());
    }

    @Test
    public void addItems() throws NegativeQuantityException {
        cart.update(john, new Item(Cookies.CHOCOLALALA, 2));
        cart.update(john, new Item(Cookies.DARK_TEMPTATION, 3));
        Set<Item> oracle = Set.of(new Item(Cookies.CHOCOLALALA, 2), new Item(Cookies.DARK_TEMPTATION, 3));
        assertEquals(oracle, processor.contents(john));
    }

    @Test
    public void removeItems() throws NegativeQuantityException {
        cart.update(john, new Item(Cookies.CHOCOLALALA, 2));
        cart.update(john, new Item(Cookies.CHOCOLALALA, -2));
        assertEquals(0,processor.contents(john).size());
        cart.update(john, new Item(Cookies.CHOCOLALALA, 6));
        cart.update(john, new Item(Cookies.CHOCOLALALA, -5));
        Set<Item> oracle = Set.of(new Item(Cookies.CHOCOLALALA, 1));
        assertEquals(oracle, processor.contents(john));
    }

    @Test
    public void removeTooMuchItems() throws NegativeQuantityException {
        cart.update(john, new Item(Cookies.CHOCOLALALA, 2));
        cart.update(john, new Item(Cookies.DARK_TEMPTATION, 3));
        Assertions.assertThrows( NegativeQuantityException.class, () -> {
            cart.update(john, new Item(Cookies.CHOCOLALALA, -3));
        });
        Set<Item> oracle = Set.of(new Item(Cookies.CHOCOLALALA, 2), new Item(Cookies.DARK_TEMPTATION, 3));
        assertEquals(oracle, processor.contents(john));
    }

    @Test
    public void modifyQuantities() throws NegativeQuantityException {
        cart.update(john, new Item(Cookies.CHOCOLALALA, 2));
        cart.update(john, new Item(Cookies.DARK_TEMPTATION, 3));
        cart.update(john, new Item(Cookies.CHOCOLALALA, 3));
        Set<Item> oracle = Set.of(new Item(Cookies.CHOCOLALALA, 5), new Item(Cookies.DARK_TEMPTATION, 3));
        assertEquals(oracle, processor.contents(john));
    }

    @Test
    public void getTheRightPrice() throws NegativeQuantityException {
        cart.update(john, new Item(Cookies.CHOCOLALALA, 2));
        cart.update(john, new Item(Cookies.DARK_TEMPTATION, 3));
        cart.update(john, new Item(Cookies.CHOCOLALALA, 3));
        assertEquals(12.20, processor.price(john), 0.01);
    }

    @Test
    public void cannotProcessEmptyCart() throws Exception {
        assertEquals(0,processor.contents(john).size());
        Assertions.assertThrows( EmptyCartException.class, () -> {
            processor.validate(john);
        });
    }

}