package com.custom.app2025.reactive.sec01.subscriber;

import java.util.function.Supplier;

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
	private Supplier<CustomMap> data;
	
	private boolean isCancelled;
	
	private int count = 0;
	private static final int MAX_COUNT = 10;
	
	public CustomSubscription(Subscriber<? super CustomMap> subscriber, Supplier<CustomMap> data) {
		this.subscriber = subscriber;
		this.data = data;
	}
	
	@Override
	public void request(long n) {
		log.debug("CustomSubscription ::: request ::: " + n);
		if (isCancelled) {
			log.debug("CustomSubscription ::: request ::: isCancelled ::: " + isCancelled);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (count >= MAX_COUNT) {
				isCancelled = true;
				subscriber.onComplete();
				return;
			}
			
			CustomMap item = this.data.get();
			item.put("count", ++count);
			subscriber.onNext(item);
		}
		
		
	}

	@Override
	public void cancel() {
		isCancelled = true;
		log.debug("CustomSubscription ::: isCancelled ::: " + isCancelled);
	}
	

}
