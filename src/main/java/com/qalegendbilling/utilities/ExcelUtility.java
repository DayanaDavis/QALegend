package com.qalegendbilling.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qalegendbilling.constants.Constants;


public class ExcelUtility {
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static FileInputStream f;

	public static List<ArrayList<String>> excelDataReader(String sheetName){
	    DataFormatter formatter = new DataFormatter();
	    try {
	        f = new FileInputStream(System.getProperty("user.dir") + Constants.EXCEL_PATH);
	    } catch (FileNotFoundException e) {
	        throw new RuntimeException(e);
	    }
	    try {
	        wb = new XSSFWorkbook(f);
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    sh = wb.getSheet(sheetName);
	    List<ArrayList<String>> excelRows = new ArrayList<ArrayList<String>>();
	    int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
	    for (int i = 0; i < rowCount + 1; i++) {
	        int x = 0;
	        Row row = sh.getRow(i);
	        String[] columnList = new String[row.getLastCellNum()];
	        for (int j = 0; j < columnList.length; j++) {
	            columnList[j] = formatter.formatCellValue(row.getCell(x));
	            x++;
	        }
	        excelRows.add(new ArrayList<String>(Arrays.asList(columnList)));
	    }
	    return excelRows;
	}
	public  Object[][] dataProviderRead(String sheetName) {
	    DataFormatter formatter = new DataFormatter();
	    try {
	        f = new FileInputStream(System.getProperty("user.dir") + Constants.EXCEL_PATH);
	    } catch (FileNotFoundException e) {
	        throw new RuntimeException(e);
	    }
	    try {
	        wb = new XSSFWorkbook(f);
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    sh = wb.getSheet(sheetName);

	    int rows =sh.getLastRowNum()+1;
	    int cols = sh.getRow(0).getLastCellNum();
	    Object[][]  data = new Object[rows][cols];

	    for (int i = 0; i < rows; i++) {
	        Row row = sh.getRow(i);
	        String[] columnList = new String[row.getLastCellNum()];
	        for (int j = 0; j < cols; j++) {
	            Cell cell =row.getCell(j);
	            data[i][j] = cell.getStringCellValue();
	        }
	    }
	    return data;
	}
	public List<String> getExcelAsArrayList(String sheetName){
        String filepath = System.getProperty("user.dir") + Constants.EXCEL_PATH;
        FileInputStream file = null;
        try {
            file = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int cellCount = sheet.getRow(0).getLastCellNum();
        List<String> values=new ArrayList<String>();
        for (int i = 0; i <= rowCount; i++) {
            Row r = sheet.getRow(i);
            for (int j = 0; j < cellCount; j++) {
                Cell c = r.getCell(j);
                DataFormatter formatter = new DataFormatter();
                values.add (formatter.formatCellValue(sheet.getRow(i).getCell(j)));
            }

        }
       return values;
    }
}
