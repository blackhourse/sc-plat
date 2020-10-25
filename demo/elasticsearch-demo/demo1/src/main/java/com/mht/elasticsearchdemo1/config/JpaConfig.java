package com.mht.elasticsearchdemo1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.mht.elasticsearchdemo1.repository")
@Configuration
public class JpaConfig {
}
