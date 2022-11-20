package fr.univcotedazur.vscf.repositories;

import fr.univcotedazur.repositories.BasicRepositoryImpl;
import fr.univcotedazur.vscf.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OrderRepository extends BasicRepositoryImpl<Order, UUID> {
}
