package com.epam.task.classloader;

import java.net.MalformedURLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.task.classloader.functionality.Function;

//Create console application for dynamic reloading of existing development functionality. 
//Existing funcionality should be placed in specifed directory and should be archived in jar. 
//The application should have console menu for choosing option, the output should be done through usage of log4j library. 

public class App {

	static final Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, MalformedURLException {
		int result = 0;
		while (true) {
			try {
				logger.info("choose implementation");
				Scanner sc = new Scanner(System.in);
				int number = sc.nextInt();

				ClassLoader loader = new IncrementClassLoader(
						"F:/test/incrementImpl.jar",
						ClassLoader.getSystemClassLoader());
				Class clazz = Class.forName(
						"com.epam.task.classloader.functionality.IncrementImpl"
								+ number, true, loader);

				Function fun = (Function) clazz.newInstance();
				result = fun.increment(result);
				logger.info("result is:"+result);
			} catch (ClassNotFoundException e) {
				logger.info("Class not found");
			}
		}
	}
}
