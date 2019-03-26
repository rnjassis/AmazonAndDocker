package com.self.amazon.persistence.mongo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = { "com.self.amazon.persistence.mongo.entity",
		"com.self.amazon.persistence.mongo.repository" }, mongoTemplateRef = "template1")
public class MongoDBConfiguration {

	@Value("${spring.mongodb-remote.host}")
	private String host;

	@Value("${spring.mongodb-remote.port}")
	private int port;

	@Value("${spring.mongodb-remote.collection}")
	private String collection;
	
	@Bean(name = "template1")
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(editalFactory());
	}

	@Bean
	public MongoDbFactory editalFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(host, port), collection);
	}

}
