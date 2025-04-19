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
		if (log.isDebugEnabled()) { log.debug("subscription ::: onSubscribe ::: " + subscription); }
		this.subscription = subscription;
	}

	@Override
	public void onNext(CustomMap params) {
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
	}

	@Override
	public void onError(Throwable throwable) {
		if (log.isDebugEnabled()) { log.debug("throwable ::: " + throwable); }
	}

	@Override
	public void onComplete() {
		if (log.isDebugEnabled()) { log.debug("subscription ::: onComplete ::: " + subscription); }
	}

	@Override
	public String toString() {
		return CustomMap.objectToString(this, new String[] { "log", "subscription" });
	}
}
