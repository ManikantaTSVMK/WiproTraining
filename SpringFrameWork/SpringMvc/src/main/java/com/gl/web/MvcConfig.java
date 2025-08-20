package com.gl.web;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
@ComponentScan("com.gl.web")
public class MvcConfig extends WebMvcConfigurerAdapter
{
	@Bean
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/wiprojdbc");
		dataSource.setUsername("root");
		dataSource.setPassword("Mani@2002");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate() 
	{
		JdbcTemplate jdbcTemplate= new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());//setter based di
		
	   return jdbcTemplate;
	}
	
	@Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/"); // Specifies the directory where views are located
        resolver.setSuffix(".jsp");          // Specifies the file extension of the views
        return resolver;
    }
	
  
}
