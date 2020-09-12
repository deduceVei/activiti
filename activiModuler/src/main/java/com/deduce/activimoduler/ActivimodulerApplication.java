package com.deduce.activimoduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.activiti.spring.boot.SecurityAutoConfiguration.class})
public class ActivimodulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivimodulerApplication.class, args);
    }

}
