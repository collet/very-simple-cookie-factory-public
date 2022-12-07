package fr.univcotedazur.vscf.cucumber.ordering;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.exceptions.AlreadyExistingCustomerException;
import fr.univcotedazur.vscf.exceptions.EmptyCartException;
import fr.univcotedazur.vscf.exceptions.PaymentException;
import fr.univcotedazur.vscf.interfaces.CartProcessor;
import fr.univcotedazur.vscf.interfaces.CustomerRegistration;
import fr.univcotedazur.vscf.repositories.CustomerRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BadOrdering {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRegistration customerRegistration;

    @Autowired
    private CartProcessor cartProcessor;

    private Customer badBoy;
    private boolean validationRefused;

    @Before
    public void settingUpContext() {
        customerRepository.deleteAll();
    }

    @Given("a bad customer")
    public void theBadCustomer() throws AlreadyExistingCustomerException {
        badBoy = customerRegistration.register("BadBoy", "dummy card");
    }

    @When("He validates an empty cart")
    public void validatesAnEmptyCart() {
        try {
            cartProcessor.validate(badBoy);
        } catch (EmptyCartException e) {
            validationRefused = true;
            return;
        } catch (PaymentException e) {
        }
        validationRefused = false;
    }

    @Then("the order is not created")
    public void theOrderIsNotCreated() {
        assertTrue(validationRefused);
    }

}
