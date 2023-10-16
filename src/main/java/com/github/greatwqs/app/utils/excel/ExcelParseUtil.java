package com.github.greatwqs.app.utils.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/***
 *
 * ExcelParseUtil for parse(read) excel file data.
 * https://blog.csdn.net/aqsunkai/article/details/52270198
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Slf4j
public class ExcelParseUtil {

    private static final String xls = "xls";
    private static final String xlsx = "xlsx";

    /****
     * read MultipartFile excel file, convert row data from given columnNum
     * return List<String[]> : List<RowData>
     * @param excelFile
     * @param columnNum
     * @return
     * @throws IOException
     */
    public static List<String[]> readExcel(MultipartFile excelFile, int columnNum) throws IOException {
        checkFile(excelFile);
        Workbook workbook = getWorkBook(excelFile);
        return readExcel(workbook, columnNum);
    }

    /****
     * read MultipartFile excel file, convert row data from given columnNum
     * return List<String[]> : List<RowData>
     * @param workbook
     * @param columnNum
     * @return
     * @throws IOException
     */
    public static List<String[]> readExcel(Workbook workbook, int columnNum) throws IOException {
        if (workbook == null) {
            return new LinkedList<>();
        }

        // Sheet index 0
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return new LinkedList<>();
        }

        List<String[]> dataRowList = new LinkedList<String[]>();
        int lastRowNum = sheet.getLastRowNum();
        // the first row ignore.
        for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }

            String[] cellsData = new String[columnNum];
            for (int cellNum = 0; cellNum < columnNum; cellNum++) {
                Cell cell = row.getCell(cellNum);
                cellsData[cellNum] = getCellValue(cell).trim();
            }
            dataRowList.add(cellsData);
        }
        workbook.close();
        return dataRowList;
    }

    /***
     * checkFile for excelFile
     * @param excelFile
     * @throws IOException
     */
    public static void checkFile(MultipartFile excelFile) throws IOException {
        if (excelFile == null) {
            log.error("excelFile null");
            throw new FileNotFoundException();
        }
        String fileName = excelFile.getOriginalFilename();
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            log.error(fileName + "is not excel file");
            throw new IOException(fileName + "is not excel file");
        }
    }

    /****
     * getWorkBook from param excelFile
     * @param excelFile
     * @return
     */
    public static Workbook getWorkBook(MultipartFile excelFile) throws IOException {
        String fileName = excelFile.getOriginalFilename();
        Workbook workbook = null;
        InputStream is = excelFile.getInputStream();
        if (fileName.endsWith(xls)) {
            //2003
            workbook = new HSSFWorkbook(is);
        } else if (fileName.endsWith(xlsx)) {
            //2007
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    /***
     * getCellValue, return String.
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) { // null return "";
            return cellValue;
        }

        // treat number as String, avoid reading 1 as 1.0
        if (cell.getCellType() == CellType.NUMERIC) {
            cell.setCellType(CellType.STRING);
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellValue = "";
                break;
            case ERROR:
                cellValue = "ERROR";
                break;
            default:
                cellValue = "Unknown CellType";
                break;
        }
        return cellValue;
    }
}
