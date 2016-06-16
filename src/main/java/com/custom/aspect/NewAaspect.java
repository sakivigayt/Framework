package com.custom.aspect;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class NewAaspect {

	@Before("execution(* *(..))")
    public void loggerMethod(JoinPoint joinPoint)
                  throws Throwable {

          // Logger logger = Logger.getLogger("testExecutionLogger");
           MethodSignature signature = (MethodSignature) joinPoint
                        .getSignature();
           String className = signature.getDeclaringTypeName();
           Method method = signature.getMethod();
           String methodName = method.getName();
          // logger.info("Class: " + className + ", Method: " + methodName);
           System.out.println("Class: " + className + ", Method: " + methodName);
    
    }      

}
