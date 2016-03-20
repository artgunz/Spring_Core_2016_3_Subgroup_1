package spring.core.service;

import spring.core.data.*;

import java.util.Date;
import java.util.List;

public interface BookingService {
    Price getTicketPrice(Event event, Date date, Seat seat, User user);

    UserTicket bookTicket(User user, Price price, TicketCreationInformation ticketCreationInformation);

    List<Ticket> getTicketsForEvent(Event event, Date date);

    List<UserTicket> getTicketsForUser(User user);
}
