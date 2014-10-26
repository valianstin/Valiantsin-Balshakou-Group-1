//�������� ���������, ������� ������ ������� ���������� �� ������ ������ � �������, ��������� �� ������ ������, 
//� ������ �� ����� ������� ��������� ������ 5 ������. ������������� ����������� ������������� ���������� ������, 
//���������������� ���������, �������, ������������� �����. �� ����� ��������� � ��� ������-"����������" , �������� 
//��� ���� �����, ������� ������� �� ����� ������ ��������� ������ 7 ������. 
//�������������� ������������� ������� wait(), notifyAll().

package com.epam.task.multithreading;

import org.apache.log4j.Logger;


public class Runner {
	static final Logger logger = Logger.getLogger(Runner.class);

	public static final Object timeLocker =new Object();
	public static final Object loggerLocker =new Object();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread chronoThread= new Thread(new ChronoThread());
		chronoThread.start();
		Thread messageThread1= new Thread(new MessageThread(5));
		messageThread1.start();
		Thread messageThread2= new Thread(new MessageThread(7));
		messageThread2.start();
	}

}
