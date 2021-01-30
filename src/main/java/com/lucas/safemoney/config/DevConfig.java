package com.lucas.safemoney.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucas.safemoney.services.instantiates.DevInstantiate;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DevInstantiate dev;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiate() {
		if(!"create".equals(strategy)) {
			return false;
		}

		dev.instantiate();
		return true;
	}
}
