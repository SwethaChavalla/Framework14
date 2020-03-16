package genericCommands;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


public class excelReader {
	@BeforeSuite()
	public void getWB() {
		//System.out.println(getCellValue(3,0));
		try {
			FileInputStream file = new FileInputStream("C:/Practice/Framework14/TestDataFiles/excelReading.xls");
			Workbook workBook = WorkbookFactory.create(file);
			Sheet sheet = workBook.getSheet("Sheet1");
			//System.out.println(sheet.getLastRowNum());
		
		for (int r = 0; r <= sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);
			List<String> values = new ArrayList<>();
			if(!(row==null)){
				int cols_count = row.getLastCellNum();
				for (int c = 0; c < cols_count; c++) {
					Cell cell = row.getCell(c);
					values.add(getCellValueAsString(cell));
			}	
			}
			process(values);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean startOfTable= false;
	private boolean columnHeader=false;
	private Map<String, String> fileHeader = new LinkedHashMap<>();
	private List<String> columnHeaders = new ArrayList<>();
	private void process(List<String> csvData) {	
		
		if (startOfTable == false) {
			String cellValue = csvData.size() > 0 ? csvData.get(0) : "";
			if (cellValue.trim().length() == 0) {
				startOfTable = true;
				columnHeader = true;
			} else {
				String name = cellValue.substring(0, cellValue.indexOf("="));
				String value = cellValue.substring(cellValue.indexOf("=") + 1);
				fileHeader.put(name, value);
			}
		} else if (columnHeader) {
			for (String cellValue : csvData) {
				if (cellValue.trim().length() == 0) {
					break;
				}
				columnHeaders.add(cellValue);
			}
			columnHeader = false;
			System.out.println(fileHeader);
			System.out.println(columnHeaders);
		}
		else {
			Map<String, String> fieldValueMap = new LinkedHashMap<String, String>();
			for (int i = 0; i < columnHeaders.size(); i++) {
				String cellValue = null;
				if (i < csvData.size()) {
					cellValue = csvData.get(i);
				}
				fieldValueMap.put(columnHeaders.get(i), cellValue);
			}
			System.out.println(fieldValueMap);
		}
		
	}

	private String getCellValueAsString(Cell cell) {
		String cv="";
		if(!(cell==null)) {
		switch(cell.getCellType()) {
        case STRING:
            cv = cell.getStringCellValue();
            break;
        case NUMERIC:
        	cell.setCellType(CellType.STRING);
        	cv = cell.getStringCellValue();
            break;
        default:
            break;
        }
		//System.out.println(cv);
		}
		return cv;
	}

	public String getCellValue(int rowNo, int cellNo) {
		
		String cv="";
		try {
		FileInputStream file = new FileInputStream("C:/Practice/Framework14/TestDataFiles/excelReading.xls");
		Workbook workBook = WorkbookFactory.create(file);
		Sheet sheet = workBook.getSheet("Sheet1");
		
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);
		if(cell.getCellType()==CellType.NUMERIC) {
		cv=""+cell.getNumericCellValue();
		}
		else
		{
			cv=cell.getStringCellValue();
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return cv;		
	}
}
