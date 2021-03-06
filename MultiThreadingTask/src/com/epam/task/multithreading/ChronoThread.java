package com.epam.task.multithreading;

import org.apache.log4j.Logger;

public class ChronoThread implements Runnable {
	Logger logger = Logger.getLogger(Runner.class);
	@Override
	public void run() {
		
		int time = 0;
		try {
			while (true) {
				synchronized(Runner.loggerLocker){
					logger.info(time+" seconds from start(chronoThread)");
				}
				Thread.sleep(1000);
				time++;
				synchronized(Runner.timeLocker){
					Runner.timeLocker.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
