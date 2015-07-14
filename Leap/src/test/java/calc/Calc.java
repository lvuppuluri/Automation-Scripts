package calc;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Calc {
	
	public static Object[][] getExcelArray(String tablePath, String sheetName) throws Exception {
		// Opens the Excel file. Here goes the path of the file.
		File excel = new File(tablePath);
		System.out.println("path"+tablePath);
		FileInputStream fis = new FileInputStream(excel);
		// Opens the sheet. Here goes the name of the sheet on the Excel file.
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(sheetName);
             System.out.println("sheet"+sheetName);
		int rowNum = ws.getLastRowNum() + 1;
		int colNum = ws.getRow(0).getLastCellNum();
		System.out.println("Total rows are: " + (rowNum-1));
		System.out.println("Total columns are: " + colNum);
		String[][] data = new String[(rowNum - 1)][colNum];

		int k = 0;
		for (int i = 1; i < rowNum; i++) {
			XSSFRow row = ws.getRow(i);
			for (int j = 0; j < colNum; j++) {
				XSSFCell cell = row.getCell(j, Row.RETURN_NULL_AND_BLANK);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String value = cellToString(cell);
				data[k][j] = value;
			};
			k++;
		};
		return data;
	}
			
	public static String cellToString(XSSFCell cell) {
		Object result;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
		   result = cell.getNumericCellValue();
		   break;
		case Cell.CELL_TYPE_STRING:
		   result = cell.getStringCellValue();
		   break;
		case Cell.CELL_TYPE_BOOLEAN:
		   result = cell.getBooleanCellValue();
		   break;
		case Cell.CELL_TYPE_FORMULA:
		   result = cell.getCellFormula();
		   break;
		default:
		   //throw new RuntimeException("Unknown Cell Type");
			return null;
		}
		return result.toString();
	}

}

