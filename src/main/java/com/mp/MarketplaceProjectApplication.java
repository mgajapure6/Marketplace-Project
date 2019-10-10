package com.mp;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = { "com.mp" })
public class MarketplaceProjectApplication extends SpringBootServletInitializer {

	@Autowired
	ServletContext context;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MarketplaceProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceProjectApplication.class, args);
	}

}
