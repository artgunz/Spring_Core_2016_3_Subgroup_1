package spring.core.velocity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import spring.core.data.User;
import spring.core.data.UserTicket;
import spring.core.service.BookingService;
import spring.core.service.UserService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"{userEmail}"}, method = RequestMethod.GET)
    public String getUserByEmail(@PathVariable("userEmail") String userEmail, Model model) {
        model.addAttribute("users", Collections.singletonList(userService.getUserByEmail(userEmail)));
        return "users";
    }

    @RequestMapping(value = {"{userEmail}/tickets/list", "{userEmail}/tickets/list.pdf"})
    @ResponseStatus(HttpStatus.OK)
    public String getTicketsForUserEmail(@PathVariable("userEmail") String userEmail, Model model) {
        User user = userService.getUserByEmail(userEmail);
        List<UserTicket> tikets = bookingService.getTicketsForUser(user);
        model.addAttribute("tickets", tikets);
        model.addAttribute("userEmail", userEmail);

        return "tickets";
    }

    @RequestMapping(value = {"{userEmail}/tickets/list/npe"})
    @ResponseStatus(HttpStatus.OK)
    public String getTicketsForUserEmail2(@PathVariable("userEmail") String userEmail, Model model) {
        throw  new NullPointerException("sdsdsd");
    }

}
