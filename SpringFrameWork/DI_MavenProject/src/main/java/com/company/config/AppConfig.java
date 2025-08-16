package com.company.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Spring will auto cofigured and will look the component in which package that we need to define


/*@Configuration is 
 * Indicating that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime, for example:

 @Configuration
 public class AppConfig {

     @Bean
     public MyBean myBean() {
         // instantiate, configure and return bean ...
     }
 }
 
 and @ComponentScan is for to tell the spring container where all bean classes are in which packages
 */
@Configuration 
@ComponentScan(basePackages = "com.company.beans")
public class AppConfig {
	

}