package com.tly03;

import java.util.Timer;
import java.util.TimerTask;

public class Demo2 {
	public static void main(String[] args) {
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
             System.out.println("Hello!!");				
			}
		};
		Timer timer=new Timer();
		long delay=10000;
		long intevalPeriod=1*1000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}

}
