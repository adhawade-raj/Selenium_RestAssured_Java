package com.test.runner;

import com.google.threadclasses.GoogleFeatureThread;

public class GoogleTestRunner {

	public static void main(String[] args) {

Thread t1 = new GoogleFeatureThread("chrome thread", "chrome");
Thread t2 = new GoogleFeatureThread("chrome thread", "FireFox");

t1.start();
t2.start();
	}

}
