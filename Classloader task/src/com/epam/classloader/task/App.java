package com.epam.classloader.task;
//Create console application for dynamic reloading of existing development functionality. 
//Existing funcionality should be placed in specifed directory and should be archived in jar. 
//The application should have console menu for choosing option, the output should be done through usage of log4j library. 

public class App {

	public static void main(String[] args) {
		for (; ; ) {
            ClassLoader loader = new TestClassLoader();
            Class clazz = Class.forName("com.epam.modules.TestModuleImpl", true, loader);
            TestModule object = (TestModule) clazz.newInstance();
            logger.info(object);
            TestModuleWrapper wrapper = new TestModuleWrapper(object);
            wrapper.hello();
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }

	}

}
