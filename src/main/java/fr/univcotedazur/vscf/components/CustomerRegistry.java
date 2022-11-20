package fr.univcotedazur.vscf.components;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.exceptions.AlreadyExistingCustomerException;
import fr.univcotedazur.vscf.interfaces.CustomerFinder;
import fr.univcotedazur.vscf.interfaces.CustomerRegistration;
import fr.univcotedazur.vscf.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class CustomerRegistry implements CustomerRegistration, CustomerFinder {

    @Autowired // annotation is optional since Spring 4.3 if component has only one constructor
    public CustomerRegistry(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerRepository customerRepository;

    @Override
    public Customer register(String name, String creditCard)
            throws AlreadyExistingCustomerException {
        if(findByName(name).isPresent())
            throw new AlreadyExistingCustomerException(name);
        Customer newcustomer = new Customer(name, creditCard);
        customerRepository.save(newcustomer,newcustomer.getId());
        return newcustomer;
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                 .filter(cust -> name.equals(cust.getName())).findAny();
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }

}
