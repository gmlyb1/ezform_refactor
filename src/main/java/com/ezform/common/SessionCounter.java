package com.ezform.common;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionCounter {

	private static final AtomicInteger count = new AtomicInteger(0);
	
	public static int increment() {
		return count.incrementAndGet();
	}
	
	public static int decrement() {
		return count.decrementAndGet();
	}
	
	public static int getCount() {
		return count.get();
	}
}
