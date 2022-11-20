package fr.univcotedazur.vscf.components;

import fr.univcotedazur.vscf.entities.Customer;
import fr.univcotedazur.vscf.entities.Item;
import fr.univcotedazur.vscf.exceptions.EmptyCartException;
import fr.univcotedazur.vscf.exceptions.NegativeQuantityException;
import fr.univcotedazur.vscf.interfaces.CartModifier;
import fr.univcotedazur.vscf.interfaces.CartProcessor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CartHandler implements CartModifier, CartProcessor {

    @Override
    public int update(Customer c, Item item) throws NegativeQuantityException {
        int newQuantity = item.getQuantity();
        Set<Item> items = contents(c);
        Optional<Item> existing = items.stream().filter(e -> e.getCookie().equals(item.getCookie())).findFirst();
        if (existing.isPresent()) {
            newQuantity += existing.get().getQuantity();
        }
        if (newQuantity < 0) {
            throw new NegativeQuantityException(c.getName(), item.getCookie(), newQuantity);
        } else if (newQuantity >= 0) {
            if (existing.isPresent()) {
                items.remove(existing.get());
            }
            if (newQuantity > 0) {
                items.add(new Item(item.getCookie(), newQuantity));
            }
        }
        c.setCart(items);
        return newQuantity;
    }

    @Override
    public Set<Item> contents(Customer c) {
        return c.getCart();
    }

    @Override
    public double price(Customer c) {
        double result = 0.0;
        for (Item item : contents(c)) {
            result += (item.getQuantity() * item.getCookie().getPrice());
        }
        return result;
    }

    @Override
    public boolean validate(Customer c) throws EmptyCartException {
        // should return an order, MVP...
        if (contents(c).isEmpty())
            throw new EmptyCartException(c.getName());
        c.setCart(new HashSet<>());
        return true;
    }


}
