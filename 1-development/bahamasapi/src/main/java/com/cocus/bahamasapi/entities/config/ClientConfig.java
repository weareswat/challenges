package com.cocus.bahamasapi.entities.config;

import com.cocus.bahamasapi.entities.model.Client;
import com.cocus.bahamasapi.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository repository) {
        return args -> {
            Client mariam = new Client(1L, 1, "Mariam", "mariam@gmail.com");

            Client alex = new Client(2L, 2, "Alex", "alex@gmail.com");

            repository.saveAll(List.of(mariam, alex));
        };
    }
}
