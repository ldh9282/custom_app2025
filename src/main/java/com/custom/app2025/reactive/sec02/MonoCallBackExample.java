package com.custom.app2025.reactive.sec02;

import com.custom.app2025.shared.model.CustomMap;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * 클래스명: MonoCallBackExample
 * 설명: mono 람다 콜백함수 활용
 * </pre>
 */
public class MonoCallBackExample {

	public static void main(String[] args) {
		// 테스트 데이터
		System.out.println("1. Start");
		CustomMap result = CustomMap.builder()
				.put("value", Math.random())
				.build();
		
		Mono.just(result).subscribe(item -> { // subscriber 대신에 람다 콜백함수를 넘겼을때 실행이 된다 (LambdaMonoSubscriber)
			System.out.println("onNext : " + item);
		});
		
		// 이때 request 요청 없이도 실행됨
		System.out.println("1. End");
		
		
		System.out.println("2. Start");
		// 람다를 2개 전달할때 첫번째 람다는 onNext 정상 콜백이고, 2번째 람다는 onError 에러 콜백
		Mono.just(result).map((item) -> {
				item.put("value2", 100 / 0);
				return item;
			})
			.subscribe(item -> {
				System.out.println("onNext : " + item);
			}, e -> {
				System.out.println("onError");
				e.printStackTrace();
			});
		System.out.println("2. End");
		
		System.out.println("3. Start");
		// 람다를 3개 전달할때 첫번째 람다는 onNext 정상 콜백이고, 2번째 람다는 onError 에러 콜백, 3번째 람다는 onComplete 완료 콜백
		Mono.just(result).log().subscribe(item -> { // 리액터 내부 signal 흐름을 보려면 log() 이용
//		Mono.just(result).subscribe(item -> {
			System.out.println("onNext : " + item);
		}, e -> {
			System.out.println("onError");
			e.printStackTrace();
		}, () -> {
			System.out.println("onComplete");
		});
		System.out.println("3. End");
		
		System.out.println("4. Start");
		
		// 콜백을 체인 방식으로도 활용가능
		Mono.just(result)
//		Mono.empty()
			.doOnNext(item -> {
				System.out.println("onNext : " + item);
			})
			.doOnError(e -> {
				System.out.println("onError");
				e.printStackTrace();
			})
			.doOnSuccess(item -> { // onNext 와 차이점은 emit 0개일때도 실행됨
				System.out.println("doOnSuccess : " + item);
			})
			.subscribe() // 이게 있어야 실행됨
			;
		
		System.out.println("4. End");
		
		
		
	}
}
