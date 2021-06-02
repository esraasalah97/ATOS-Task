package atos.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import reader.ExcelReader;

import org.apache.poi.ss.usermodel.DataFormatter;
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
			    DataFormatter formatter = new DataFormatter();
			    while (rowIterator.hasNext()) {
			    	Row row=rowIterator.next();
			        testData.put(formatter.formatCellValue(row.getCell(0)),formatter.formatCellValue(row.getCell(1)));
			    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
