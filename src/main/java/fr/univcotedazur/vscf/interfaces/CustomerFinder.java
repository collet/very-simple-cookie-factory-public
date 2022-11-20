package fr.univcotedazur.vscf.interfaces;

import fr.univcotedazur.vscf.entities.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerFinder {

    Optional<Customer> findByName(String name);

    Optional<Customer> findById(UUID id);

}
