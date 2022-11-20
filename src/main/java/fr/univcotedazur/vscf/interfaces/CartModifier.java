package fr.univcotedazur.vscf.interfaces;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.entities.Item;
import fr.univcotedazur.vscf.exceptions.NegativeQuantityException;

public interface CartModifier {

    int update(Customer retrieveCustomer, Item it) throws NegativeQuantityException;

}
