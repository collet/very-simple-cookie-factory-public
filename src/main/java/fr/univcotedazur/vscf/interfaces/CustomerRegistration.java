package fr.univcotedazur.vscf.interfaces;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.exceptions.AlreadyExistingCustomerException;

public interface CustomerRegistration {

    Customer register(String name, String creditCard)
            throws AlreadyExistingCustomerException;
}
