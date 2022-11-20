package fr.univcotedazur.vscf.connectors;

import fr.univcotedazur.vscf.interfaces.Bank;
import fr.univcotedazur.vscf.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class BankProxy implements Bank {

    @Override
    public boolean pay(Customer customer, double value) {
        // should be an external connection to a bank service
        return (value>0);
    }

}