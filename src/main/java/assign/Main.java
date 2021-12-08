package assign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"assign"})
@EntityScan("assign.Models")
@EnableJpaRepositories("assign.Repo")
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
 *
 * spring.dataSource.driver-class-name =com.mysql.cj.jdbc.Driver
 * spring.jpa.hibernate.ddl-auto=update
 * spring.dataSource.url=jdbc:mysql://cs3743.fulgentcorp.com:3306/cs4743_ivq495
 * spring.dataSource.username=ivq495
 * spring.dataSource.password=tj6obQfixEERkhqFg3KM
 * hibernate.dialect= org.hibernate.dialect.MySQLDialect
 * spring.jpa.show-sql: true
 *
 * module-info
 *
 *
 module assign.Main {
 requires javafx.controls;
 requires javafx.fxml;
 requires org.apache.logging.log4j;
 requires org.json;
 requires spring.beans;
 requires org.hibernate.orm.core;
 requires spring.context;
 requires spring.test;
 requires spring.web;
 requires java.persistence;
 requires spring.boot.autoconfigure;
 requires spring.boot;
 requires spring.data.jpa;
 requires spring.data.commons;
 requires spring.data.rest.core;
 requires spring.core;
 requires org.apache.httpcomponents.httpcore;
 requires org.apache.httpcomponents.httpclient;


 exports assign.Gateways;
 opens assign.Gateways to spring.core, javafx.fxml;
 exports assign.DocRobsStuff;
 opens assign.DocRobsStuff to spring.core, javafx.fxml;
 exports assign.DataModels;
 opens assign.DataModels to spring.core, javafx.fxml;
 exports assign.Session;
 opens assign.Session to spring.core, javafx.fxml;
 exports assign.Repo;
 opens assign.Repo to spring.core, javafx.fxml;
 exports assign.Models;
 opens assign.Models to javafx.fxml, spring.core;
 exports assign;
 opens assign to javafx.fxml, spring.core;
 }
 module app {
 // transitively requires javafx.base and javafx.graphics
 requires javafx.controls;
 requires org.apache.logging.log4j;
 requires org.json;
 requires org.apache.httpcomponents.httpcore;
 requires org.apache.httpcomponents.httpclient;
 requires java.persistence;
 requires spring.data.commons;
 requires spring.data.rest.core;
 requires spring.core;
 requires spring.data.jpa;
 requires spring.boot;
 requires spring.boot.autoconfigure;
 requires spring.context;
 requires javafx.fxml;
 requires spring.beans;
 requires spring.test;
 requires spring.web;

 // export javafx.application.Application implementation's package
 // to at least javafx.graphics
 exports assign;// to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 opens assign to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 exports assign.Models;// to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 opens assign.Models to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 exports assign.Repo;// to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 opens assign.Repo to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 exports assign.Gateways;
 opens assign.Gateways to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 exports assign.DocRobsStuff;
 opens assign.DocRobsStuff to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 exports assign.DataModels;
 opens assign.DataModels to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 exports assign.Session;
 opens assign.Session to spring.core, spring.beans, spring.context, spring.web, spring.test, spring.boot.autoconfigure, spring.boot, spring.data.jpa, spring.data.rest.core, spring.data.commons, java.persistence, org.apache.httpcomponents.httpclient, org.apache.httpcomponents.httpcore, org.json, org.apache.logging.log4j, javafx.controls;
 }
 */