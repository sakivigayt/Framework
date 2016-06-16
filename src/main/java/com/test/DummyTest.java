package com.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jcabi.aspects.Loggable;
import com.log.LoggerFactory;
import com.log.MyLogger;

public class DummyTest {
	private static final MyLogger LOGGGER = LoggerFactory.getLogger(DummyTest.class);

	/*@BeforeMethod
	public void beforeTest() {
		ThreadContext.put("logFileName", Thread.currentThread().getName());
	}

	@AfterMethod
	public void afterTest() {
		ThreadContext.remove("logFileName");
	}*/

	@Test
	public void hello(){
		System.out.println("test");
	}
}
