package com.custom.app2025.reactive.sec02;

import java.util.List;
import java.util.stream.Stream;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.custom.app2025.shared.model.CustomMap;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LazyStream {

	public static void main(String[] args) {
		List<CustomMap> list = Stream.of(CustomMap.builder().set("value", Math.random()).build())
				.peek(Item -> {
					if (log.isDebugEnabled()) { log.debug("item ::: " + Item); }
				})
				.toList();
		CustomMap result = CustomMap.builder()
				.set("value", Math.random())
				.set("value2", Math.random())
				.build();
		System.out.println(list);
	}
}
