package com.custom.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parameterize {

	public enum DataType {
		EXCEL, XML, JSON, DATABASE
	}

	public String query() default "";

	public DataType dataType() default DataType.EXCEL;

	public String filePath() default "";

	public String sheetName() default "";
	
	public Class<?> entityName() default Object.class;

	int id() default 0;

}
