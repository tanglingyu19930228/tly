package com.tly03;

public class Demo1 {
	public static void main(String[] args) {
		final long timeInterval = 1000;
		Runnable r = new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("Hello!!");
					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(r);
		thread.start();
	}

}
