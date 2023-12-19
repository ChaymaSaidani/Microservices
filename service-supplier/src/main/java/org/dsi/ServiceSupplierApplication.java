package org.dsi;

import org.dsi.entity.Supplier;
import org.dsi.repo.SupplierRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServiceSupplierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSupplierApplication.class, args);
	}
	
	@Bean
    CommandLineRunner start(SupplierRepo supplierRepo){
        return args -> {
        	supplierRepo.save(new Supplier(null,"user","user@gmail.com"));
        	supplierRepo.save(new Supplier(null,"user","user@gmail.com"));
        	supplierRepo.save(new Supplier(null,"user","user@gmail.com"));
        };
    }
	}

