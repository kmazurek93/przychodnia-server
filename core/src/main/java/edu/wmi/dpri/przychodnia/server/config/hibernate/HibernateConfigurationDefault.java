//package edu.wmi.dpri.przychodnia.server.config.hibernate;
//
///**
// * Created by lupus on 14.09.16.
// */
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.*;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource(value = {"classpath:application.properties"})
//@ComponentScan({"edu.wmi.dpri.przychodnia.server"})
//@EnableJpaRepositories(basePackages = {"edu.wmi.dpri.przychodnia.server.repository"})
//@Profile("default")
//public class HibernateConfigurationDefault {
//    private HibernateConfiguration configuration;
//
//    @Autowired
//    public HibernateConfigurationDefault(Environment env) {
//        configuration = new HibernateConfiguration(env);
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        return configuration.sessionFactory();
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        return configuration.dataSource();
//    }
//
//    @Bean
//    public Properties hibernateProperties() {
//        return configuration.hibernateProperties();
//    }
//
//    @Bean
//    @Autowired
//    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
//        return configuration.transactionManager(sessionFactory);
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        return configuration.entityManagerFactory();
//    }
//
//}
