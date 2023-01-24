package com.mjc.school;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ={
        "com.mjc.school.service",
        "com.mjc.school.repository",
        "com.mjc.school.controller",
        "com.mjc.school"})
public class Main {
    ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

}
