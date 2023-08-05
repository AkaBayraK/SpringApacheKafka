package com.example.configurations;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class ApacheKafkaTopicConfig {
	
	@Value(value="${apache.kafka.bootstrap-server.address}")
	private String apacheKafkaServerAddress;
	
	@Bean
	public KafkaAdmin apacheKafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, apacheKafkaServerAddress);
		return new KafkaAdmin(configs);
	}
	
	@Bean
	public NewTopic newTopicCreate() {
		return new NewTopic("apacheKafkaAppTopic", 1, (short) 1);
	}
	
}
