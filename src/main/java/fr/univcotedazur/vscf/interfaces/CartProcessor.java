package fr.univcotedazur.vscf.interfaces;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.entities.Item;
import fr.univcotedazur.vscf.exceptions.EmptyCartException;

import java.util.Set;

public interface CartProcessor {

    Set<Item> contents(Customer c);

    double price(Customer c);

    boolean validate(Customer c) throws EmptyCartException;

}
