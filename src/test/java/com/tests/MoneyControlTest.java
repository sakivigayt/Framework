package com.tests;

import org.testng.annotations.Test;

import com.drivers.DriverFactory;
import com.framework.setup.WebTest;
import com.impl.MoneyControlImpl;

public class MoneyControlTest extends WebTest{
	
	
	MoneyControlImpl moneyCntr;
	
   @Test
	public void loginTest(){
		moneyCntr=new MoneyControlImpl();
		moneyCntr.navigateToApp();
		moneyCntr.login("sapientDemo", "sapientDemo@1");
		moneyCntr.assertSuccessfullLogin();
	}
   
	
	
	@Test
	public void searchQuote(){
		moneyCntr=new MoneyControlImpl();
		moneyCntr.navigateToApp();
		moneyCntr.getQuote("GAIL");
		moneyCntr.assertStockName("GAIL India");
	}
	
	@Test
	public void searchQuoteFailed(){
		moneyCntr=new MoneyControlImpl();
		moneyCntr.navigateToApp();
		moneyCntr.getQuote("GAIL");
		moneyCntr.assertStockName("GAIL Indi");
	}
	
	
}
