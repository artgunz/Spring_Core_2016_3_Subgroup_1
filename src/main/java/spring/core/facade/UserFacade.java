package spring.core.facade;

import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.data.UserTicket;

import java.util.List;

public interface UserFacade {

    User register(UserRegistrationInformation registrationInformation);

    void remove(User user);

    User getById(Long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    List<UserTicket> getBookedTickets(User user);

}
