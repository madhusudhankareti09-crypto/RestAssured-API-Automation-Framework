package api.utilites;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {

    String path;

    FileInputStream fi;
    FileOutputStream fo;

    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Row row;
    Cell cell;

    // Constructor
    public XLUtility(String path) {
        this.path = path;
    }

    // ==========================
    // Get Row Count
    // ==========================
    public int getRowCount(String sheetName) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();

        workbook.close();
        fi.close();

        return rowCount;
    }

    // ==========================
    // Get Cell Count
    // ==========================
    public int getCellCount(String sheetName, int rownum) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);

        int cellCount = row.getLastCellNum();

        workbook.close();
        fi.close();

        return cellCount;
    }

    // ==========================
    // Get Cell Data
    // ==========================
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);

        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();

        String data;

        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }

        workbook.close();
        fi.close();

        return data;
    }

    // ==========================
    // Set Cell Data
    // ==========================
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);

        if (row == null) {
            row = sheet.createRow(rownum);
        }

        cell = row.getCell(colnum);

        if (cell == null) {
            cell = row.createCell(colnum);
        }

        cell.setCellValue(data);

        fo = new FileOutputStream(path);

        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    // ==========================
    // Fill Green Color
    // ==========================
    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);

        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

    // ==========================
    // Fill Red Color
    // ==========================
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);

        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }
}