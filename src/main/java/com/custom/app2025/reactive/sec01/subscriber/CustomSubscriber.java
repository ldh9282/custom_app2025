package com.custom.app2025.reactive.sec01.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.custom.app2025.shared.model.CustomMap;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: CustomSubscriber
 * 설명: 커스텀 Subscriber
 * </pre>
 */
@Log4j2
public class CustomSubscriber implements Subscriber<CustomMap> {

	private Subscription subscription;
	
	public Subscription getSubscription() {
		return subscription;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		log.debug("subscription ::: onSubscribe ::: " + subscription);
		this.subscription = subscription;
	}

	@Override
	public void onNext(CustomMap item) {
		log.debug("item ::: " + item);
	}

	@Override
	public void onError(Throwable throwable) {
		log.debug("throwable ::: " + throwable);
	}

	@Override
	public void onComplete() {
		log.debug("subscription ::: onComplete ::: " + subscription);
	}

}
