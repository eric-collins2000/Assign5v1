package com.example.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
/**
 * spring.jpa.hibernate.ddl-auto=update
 * spring.datasource.url=jdbc:mysql://cs3743.fulgentcorp.com:3306/cs4743_ivq495
 * spring.datasource.username=ivq495
 * spring.datasource.password=tj6obQfixEERkhqFg3KM
 * hibernate.dialect= org.hibernate.dialect.MySQLDialect
 * spring.datasource.driver-class-name =com.mysql.jdbc.Driver
 * #spring.jpa.show-sql: true
 *
 * spring.jpa.hibernate.ddl-auto=update
 * spring.datasource.url=jdbc:mysql://cs3743.fulgentcorp.com:3306/cs4743_ivq495
 * spring.datasource.username=ivq495
 * spring.datasource.password=tj6obQfixEERkhqFg3KM
 * spring.datasource.driver-class-name =com.mysql.jdbc.Driver
 * #spring.jpa.show-sql: true
 */