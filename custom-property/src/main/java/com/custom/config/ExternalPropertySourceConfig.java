package com.custom.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ExternalPropertySourceConfig {


    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Properties extProperties = new Properties();
        extProperties.setProperty("spring.datasource.url", "jdbc:h2:mem:test;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        extProperties.setProperty("spring.datasource.username", "sa");
        extProperties.setProperty("spring.datasource.password", "");
        extProperties.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
        extProperties.setProperty("url", "someurl");
        ppc.setProperties(extProperties);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}
