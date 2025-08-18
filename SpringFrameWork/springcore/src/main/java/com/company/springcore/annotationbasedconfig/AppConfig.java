package com.company.springcore.annotationbasedconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration 
@ComponentScan(basePackages = "com.company.springcore.xmlbasedconfig,com.company.springcore.annotationbeans")
public class AppConfig {

}