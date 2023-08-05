package com.example.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
	
	@Autowired
	KafkaTemplate<String, String> template;
	
	@Value(value="${apache.kafka.topic.name}")
	private String apacheKafkaTopicName;
	
	public void sendMessage(String message) {
		
		//we can check the callback 
		template.send(apacheKafkaTopicName, message);
		
	}

}
