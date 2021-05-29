package atos.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import reader.ExcelReader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
public class Testdata {
	private HashMap<String,String> testData = new HashMap<String,String>();
	public Testdata(String fileName,String sheetName) {
		readTestData(fileName,sheetName);
	}
	public HashMap<String, String> getTestData() {
		return testData;
	}
	private void readTestData(String fileName,String sheetName) {
		try {
			ExcelReader reader=new ExcelReader();
			Sheet testdataSheet=reader.getSheet(fileName,sheetName);
			    Iterator<Row> rowIterator = testdataSheet.rowIterator();
			    while (rowIterator.hasNext()) {
			    	Row row=rowIterator.next();
			        testData.put(row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue());
			    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
