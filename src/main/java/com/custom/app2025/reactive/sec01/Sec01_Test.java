package com.custom.app2025.reactive.sec01;

import com.custom.app2025.reactive.sec01.publisher.CustomPublisher;
import com.custom.app2025.reactive.sec01.subscriber.CustomSubscriber;

public class Sec01_Test {

	public static void main(String[] args) {
		CustomPublisher customPublisher = new CustomPublisher();
		CustomSubscriber customSubscriber = new CustomSubscriber();
		
		customPublisher.subscribe(customSubscriber);
		
		customSubscriber.getSubscription().request(3);
//		customSubscriber.getSubscription().cancel();
		customSubscriber.getSubscription().request(3);
		customSubscriber.getSubscription().request(3);
		customSubscriber.getSubscription().request(3);
		customSubscriber.getSubscription().request(3);
	}
}
