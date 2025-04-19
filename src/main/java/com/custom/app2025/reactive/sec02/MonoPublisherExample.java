package com.custom.app2025.reactive.sec02;

import com.custom.app2025.reactive.sec01.subscriber.CustomSubscriber;
import com.custom.app2025.shared.model.CustomMap;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * 클래스명: MonoPublisherExample
 * 설명: 퍼블리셔 중 하나인 모노의 예제
 * 
 * 모노는 0개 도는 1개를 emit 을 하는 퍼블리셔
 * </pre>
 */
public class MonoPublisherExample {

	public static void main(String[] args) {
		// Mono.just 정확히 1개 emit (onSubscribe -> onNext 1회 -> onComplete)
		Mono<CustomMap> monoPublisher = Mono.just(CustomMap.builder().set("value", Math.random()).build());
		boolean isEmpty = false;
		if (isEmpty) { // 필요시 0개 emit 으로 중간에 로직 변경도 가능 (onSubscribe -> onComplete)
			monoPublisher = Mono.empty();
		}
		CustomSubscriber subscriber = new CustomSubscriber();
		
		monoPublisher.subscribe(subscriber);
		
		// 10 번 실행해도 monoPublisher는 onNext 한번만 실행 (2개 이상 emit 불가능)
		// Mono.empty 로 변경되었을경우 emit 0개, onNext 실행하지 않음
		subscriber.getSubscription().request(10);
	}
}
