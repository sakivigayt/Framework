package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static final Logger LOG = Logger.getLogger(ExcelUtils.class);
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelUtils(String path) throws IOException {
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {

		}

	}

	public void setSheet(String sheetname) {
		sheet = workbook.getSheet(sheetname);
	}

	public int getRowCount() {

		return sheet.getPhysicalNumberOfRows();
	}

	public int getHeaderCount(int row) {
		int j = sheet.getRow(row).getPhysicalNumberOfCells();
		int i;
		for (i = 0; i < j; i++) {
			if (getCellData(row, i) == null) {
				break;
			}
		}
		return i;
	}

	public int getColumnCount(int row) {
		return sheet.getRow(row).getPhysicalNumberOfCells();
	}

	public String getCellData(int row, int column) {
		XSSFRow rowCell = sheet.getRow(row);

		if (rowCell == null) {
			return null;
		} else {
			XSSFCell cell = rowCell.getCell(column);
			if (cell != null) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				return cell.getStringCellValue().trim();
			} else {
				return null;
			}

		}
	}

	public void writeData(int rows, List<String> data) {
		int col = 0;
		int rowsLocal = rows;
		Row row = sheet.createRow(rowsLocal++);
		for (String s : data) {
			Cell cell = row.createCell(col++);
			cell.setCellValue(s);
			addBorder(cell);
		}

	}

	public int rowNumberwithData(int column, String data) {
		int rowCount = getRowCount();
		int rowNumber = -1;
		for (int i = 0; i < rowCount; i++) {
			if (data.equals(getCellData(i, column))) {
				rowNumber = i;
				break;
			}
		}

		if (rowNumber == -1) {
			// throw new InitializationException("no row found with data: " +
			// data + " in column " + column);
		}
		return rowNumber;
	}

	public List<String> readRowData(int row, int colStart) {
		Row row1 = sheet.getRow(row);
		int colCount = row1.getPhysicalNumberOfCells();
		List<String> val = new ArrayList<String>();
		for (int i = colStart; i < colCount; i++) {
			Cell cell = row1.getCell(i);
			if (!"".equals(cell.getStringCellValue())) {
				val.add(cell.getStringCellValue());
			} else {
				break;
			}
		}
		return val;
	}

	public void changeColor(Cell cell) {
		CellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
		cell.setCellStyle(style);
	}

	/*
	 * public void createLink(Cell cell, String file) { try { CreationHelper
	 * createHelper = workbook.getCreationHelper();
	 * org.apache.poi.ss.usermodel.Hyperlink hp =
	 * createHelper.createHyperlink(Hyperlink.LINK_FILE);
	 * 
	 * hp.setAddress(file.replace("\\", Constants.FS)); cell.setHyperlink(hp); }
	 * catch (Exception e) { LOG.error("createLink", e); }
	 * 
	 * }
	 */
	public void addBorder(Cell cell) {
		CellStyle style = workbook.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
	}

	public void headerColor(Cell cell) {
		CellStyle style = workbook.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_DOUBLE);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_DOUBLE);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(CellStyle.BORDER_DOUBLE);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_DOUBLE);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
	}

	public String[][] excelTestData() throws Exception {

		try {
			int startRowNumber = 1;

			List<Integer> row = new ArrayList<Integer>();

			int col = getHeaderCount(startRowNumber) - 1;
			while (getCellData(startRowNumber, 0) != null) {
				if (Constants.TEST_FLOW_EXCEL_INCLUDE_INDICATOR
						.equalsIgnoreCase(getCellData(startRowNumber, 0))) {
					row.add(startRowNumber);
				}
				startRowNumber++;
			}

			String[][] data = new String[row.size()][col];
			for (int i = 0; i < row.size(); i++) {
				for (int j = 0; j < col; j++) {
					data[i][j] = getCellData(row.get(i), j + 1);
				}

			}
			return data;
		} catch (Exception e) {
			LOG.error("exception: ", e);
		}

		return new String[0][0];

	}

	public String[][] excelTestData(int startRow, int col) throws Exception {

		try {

			List<Integer> row = new ArrayList<Integer>();

			col = getHeaderCount(startRow) - 1;
			while (getCellData(startRow, col) != null) {
				if (Constants.TEST_FLOW_EXCEL_INCLUDE_INDICATOR
						.equalsIgnoreCase(getCellData(startRow, 0))) {
					row.add(startRow);
				}
				startRow++;
			}

			String[][] data = new String[row.size()][col];
			for (int i = 0; i < row.size(); i++) {
				for (int j = 0; j < col; j++) {
					data[i][j] = getCellData(row.get(i), j + 1);
				}

			}
			return data;
		} catch (Exception e) {
			LOG.error("exception: ", e);
		}

		return new String[0][0];

	}

}
