package spring.core.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import spring.core.Application;
import spring.core.csv.loader.auditorium.AuditoriumLoader;
import spring.core.csv.loader.auditorium.impl.AuditoriumLoaderImpl;
import spring.core.strategy.discount.DiscountStrategy;
import spring.core.strategy.discount.impl.UserBirthDiscountStrategyImpl;
import spring.core.strategy.discount.impl.UserTicketsBoughtDiscountStrategyImpl;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource("classpath:persistence.properties")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackageClasses = Application.class)
@Import(value = {AspectSpringConfiguration.class, DBSpringConfiguration.class})
class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "auditoriumLoaderImpl")
    @Profile("dev")
    public AuditoriumLoader auditoriumLoaderDev() {
        AuditoriumLoader auditoriumLoader = new AuditoriumLoaderImpl();
        auditoriumLoader.setResourceName("classpath:auditorium.csv");

        return auditoriumLoader;
    }

    @Bean(name = "auditoriumLoaderImpl")
    @Profile("test")
    public AuditoriumLoader auditoriumLoaderTest() {
        AuditoriumLoader auditoriumLoader = new AuditoriumLoaderImpl();
        auditoriumLoader.setResourceName("classpath:auditoriumTest.csv");

        return auditoriumLoader;
    }

    @Bean
    public List<DiscountStrategy> discountStrategyList() {
        List<DiscountStrategy> aList = new ArrayList<>();
        aList.add(new UserBirthDiscountStrategyImpl());
        aList.add(new UserTicketsBoughtDiscountStrategyImpl());

        return aList;
    }
}