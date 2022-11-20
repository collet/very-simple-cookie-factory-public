package fr.univcotedazur.vscf.repositories;

import fr.univcotedazur.repositories.BasicRepositoryImpl;
import fr.univcotedazur.vscf.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CustomerRepository extends BasicRepositoryImpl<Customer, UUID> {
}
