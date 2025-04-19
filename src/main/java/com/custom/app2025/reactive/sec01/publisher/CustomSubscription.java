package com.custom.app2025.reactive.sec01.publisher;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.custom.app2025.shared.model.CustomMap;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: CustomSubscription
 * 설명: 커스텀 Subscription
 * </pre>
 */
@Log4j2
public class CustomSubscription implements Subscription {

	private Subscriber<? super CustomMap> subscriber;
	private boolean isCancelled;
	
	private static final int MAX_ITEMS = 10;
	private int count;
	
	public void setSubscriber(Subscriber<? super CustomMap> subscriber) {
		this.subscriber = subscriber;
		this.count = 0;
	}

	@Override
	public void request(long n) {
		if (isCancelled) {
			if (log.isDebugEnabled()) { log.debug("CustomSubscription :::  request ::: isCancelled ::: " + isCancelled); }
			return;
		}
		if (log.isDebugEnabled()) { log.debug("CustomSubscription :::  request ::: " + n); }
		
		for (int i = 0; i < n && count < MAX_ITEMS; i++) {
			count++;
			CustomMap params = new CustomMap();
			params.put("count", count);
			
			subscriber.onNext(params);
		}
		
		if (count == MAX_ITEMS) {
			if (log.isDebugEnabled()) { log.debug("count :::  MAX_ITEMS ::: " + count); }
			subscriber.onComplete();
			isCancelled = true;
			return;
		}
	}

	@Override
	public void cancel() {
		isCancelled = true;
		if (log.isDebugEnabled()) { log.debug("CustomSubscription :::  isCancelled ::: " + isCancelled); }
	}
	
	@Override
	public String toString() {
		return CustomMap.objectToString(this, new String[] { "log", "subscriber" });
	}
	

}
