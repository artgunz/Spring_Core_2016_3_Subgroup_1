package spring.core.aop.aspect.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import spring.core.aop.AOPHelper;
import spring.core.data.*;
import spring.core.service.UserService;

import java.util.Random;

@Aspect
public class EventUserLuckyAspect {
    private static final Logger LOGGER = LogManager.getLogger(EventUserLuckyAspect.class);

    @Autowired
    UserService userService;

    @Pointcut("execution(* spring.core.service.BookingService.*(..))")
    public void inBookingService() {
    }

    @Around(value = "inBookingService() && execution(* *.getTicketPrice(.., spring.core.data.User ,..))")
    public Price luckyPrice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (isLucky()) {
            User user = AOPHelper.getArgWithType(proceedingJoinPoint, User.class);
            assert user != null;

            LOGGER.info("User {} is lucky today!!! Congrats!", user.getName());

            addLuckyEvent(proceedingJoinPoint);

            return new Price(Currency.USD, 0.0);
        } else {
            return (Price) proceedingJoinPoint.proceed();
        }
    }


    private void addLuckyEvent(JoinPoint joinPoint) {
        User user = AOPHelper.getArgWithType(joinPoint, User.class);
        assert user != null;

        UserStatistic userStatistic = user.getUserStatistic();
        if (userStatistic == null) {
            userStatistic = new UserStatistic();
        }

        Event event = AOPHelper.getArgWithType(joinPoint, Event.class);
        assert event != null;

        userStatistic.getLuckyEvents().add(event);

        userService.updateUserStatisticByEmail(user.getEmail(), userStatistic);
    }

    private boolean isLucky() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
