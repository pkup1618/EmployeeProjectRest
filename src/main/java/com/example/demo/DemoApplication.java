package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import com.example.demo.filters.LoggingFilter;




@SpringBootApplication
@EnableJdbcRepositories
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    public Logger logger(){
        return LoggerFactory.getLogger(DemoApplication.class.getName());
    }

	@Bean
	public FilterRegistrationBean requestResponseFilter() {
		final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();

		LoggingFilter filter = new LoggingFilter();

		filterRegBean.setFilter(filter);

		filterRegBean.addUrlPatterns("/*");
		
		filterRegBean.setName("Logging Filter");
		filterRegBean.setAsyncSupported(Boolean.TRUE);

		return filterRegBean;
	}

}