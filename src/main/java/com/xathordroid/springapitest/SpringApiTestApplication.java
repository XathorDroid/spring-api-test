package com.xathordroid.springapitest;

import com.xathordroid.springapitest.entity.Customer;
import com.xathordroid.springapitest.entity.Employee;
import com.xathordroid.springapitest.repository.CustomerRepository;
import com.xathordroid.springapitest.repository.EmployeeRepository;
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
	
	private static final boolean ENABLE_COMMAND_LINE_RUNNER_LIST_BEANS = false;
	private static final boolean ENABLE_COMMAND_LINE_RUNNER_CONSUME_REST = false;
	private static final boolean ENABLE_COMMAND_LINE_RUNNER_DEMO_JPA_1 = false;
	private static final boolean ENABLE_COMMAND_LINE_RUNNER_DEMO_JPA_2 = true;

	private static final Logger log = LoggerFactory.getLogger(SpringApiTestApplication.class); 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringApiTestApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		if (ENABLE_COMMAND_LINE_RUNNER_LIST_BEANS) 
			return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:)");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName: beanNames) {
				System.out.println(beanName);
			}
		};
		else
			return null;
	}
	
	@Bean
	public RestTemplate restTEmplate(RestTemplateBuilder builder) {
		if (ENABLE_COMMAND_LINE_RUNNER_CONSUME_REST) 
			return builder.build();
		else 
			return null;
	}
	
	@Bean
	public CommandLineRunner runConsumingRest(RestTemplate restTemplate) throws Exception {
		if (ENABLE_COMMAND_LINE_RUNNER_CONSUME_REST) 
			return args -> {
			RandomApi randomApi = restTemplate.getForObject("https://api.publicapis.org/random?auth=null", RandomApi.class);
			log.info(randomApi.toString());
		};
		else
			return null;
	}
	
	@Bean
	public CommandLineRunner demoJpa(CustomerRepository repository) {
		if (ENABLE_COMMAND_LINE_RUNNER_DEMO_JPA_1) 
			return args -> {
			// save new customers in DB
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			
			// load all customers from DB
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");
			
			// load a specific customer from DB by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("---------------------------------");
			log.info(customer.toString());
			log.info("");
			
			// load customers from DB by lastName
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
		else
			return null;
	}
	
	@Bean
	public CommandLineRunner initDatabase(EmployeeRepository repository) {
		if (ENABLE_COMMAND_LINE_RUNNER_DEMO_JPA_2)
			return args -> {
				log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
				log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
			};
		else
			return null;
	}
}
