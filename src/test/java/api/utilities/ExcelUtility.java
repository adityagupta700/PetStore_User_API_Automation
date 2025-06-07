package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public String filePath;

	public ExcelUtility(String filePath) {
		this.filePath = filePath;
	}

	public int getRowCount(String sheetName) throws Exception {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
		wb.close();
		fis.close();

		return rowCount;
	}

	public int getColCount(String sheetName, int rowInd) throws Exception {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowInd); // rowInd => row index
		int cellCount = row.getLastCellNum() - row.getFirstCellNum();
		wb.close();
		fis.close();

		return cellCount;
	}

	public String getCellData(String sheetName, int rowInd, int colNum) throws Exception {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowInd);
		cell = row.getCell(colNum); // colNum is 0-based means it's like index

		String data;
		if (cell == null) {
			data = "";
		} else {
			switch (cell.getCellType()) {
			case STRING:
				data = cell.getStringCellValue();
				break;
			case NUMERIC:
				data = String.valueOf((int) cell.getNumericCellValue());
				break;
			case BOOLEAN:
				data = String.valueOf(cell.getBooleanCellValue());
				break;
			case FORMULA:
				data = cell.getCellFormula();
				break;
			default:
				data = "";
			}
		}

		wb.close();
		fis.close();
		return data.trim();
	}

	public void setCellData(String sheetName, int rowNum, int colNum, String cellData) throws Exception {
		File file = new File(filePath);

		// checking if the file exists at the filePath given
		if (!file.exists()) {
			wb = new XSSFWorkbook(); // creating the workbook which will be added to file at wb.write() step
		} else {
			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
			fis.close();
		}

		// checking if the sheet exists, if not then sheet creating it of same name
		// given as parameter
		if (wb.getSheet(sheetName) == null) {
			sheet = wb.createSheet(sheetName);
		}

		// checking if row exists, if not then creating one at same index
		if (sheet.getRow(rowNum) == null) {
			row = sheet.createRow(rowNum);
		}

		row = sheet.getRow(rowNum);

		cell = row.createCell(colNum);
		cell.setCellValue(cellData);
		fos = new FileOutputStream(filePath);
		wb.write(fos);
		wb.close();
		fos.close();
	}
}
