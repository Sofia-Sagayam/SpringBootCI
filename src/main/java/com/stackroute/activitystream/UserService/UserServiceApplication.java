package com.stackroute.activitystream.UserService;


import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication(scanBasePackages={"com.stackroute.activitystream.UserService"})
@EntityScan(basePackages={"com.stackroute.activitystream.UserService"})
public class UserServiceApplication {
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory) {
	    return hibernateEntityManagerFactory.getSessionFactory();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	

}
