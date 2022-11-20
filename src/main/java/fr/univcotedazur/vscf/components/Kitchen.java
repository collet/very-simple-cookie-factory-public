package fr.univcotedazur.vscf.components;

import fr.univcotedazur.vscf.entities.Order;
import fr.univcotedazur.vscf.entities.OrderStatus;
import fr.univcotedazur.vscf.exceptions.UnknownOrderId;
import fr.univcotedazur.vscf.interfaces.OrderProcessing;
import fr.univcotedazur.vscf.interfaces.Tracker;
import fr.univcotedazur.vscf.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class Kitchen implements OrderProcessing, Tracker {

    OrderRepository orderRepository;

    @Autowired
    public Kitchen(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void process(Order order) {
        order.setStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(order, order.getId());
    }

    @Override
    public OrderStatus retrieveStatus(UUID orderId) throws UnknownOrderId {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty())
            throw new UnknownOrderId(orderId);
        return  orderOpt.get().getStatus();
    }

}
