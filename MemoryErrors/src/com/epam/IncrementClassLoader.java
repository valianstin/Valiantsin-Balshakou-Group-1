package com.epam;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

public class IncrementClassLoader extends ClassLoader {
	final Logger logger = Logger.getLogger(IncrementClassLoader.class);

	private String path;

	public IncrementClassLoader(String path, ClassLoader parent) {
		super(parent);
		this.path = path;
	}

	protected Class findClass(final String name) throws ClassNotFoundException {

		try {
			JarFile jarFile = new JarFile(path);
			JarEntry jarEntry = jarFile.getJarEntry(name.replace(".", "/")+".class");
			byte[] classBytes = loadClassData(jarFile, jarEntry);
			return defineClass(name, classBytes, 0, classBytes.length);

		} catch (IOException e) {
			throw new ClassNotFoundException(
					"I/O problems detected" + name + ": "
							+ e);
		} catch (ClassFormatError e) {
			throw new ClassNotFoundException(
					"Format of class file incorrect for class " + name + ": "
							+ e);
		} catch(NullPointerException e){
			throw new ClassNotFoundException(
					"Class doesn't exist in jar" + name + ": "
							+ e);
		}
	}

	private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry)
			throws IOException {

		long size = jarEntry.getSize();
		if (size == -1 || size == 0)
			return null;
		byte[] data = new byte[(int) size];
		InputStream in = jarFile.getInputStream(jarEntry);
		in.read(data);
		return data;

	}
}
