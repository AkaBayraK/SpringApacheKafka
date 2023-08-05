package com.example.entities;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	@KafkaListener(topics="${apache.kafka.topic.name}", groupId="group1")
	public void listenMessage(String message) {
		System.out.println(message);
	}
}
