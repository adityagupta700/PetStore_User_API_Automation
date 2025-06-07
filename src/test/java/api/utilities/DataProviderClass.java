package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "UserData")
	public String[][] getUserPayloadData() throws Exception {

		String excelPath = System.getProperty("user.dir") + "//testData//UserData.xlsx";

		ExcelUtility xl = new ExcelUtility(excelPath);

		String sheetName = "UserPayloadData";

		int rowCount = xl.getRowCount(sheetName); // No. of Rows
		int colCount = xl.getColCount(sheetName, 0); // No. of Columns

		String[][] excelData = new String[rowCount - 1][colCount]; // to skip the header row

		for (int i = 1; i < rowCount; i++) { // skipping the header row
			for (int j = 0; j < colCount; j++) {
				excelData[i - 1][j] = xl.getCellData(sheetName, i, j);
			}
		}

		return excelData;
	}

	@DataProvider(name = "Username")
	public String[] getUsernames() throws Exception {
		
		String excelPath = System.getProperty("user.dir") + "//testData//UserData.xlsx";

		ExcelUtility xl = new ExcelUtility(excelPath);

		String sheetName = "UserPayloadData";
		
		int rowCount = xl.getRowCount(sheetName); // No. of Rows
		
		String[] usernames = new String[rowCount - 1];
		
		for(int i = 1; i < rowCount; i++) {
			usernames[i - 1] = xl.getCellData(sheetName, i, 1);
		}
		
		return usernames;
		
	}
}
