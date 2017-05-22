package com.framework.setup;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.drivers.DriverConfig;
import com.drivers.DriverFactory;

public class FrameworkListener implements IInvokedMethodListener {

	DriverConfig config;



	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		config=new DriverConfig();
		config.setBrowserName(method.getTestMethod().getXmlTest().getParameter("browser"));
		DriverFactory.initiateDriver();
		DriverFactory.setDriverConfig(config);

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
		DriverFactory.quitDriver();
		
	}

}
