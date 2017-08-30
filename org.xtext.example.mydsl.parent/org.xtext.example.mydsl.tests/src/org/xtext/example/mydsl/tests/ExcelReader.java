package org.xtext.example.mydsl.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public static Map<String, String> readRow(String excelFileName, String sheetName, String cellContent) {
		try {
			Sheet sheet = loadSheet(excelFileName, sheetName);
			return readRow(sheet, cellContent);
		} catch (Exception ex) {
			logException(excelFileName, ex);
			return new HashMap<String, String>();
		}
	}

	public static Map<String, String> readRow(String excelFileName, String sheetName, String headerColumnName,
			String cellContent) {
		try {
			Sheet sheet = loadSheet(excelFileName, sheetName);
			return readRow(sheet, headerColumnName, cellContent);
		} catch (Exception ex) {
			logException(excelFileName, ex);
			return new HashMap<String, String>();
		}
	}

	private static void logException(String excelFileName, Exception ex) {
		System.err.println("Error processing " + excelFileName);
		ex.printStackTrace();
	}
	
	private static Map<String, String> readRow(Sheet sheet, String headerColumnName, String cellContent) {
		Map<String, String> result = new HashMap<>();
		Objects.requireNonNull(sheet, "Argument [sheet] cannot be null!");
		if (headerColumnName == null || headerColumnName.isEmpty()) throw new IllegalArgumentException("Argument [headerColumnName] cannot be null or empty!");
		if (cellContent == null || cellContent.isEmpty()) throw new IllegalArgumentException("Argument [cellContent] cannot be null or empty!");
		Row header = sheet.getRow(0);
		Integer colidx = null;
		for (Cell col : header) {
			if (col.getCellType() == Cell.CELL_TYPE_STRING && 
				col.getRichStringCellValue().getString().trim().equals(headerColumnName)) {
				colidx = col.getColumnIndex();
			}
		}
		if (colidx == null) return result;
		for (Row row : sheet) {
			Map<String, String> map = readRow(header, row, colidx, cellContent);
			if (!map.isEmpty()) return map;
		}
		return result;
	}

	private static Map<String, String> readRow(Sheet sheet, String cellContent) {
		Map<String, String> result = new HashMap<>();
		Objects.requireNonNull(sheet, "Argument [sheet] cannot be null!");
		if (cellContent == null || cellContent.isEmpty()) throw new IllegalArgumentException("Argument [cellContent] cannot be null or empty!");
		Row header = sheet.getRow(0);
		for (Row row : sheet) {
			 Map<String, String> map = readRow(header, row, null, cellContent);
			 if (!map.isEmpty()) return map;
		}
		return result;
	}

	private static Map<String, String> readRow(Row header, Row row, Integer colidx, String cellContent) {
		Map<String, String> result = new HashMap<>();
		for (Cell cell : row) {
			if (colidx != null && cell.getColumnIndex() != colidx) continue;
			DataFormatter formatter = new DataFormatter();
			String colname = formatter.formatCellValue(cell);
			if (colname.trim().equals(cellContent)) {
				row.cellIterator().forEachRemaining(c -> {
					String value = formatter.formatCellValue(c);
					String name = header.getCell(c.getColumnIndex()).getStringCellValue();
					String raw = name.trim().replaceAll(" ", "").substring(0, 1).toLowerCase()
								 + name.trim().replaceAll(" ", "").substring(1);
					String key = StringUtils.stripAccents(raw.replaceAll("[^\\p{Alpha}]+", "").replaceAll("\\p{M}", ""));
					result.put(key, value);
				});
				return result;
			}
		}
		return result;
	}

	public static Sheet getSheet(String excelFileName, String sheetName) throws IOException {
		return loadSheet(excelFileName, sheetName);
	}

	private static Sheet loadSheet(String excelFileName, String sheetName) throws IOException {
		return loadExcelSheet(excelFileName, sheetName);
	}

	private static Sheet loadExcelSheet(String excelFileName, String sheetName) throws IOException {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(excelFileName);
		Workbook workbook = excelFileName.endsWith("xls") ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
		return workbook.getSheet(sheetName);
	}
}
