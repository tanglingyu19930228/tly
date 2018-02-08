package com.tly03;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo3 {
	public static void main(String[] args) {
		Runnable runnable=new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello!!");
				
			}
		};
		ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
	}

}
