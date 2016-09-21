package edu.wmi.dpri.przychodnia.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
/**
 * Created by lupus on 21.09.16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"edu.wmi.dpri.przychodnia.server.repository"})
@EntityScan(basePackages = {"edu.wmi.dpri.przychodnia.server.entity"})
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
}
