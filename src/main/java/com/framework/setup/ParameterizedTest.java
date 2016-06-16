package com.framework.setup;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.custom.annotations.Parameterize;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.ExcelUtils;

public class ParameterizedTest {

	private static String fileName;
	private static String sheetName;
	private static int testIndex;

	private static String query;

	public static String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		ParameterizedTest.fileName = fileName;
	}

	public static String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		ParameterizedTest.sheetName = sheetName;
	}

	public int getTestIndex() {
		return testIndex;
	}

	public void setTestIndex(int testIndex) {
		ParameterizedTest.testIndex = testIndex;
	}

	public static String getQuery() {
		return query;
	}

	public static void setQuery(String query) {
		ParameterizedTest.query = query;
	}

	@DataProvider(name = "ExcelData")
	public static Object[][] dataParameter(Method m) throws Exception {
		System.out.println(m.getName());
		String fileName = m.getAnnotation(Parameterize.class).filePath();
		String sheetName = m.getAnnotation(Parameterize.class).sheetName();
		ExcelUtils exl = new ExcelUtils(fileName);
		exl.setSheet(sheetName);

		return exl.excelTestData();

	
}

	
	@DataProvider(name = "JSONReader")
	public static Iterator<Object[]> jsonData(Method m) throws Exception {
		Collection<Object[]> dp = new ArrayList<Object[]>();
		ObjectMapper mapper = new ObjectMapper();
		String fileName = m.getAnnotation(Parameterize.class).filePath();
		Class<?> entityType = m.getAnnotation(Parameterize.class).entityName();
		Iterator<?> entityIterator = mapper.reader(entityType).readValues(new File(fileName));

		while (entityIterator.hasNext()) {
			dp.add(new Object[] { entityIterator.next() });
		}

		return dp.iterator();

	}

}
