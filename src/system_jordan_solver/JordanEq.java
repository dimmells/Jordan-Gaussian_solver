package system_jordan_solver;


import GUI_form.JordGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import static excel_stream.Reader.createArray;
import static excel_stream.Reader.readFromExcel;
import static excel_stream.Writer.writeToExcel;

/**
 * Created by dimic on 12.03.2017.
 */
public class JordanEq {
    public static double[][] arr;
    public static int keyNum;
    public static int keyIndex;
    public static double[][] result;

    public static void main(String[] args) throws IOException {
        JordGUI gui = new JordGUI();
        gui.setVisible(true);
    }

    public static void getArr() {
        try {
            arr = readFromExcel();
        }
        catch (IOException e1) {}
    }

    public static void copyArr() {
        result = new double[arr.length*4+4+5][arr[0].length];
        for(int i=0; i<arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result[i][j] = arr [i][j];
            }
        }
    }

    public static void printArr(double[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                if (arr[i][j]!=0)
                    roundPrint(arr[i][j]);
                else
                    System.out.print("        ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }

    public static void roundPrint(double element) {
        String pattern = "##0.000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(element);
        System.out.print(format + "\t");
    }

    public static void chooseKey(int keyNumb) {
        for (keyIndex = keyNumb; keyIndex<arr.length; keyIndex++) {
            if (arr[keyNumb][keyIndex] != 0) {
                keyNum = keyNumb;
                break;
            }
        }
    }

    public static void processMatrix() throws IOException{
        copyArr();

        for (int key=0; key<arr[0].length - 1; key++) {
            chooseKey(key);

            processOtherElements();
            processColumn();
            processLine();
            processKey();

            delete();

            copyResult(key);
        }

        writeToExcel(result);
    }

    public static void copyResult(int key) {
        for(int i=0; i<arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result[i+5*key+5][j] = arr[i][j];
            }
        }
    }

    public static void processOtherElements() {
        for (int i = 0; i<arr.length; i++) {
            for (int j = 0; j<arr[i].length; j++) {
                if (i!=keyNum && j!=keyIndex) {
                    arr[i][j] = arr[i][j] - (arr[i][keyIndex] * arr[keyNum][j] / arr[keyNum][keyIndex]);
                }
            }
        }
    }

    public static void processLine() {
        for (int i = 0; i<arr[0].length; i++) {
            if (i!=keyIndex) {
                arr[keyNum][i] = - arr[keyNum][i] / arr[keyNum][keyIndex];
            }
        }
    }

    public static void processColumn() {
        for (int i = 0; i<arr[0].length - 1; i++) {
            if (i!=keyNum) {
                arr[i][keyIndex] = arr[i][keyIndex] / arr[keyNum][keyIndex];
            }
        }
    }

    public static void processKey() {
        arr[keyNum][keyIndex] = 1 / arr[keyNum][keyIndex];
    }

    public static void delete() {
        if (keyNum >= 1) {
            for (int i=0; i<arr.length; i++) {
                arr[i][keyNum-1] = 0;
            }
        }
    }
}
