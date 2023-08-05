package com.example;

import java.util.Date;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.entities.Publisher;

@SpringBootApplication
public class SpringApacheKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApacheKafkaApplication.class, args);
	}

    @Bean
    public ApplicationRunner runner(Publisher p) {
        return args -> {
        	p.sendMessage("Hello Apache Kafka : "+new Date().toGMTString());
        };
    }
    
}
