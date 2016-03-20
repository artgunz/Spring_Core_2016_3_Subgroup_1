package spring.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring.core.aop.aspect.impl.BookingAspect;
import spring.core.aop.aspect.impl.DefaultLoggerAspect;
import spring.core.aop.aspect.impl.EventAspect;
import spring.core.aop.aspect.impl.EventUserLuckyAspect;

@Configuration
@EnableAspectJAutoProxy
public class AspectSpringConfiguration {

    @Bean
    public EventAspect eventAspect() {
        return new EventAspect();
    }

    @Bean
    public BookingAspect bookingAspect() {
        return new BookingAspect();
    }

    @Bean
    public DefaultLoggerAspect defaultAspect() {
        return new DefaultLoggerAspect();
    }

    @Bean
    public EventUserLuckyAspect eventUserLuckyAspect() {
        return new EventUserLuckyAspect();
    }

}
