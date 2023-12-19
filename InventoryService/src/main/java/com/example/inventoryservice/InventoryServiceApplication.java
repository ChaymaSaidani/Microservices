package com.example.inventoryservice;

import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig(config -> {
            config.exposeIdsFor(Inventory.class);
        });
    }

    @Bean
    CommandLineRunner start(InventoryRepository inventroyRepository){
        return args -> {
            inventroyRepository.save(new Inventory(null,"Computer Desk Top DELL G15",900));
            inventroyRepository.save(new Inventory(null,"Printer DELL",80));
            inventroyRepository.save(new Inventory(null,"MacBook Pro lap Top",1800));
            inventroyRepository.findAll().forEach(System.out::println);
        };
    }

}
