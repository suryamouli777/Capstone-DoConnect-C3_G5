package com.wipro.gl.doconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * Author : B. Surya Mouli
 * Modified date : 24-08-2022 
 * Description : Spring application is initialized 
 * 
 */


@SpringBootApplication
public class DoConnectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoConnectBackendApplication.class, args);
	}

	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
