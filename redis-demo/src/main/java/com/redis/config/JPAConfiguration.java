package com.redis.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EntityScan(basePackages = {"com.redis.model"})
@EnableJpaRepositories(basePackages = {"com.redis.repository.jpa"})
@Configuration
public class JPAConfiguration {
}
