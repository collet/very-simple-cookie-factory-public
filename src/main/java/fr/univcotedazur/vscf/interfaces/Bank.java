package fr.univcotedazur.vscf.interfaces;


import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.exceptions.PaymentException;

public interface Bank {

    boolean pay(Customer customer, double value) throws PaymentException;
}
