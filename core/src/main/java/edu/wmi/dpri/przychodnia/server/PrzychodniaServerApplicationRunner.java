package edu.wmi.dpri.przychodnia.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by lupus on 21.09.16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"edu.wmi.dpri.przychodnia.server.repository"})
@EntityScan(basePackages = {"edu.wmi.dpri.przychodnia.server.entity"})
@ComponentScan(basePackages = {"edu.wmi.dpri.przychodnia.server"})
public class PrzychodniaServerApplicationRunner extends SpringBootServletInitializer {

    public static void main(String args[]) {
        SpringApplication.run(PrzychodniaServerApplicationRunner.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.profiles(System.getProperty("spring.profiles.active").split(","));
        System.out.println("Profiles loaded: " + System.getProperty("spring.profiles.active"));
        return application.sources(PrzychodniaServerApplicationRunner.class);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}
