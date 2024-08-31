package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReadWriteActions {

/*
If excel is not present it will create xl and write first record in it.
Then from next run onwords it will get the existing excel and check number of rows present
And start writing data after last row present.
 */
    public static void ExcelWriterUsingHashMap(HashMap data) throws IOException {

        XSSFWorkbook workbook;
        XSSFSheet sheet;
        int rowNum=0;
        try {
                // Create a workbook and a sheet
                FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\ResultSheets\\" + "output.xlsx");
                 workbook = new XSSFWorkbook(fileIn);
                sheet = workbook.getSheetAt(0);

                // Find the last row number
                int lastRowNum = sheet.getLastRowNum();
                // Add data rows starting from the next row
                 rowNum = lastRowNum + 1;
            } catch (Exception e) {
                    workbook = new XSSFWorkbook();
                   sheet = workbook.createSheet("Data");
                Row headerRow = sheet.createRow(0);
                int headerCellNum = 0;

                for(Object key: data.keySet()) {
                    Cell cell = headerRow.createCell(headerCellNum++);
                    cell.setCellValue(key.toString());
                    // Add data rows
                     rowNum = 1;
                }
            }

        for (Map<String, String> data1 : new Map[]{data}) {
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            for (Map.Entry<String, String> entry : data1.entrySet()) {
                Cell cell = row.createCell(cellNum++);
                cell.setCellValue(entry.getValue());
            }
        }

        // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"\\src\\test\\ResultSheets\\"+"output.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Closing the workbook
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
