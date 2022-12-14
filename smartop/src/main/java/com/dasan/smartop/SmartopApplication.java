package com.dasan.smartop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class SmartopApplication extends SpringBootServletInitializer{
	private static final Logger logger = LoggerFactory.getLogger(SmartopApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		return application.sources(SmartopApplication.class);
	}
	
	public static void main(String[] args) {
		logger.debug(args.toString());
		SpringApplication.run(SmartopApplication.class, args);
	}
}
