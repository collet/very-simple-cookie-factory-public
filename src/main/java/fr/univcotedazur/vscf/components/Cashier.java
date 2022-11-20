package fr.univcotedazur.vscf.components;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.entities.Item;
import fr.univcotedazur.vscf.entities.Order;
import fr.univcotedazur.vscf.exceptions.PaymentException;
import fr.univcotedazur.vscf.interfaces.Bank;
import fr.univcotedazur.vscf.interfaces.OrderProcessing;
import fr.univcotedazur.vscf.interfaces.Payment;
import fr.univcotedazur.vscf.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Cashier implements Payment {

    private Bank bank;

    private OrderProcessing kitchen;

    private OrderRepository orderRepository;

    @Autowired
    public Cashier(Bank bank, OrderProcessing kitchen, OrderRepository orderRepository) {
        this.bank = bank;
        this.kitchen = kitchen;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order payOrder(Customer customer, Set<Item> items) throws PaymentException {
        Order order = new Order(customer, new HashSet<>(items));
        double price = order.getPrice();
        boolean status = false;
        status = bank.pay(customer, price);
        if (!status) {
            throw new PaymentException(customer.getName(), price);
        }
        orderRepository.save(order,order.getId());
        kitchen.process(order);
        return order;
    }

}
