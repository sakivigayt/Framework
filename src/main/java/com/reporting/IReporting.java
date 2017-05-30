package com.reporting;

public interface IReporting {
	
	public  void logPass(String details);
		
	public void logFail(String details);
	
	public void logDetail (String detail);
	
	public void verify(boolean condition,String detail);

	public void assertTrue(boolean condition,String detail);
	

}
