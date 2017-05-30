package com.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectClass {
	
	@Pointcut("execution(public * *(..))") 
	private void detailedLogging(){
		System.out.println("method");
	}

}
