package excel_stream;

import GUI_form.JordGUI;
import javafx.beans.value.WritableDoubleValue;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dimic on 16.03.2017.
 */
public class Writer {

    public static void writeToExcel(double[][] result) throws IOException {
        JordGUI jGUI = new JordGUI();
        File file = jGUI.output;
        FileOutputStream out = new FileOutputStream(file);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Жорданові перетворення");

        CellStyle style = workbook.createCellStyle();
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);

        for (int i = 0; i < result.length; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < result[i].length; j++) {
                Cell cell = row.createCell(j);
                if (result[i][j]!=0) {
                    cell.setCellValue(result[i][j]);
                    cell.setCellStyle(style);
                }
                else {
                    cell.setCellValue(" ");
                    cell.setCellStyle(style);
                }
            }
        }

        workbook.write(out);

        workbook.close();
        out.close();
    }
}
