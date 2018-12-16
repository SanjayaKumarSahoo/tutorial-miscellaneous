package com.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = { "com.example.elastic.repository" })
@ComponentScan(basePackages = {"com.example.elastic.service"})
public class ElasticSearchConfiguration {
	
}
