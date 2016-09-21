//package edu.wmi.dpri.przychodnia.server.config.hibernate;
//
//import org.hibernate.SessionFactory;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
///**
// * Created by lupus on 19.09.16.
// */
//public class HibernateProvider {
//
//    private Environment env;
//
//    public HibernateProvider(Environment env) {
//        this.env = env;
//    }
//
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
//        lsfb.setDataSource(dataSource());
//        lsfb.setPackagesToScan("edu.wmi.dpri.przychodnia.server.entity");
//        lsfb.setHibernateProperties(hibernateProperties());
//        return lsfb;
//    }
//
//    public DataSource dataSource() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e1) {
//            e1.printStackTrace();
//        }
//        DriverManagerDataSource dmds = new DriverManagerDataSource();
//        dmds.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
//        dmds.setUrl(env.getRequiredProperty("spring.datasource.url"));
//        dmds.setUsername(env.getRequiredProperty("spring.datasource.username"));
//        dmds.setPassword(env.getRequiredProperty("spring.datasource.password"));
//        return dmds;
//    }
//
//    private Properties hibernateProperties() {
//        Properties p = new Properties();
//        p.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//        p.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
//        p.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
//        return p;
//    }
//
//    public HibernateTransactionManager transactionManager(SessionFactory s) {
//        HibernateTransactionManager htm = new HibernateTransactionManager();
//        htm.setSessionFactory(s);
//        return htm;
//    }
//
//    public EntityManagerFactory entityManagerFactory() {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("edu.wmi.dpri.przychodnia.server.entity");
//        factory.setDataSource(dataSource());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
//}
