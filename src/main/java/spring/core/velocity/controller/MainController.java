package spring.core.velocity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.core.App;

@Controller
public class MainController {
    @Autowired
    ApplicationContext context;

    @RequestMapping(value = {"/cinema/testinit"}, method = RequestMethod.GET)
    public String testInit() {
        try {
            App app = new App();
            app.init(context);
            app.initAuditoriums();
            app.makeInitDB(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }
}
