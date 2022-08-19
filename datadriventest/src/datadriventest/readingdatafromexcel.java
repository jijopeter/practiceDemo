package datadriventest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;


public class readingdatafromexcel {
	
	public static void main(String[] args) throws IOException {
		FileInputStream file=new FileInputStream("C://Users//Admin//OneDrive - Dunn Solutions Group, Inc//Documents//data files//datadrivendata.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		int rowcount=sheet.getLastRowNum();
		int coloumcount=sheet.getRow(0).getLastCellNum();
		for(int i=0;i<rowcount;i++)
		{
			XSSFRow currentrow=sheet.getRow(i);
			for(int j=0;j<coloumcount;j++)
			{
				String value=currentrow.getCell(j).toString();
				System.out.print(" "+value);
			}
			System.out.println();
		}

	}

}
