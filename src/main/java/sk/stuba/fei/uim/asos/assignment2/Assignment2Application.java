package sk.stuba.fei.uim.asos.assignment2;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sk.stuba.fei.uim.asos.assignment2.insurance.endpoint.ContractsEndPoint;
import sk.stuba.fei.uim.asos.assignment2.user.endpoint.UsersEndPoint;

import javax.xml.ws.Endpoint;

@Log
public class Assignment2Application {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext("config.xml");

        log.info("Application has started...");

        Endpoint.publish("http://localhost:8080/contracts", applicationContext.getBean(ContractsEndPoint.class));
        Endpoint.publish("http://localhost:8080/users", applicationContext.getBean(UsersEndPoint.class));

    }

}
