package com.xathordroid.springapitest;

import com.xathordroid.springapitest.resource.domain.RandomApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class SpringApiTestApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringApiTestApplication.class); 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringApiTestApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:)");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName: beanNames) {
				System.out.println(beanName);
			}
		};
	}
	
	@Bean
	public RestTemplate restTEmplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner runConsumingRest(RestTemplate restTemplate) throws Exception {
		return args -> {
			RandomApi randomApi = restTemplate.getForObject("https://api.publicapis.org/random?auth=null", RandomApi.class);
			log.info(randomApi.toString());
		};
	}
}
