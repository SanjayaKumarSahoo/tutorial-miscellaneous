package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@EnableCouchbaseRepositories(basePackages = {"com.repository.OrderRepository"})
@Configuration
public class CouchbaseRepository {
}
