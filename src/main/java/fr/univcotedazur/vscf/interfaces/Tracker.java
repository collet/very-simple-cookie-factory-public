package fr.univcotedazur.vscf.interfaces;

import fr.univcotedazur.vscf.entities.OrderStatus;
import fr.univcotedazur.vscf.exceptions.UnknownOrderId;

import java.util.UUID;

public interface Tracker {

    OrderStatus retrieveStatus(UUID orderId) throws UnknownOrderId;

}