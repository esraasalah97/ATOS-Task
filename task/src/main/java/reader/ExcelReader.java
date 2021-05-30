package reader;


import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	  public  XSSFWorkbook getExcelFile(String fileName) throws IOException{
		    InputStream inputStream = ExcelReader.class.getClassLoader().getResourceAsStream("./resources/"+fileName+".xlsx");
		    return new XSSFWorkbook(inputStream);		    
            }
	  public Sheet getSheet(String fileName,String sheetName) throws IOException {
		 XSSFWorkbook reader=getExcelFile(fileName);
		 return  reader.getSheet(sheetName); 
	  }
}
