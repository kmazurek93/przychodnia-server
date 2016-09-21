//package edu.wmi.dpri.przychodnia.server.config.hibernate;
//
///**
// * Created by lupus on 14.09.16.
// */
//
//import org.hibernate.SessionFactory;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//public class HibernateConfiguration {
//
//    private Environment env;
//
//    public HibernateConfiguration(Environment env) {
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
//    public Properties hibernateProperties() {
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
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean emfb =   new LocalContainerEntityManagerFactoryBean() ;
//        emfb.setDataSource(dataSource());
//        emfb.setPackagesToScan("edu.wmi.dpri.przychodnia.server.entity");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() ;
//        emfb.setJpaVendorAdapter(vendorAdapter);
//        return emfb ;
//    }
//}
