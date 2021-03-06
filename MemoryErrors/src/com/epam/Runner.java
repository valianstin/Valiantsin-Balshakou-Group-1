package com.epam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

//Design and implement code that will introduce:
//1. java.lang.OutOfMemoryError: Java heap space. Create big objects continuously and make them stay in memory.
//2. java.lang.OutOfMemoryError: PermGen space. Load classes continuously and make them stay in memory.

public class Runner {
	static final Logger logger = Logger.getLogger(Runner.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("\nchoose implementation:OOM\n1-permGen OOM\n2-heapSpace OOM");
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		if (number == 1) {
			OutOfMemoryErrorPermGenSpace();
		} else
			OutOfMemoryErrorJavaHeapSpace();
	}

	public static void OutOfMemoryErrorJavaHeapSpace() {
		List<Integer> list = new ArrayList<Integer>();
		Integer i;
		while (true) {
			i = new Integer(6);
			list.add(i);
		}
	}

	public static void OutOfMemoryErrorPermGenSpace() {
		List<Object> list = new ArrayList<Object>();
		ClassLoader loader;
		Class clazz;
		try {
			while (true) {
				loader = new IncrementClassLoader("F:/test/incrementImpl.jar",
						ClassLoader.getSystemClassLoader());
				clazz = Class
						.forName(
								"com.epam.task.classloader.functionality.IncrementImpl1",
								true, loader);
				list.add(clazz);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}