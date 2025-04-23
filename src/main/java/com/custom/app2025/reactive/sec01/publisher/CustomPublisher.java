package com.custom.app2025.reactive.sec01.publisher;

import java.util.function.Supplier;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import com.custom.app2025.reactive.sec01.subscriber.CustomSubscriber;
import com.custom.app2025.reactive.sec01.subscriber.CustomSubscription;
import com.custom.app2025.shared.model.CustomMap;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: CustomPublisher
 * 설명: 커스텀 Publisher
 * </pre>
 */
@Log4j2
public class CustomPublisher implements Publisher<CustomMap> {

	private Supplier<CustomMap> data;
	public CustomPublisher(Supplier<CustomMap> data) {
		this.data = data;
	}
	@Override
	public void subscribe(Subscriber<? super CustomMap> subscriber) {
		CustomSubscription customSubscription = new CustomSubscription(subscriber, data);
		subscriber.onSubscribe(customSubscription);
		
	}
	
	public static void main(String[] args) {
		// 공급할 데이터
		Supplier<CustomMap> data = () -> {
			CustomMap map = CustomMap.builder().put("value", Math.random()).build();
			log.debug("공급할 데이터 ::: " + map);
			return map;
		};
		// 퍼블리셔 (데이터 생산자)
		CustomPublisher customPublisher = new CustomPublisher(data);
		// 구독자 (데이터 소비자)
		CustomSubscriber customSubscriber = new CustomSubscriber();
		
		customPublisher.subscribe(customSubscriber);
		
		customSubscriber.getSubscription().request(3); // 요청을 통해 onNext 실행
//		customSubscriber.getSubscription().cancel();
		customSubscriber.getSubscription().request(3);
		customSubscriber.getSubscription().request(3);
		customSubscriber.getSubscription().request(3); // MAX_COUNT 도달 후 Complete
		customSubscriber.getSubscription().request(3); // Complete 이후 onNext 실행 안함
	}

}
