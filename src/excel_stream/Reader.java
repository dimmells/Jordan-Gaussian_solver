package excel_stream;

import GUI_form.JordGUI;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import system_jordan_solver.JordanEq;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Reader {
    private static LinkedList<Double> list = new LinkedList<>();

    public static double[][] readFromExcel() throws IOException{

        JordGUI jGUI = new JordGUI();
        File file = jGUI.input;
        FileInputStream in = new FileInputStream(file);

        Workbook myExcelBook = new XSSFWorkbook(in);
        Sheet myExcelSheet = myExcelBook.getSheetAt(0);

        for (Row row : myExcelSheet) {
            for (Cell cell : row)
            if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                list.add(cell.getNumericCellValue());
            }
        }

        myExcelBook.close();
        in.close();

        return createArray();
    }

    public static double[][] createArray () {
        int countOfVar = 2;

        while (true){
            if (list.size() == (countOfVar + 1) * countOfVar) {
                break;
            }
            else {
                countOfVar++;
            }
        }

        double[][] array = new double[countOfVar][countOfVar + 1];

        for (int i=0; i < array.length; i++) {
            for (int j=0; j < countOfVar + 1; j++) {
                array[i][j] = list.get(0);
                list.remove(0);
            }
        }

        return array;
    }

}
