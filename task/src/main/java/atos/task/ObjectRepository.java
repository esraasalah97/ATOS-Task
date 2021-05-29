package atos.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import reader.ExcelReader;

public class ObjectRepository {
	private HashMap<String,List<String>> objects = new HashMap<String,List<String>>();
public ObjectRepository(String fileName,String sheetName) {
	readObjects(fileName,sheetName);
}
	public HashMap<String, List< String>> getObjects() {
	return objects;
}
	private void readObjects(String fileName,String sheetName) {
		try {
			ExcelReader reader=new ExcelReader();
			Sheet testdataSheet=reader.getSheet(fileName,sheetName);
			    Iterator<Row> rowIterator = testdataSheet.rowIterator();
			    while (rowIterator.hasNext()) {
			    	Row row=rowIterator.next();
			    	List<String>innerList= fillInnerList(row);
			        objects.put(row.getCell(0).getStringCellValue(),innerList);
			    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<String> fillInnerList( Row row) {
		List <String> innerList =new ArrayList<String>();
		innerList.add(row.getCell(1).getStringCellValue());  
		innerList.add(row.getCell(2).getStringCellValue());
		    
         return innerList;
	}


}
