package genericlibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataUtility {

	public String getDataFromProperties(String key) {
		FileInputStream fin;
		Properties p = null;
		try {
			fin = new FileInputStream("./ExcelData/login.properties");
			p = new Properties();
			p.load(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}

	public String getUserNameAndPassword(String sheetname, int row, int cell)
			throws EncryptedDocumentException, IOException {
		FileInputStream fin = new FileInputStream("./ExcelData/login.properties");
		Workbook w = WorkbookFactory.create(fin);
		return w.getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
	}

	public Object[][] getAllData(String sheetname) throws EncryptedDocumentException, IOException {
		FileInputStream fin = new FileInputStream("./ExcelData/Book1.xlsx");
		Workbook w = WorkbookFactory.create(fin);
		int rowCount = w.getSheet(sheetname).getPhysicalNumberOfRows();
		Object data[][] = new Object[rowCount - 1][];
		for (int i = 1; i < rowCount; i++) {
			int cellCount = w.getSheet(sheetname).getRow(i).getPhysicalNumberOfCells();
			data[i - 1] = new Object[cellCount];
			for (int j = 0; j < cellCount; j++) {
				data[i - 1][j] = w.getSheet(sheetname).getRow(i).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
}
