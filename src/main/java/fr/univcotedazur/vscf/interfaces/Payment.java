package fr.univcotedazur.vscf.interfaces;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.entities.Item;
import fr.univcotedazur.vscf.entities.Order;
import fr.univcotedazur.vscf.exceptions.PaymentException;

import java.util.Set;

public interface Payment {

    Order payOrder(Customer customer, Set<Item> items) throws PaymentException;

}
