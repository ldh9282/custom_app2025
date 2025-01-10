package com.custom.app2025.shared.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: CustomJpaAspect
 * 설명: JPA 로깅을 위한 Aspect
 * </pre>
 */
@Aspect
@Component @Log4j2
public class CustomJpaAspect {

	@Before("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))"
			+ " or " + "execution(* com.querydsl.jpa.impl.JPAQueryFactory.*(..))"
			)
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
        	if (log.isDebugEnabled()) { log.debug("바인딩 파라미터 : " + arg); }
        }
    }
}
