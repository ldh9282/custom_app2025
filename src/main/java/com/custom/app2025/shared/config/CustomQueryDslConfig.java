package com.custom.app2025.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class CustomQueryDslConfig {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Bean
	public JPAQueryFactory queryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}
