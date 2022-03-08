package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_ExcelData {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	FileInputStream inputstream;

	public Read_ExcelData(String excelPath, String Sheetname) throws IOException {

		try {
			
			inputstream = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(inputstream);
			sheet = workbook.getSheet(Sheetname);
		} catch (IOException e) {

			e.printStackTrace();
		}
		finally{
			inputstream.close();
		}
	}

	public String [][] getInputData() throws IOException{
				
		int rowcount= sheet.getPhysicalNumberOfRows();
		int cols=sheet.getRow(0).getLastCellNum();
		
		
		String [][] Value=new String[rowcount-1][cols-1];
		try {
		for(int i=1;i<rowcount;i++) {
        	for(int j=1;j<cols;j++) {
        		Value[i-1][j-1]=getCellData(i,j);
        	}
        	System.out.println();
        	
        	}
		}
		catch (Exception e) {	
			
			e.printStackTrace();
		}
		
				
		return Value;

	}

	public static String getCellData(int i,int j) throws IOException{

		String data= null ;
		try {
		
		DataFormatter formatter = new DataFormatter();
		Row row=sheet.getRow(i);
		Cell cell = row.getCell(j);
		
		data = formatter.formatCellValue(cell);
		//System.out.print(data);
		
		}
		catch (Exception e) {
			 e.printStackTrace();
			//data ="";
		}
		
		return data;
	}
	
	
	
}














