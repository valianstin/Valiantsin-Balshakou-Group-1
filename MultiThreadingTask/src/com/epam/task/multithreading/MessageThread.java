package com.epam.task.multithreading;


import java.util.Date;

import org.apache.log4j.Logger;

public class MessageThread implements Runnable {
	int time;

	public MessageThread(int time) {
		super();
		this.time = time*1000;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(Runner.class);
		Date lastMessageTime=new Date();
		Date currentTime=lastMessageTime;
		try {
			while (true) {
				synchronized(Runner.loggerLocker){
					logger.info("Message from MessageThread("+time+"millisec)"+(currentTime.getTime()-lastMessageTime.getTime())+" milliseconds since last message");
				}
				lastMessageTime=new Date();
				while (currentTime.getTime()-lastMessageTime.getTime()<time){
					synchronized (Runner.timeLocker) {
						Runner.timeLocker.wait();
					}
					currentTime=new Date();
				}		
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
