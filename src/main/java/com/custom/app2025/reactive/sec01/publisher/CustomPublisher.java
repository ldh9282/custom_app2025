package com.custom.app2025.reactive.sec01.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import com.custom.app2025.shared.model.CustomMap;

/**
 * <pre>
 * 클래스명: CustomPublisher
 * 설명: 커스텀 Publisher
 * </pre>
 */
public class CustomPublisher implements Publisher<CustomMap> {

	@Override
	public void subscribe(Subscriber<? super CustomMap> subscriber) {
		CustomSubscription customSubscription = new CustomSubscription();
		customSubscription.setSubscriber(subscriber);
		subscriber.onSubscribe(customSubscription);
		
	}

}
