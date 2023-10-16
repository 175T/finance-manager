package com.github.greatwqs.app.utils.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.function.Function;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/***
 *
 * Excel 导出工具类
 *
 * @author greatwqs
 * Create on 2020/7/9
 */
@Data
@Slf4j
public class ExcelExport<T> {
    private static final String FONT = "Arial";
    private LinkedHashMap<String, Function<T, Object>> getterMap = Maps.newLinkedHashMap();
    private String dateFormat;
    private TimeZone targetTimeZone;
    private Locale locale;
    private HSSFWorkbook hssfWorkbook;
    private String sheetName;
    private String fileName;

    private static void setCellValue(Object value, Cell cell, String dateFormat, TimeZone targetTimeZone) {
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue(String.valueOf(value));
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Date) {
            cell.setCellValue(DateFormatUtils.format((Date) value, dateFormat, targetTimeZone));
        } else {
            if (value != null) {
                cell.setCellValue(value.toString());
            }
        }
    }

    public ExcelExport<T> putCreateExcelRule(String columnName, Function<T, Object> getter) {
        getterMap.put(columnName, getter);
        return this;
    }

    public HSSFWorkbook toExcel(String sheetName, Collection<T> collection) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(12);
        ExcelStyle excelStyle = createExcelStyle(workBook);

        HSSFRow rowHeader = sheet.createRow(0);
        int headerColumn = 0;
        for (final String columnName : getterMap.keySet()) {
            HSSFCell cellHeader = rowHeader.createCell(headerColumn);
            cellHeader.setCellStyle(excelStyle.getTitleStyle());
            cellHeader.setCellValue(columnName);
            headerColumn++;
        }

        int rowNum = 1;
        for (T t : collection) {
            HSSFRow row = sheet.createRow(rowNum);
            int columnNum = 0;
            for (Function<T, Object> getter : getterMap.values()) {
                HSSFCell cell = row.createCell(columnNum);
                cell.setCellStyle(excelStyle.getBodyStyle());
                setCellValue(getter.apply(t), cell, dateFormat, targetTimeZone);
                columnNum++;
            }
            rowNum++;
        }
        return workBook;
    }

    public void downloadExcel(OutputStream outputStream) {
        try {
            hssfWorkbook.write(outputStream);
            outputStream.flush();
        } catch (IOException ex) {
            log.error("download log to excel fail", ex);
        }
    }

    private ExcelStyle createExcelStyle(HSSFWorkbook workbook) {
        // 生成一个样式
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        // 设置这些样式
        titleStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        HSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontName(FONT);
        titleFont.setColor(HSSFFont.COLOR_NORMAL);
        titleFont.setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        titleStyle.setFont(titleFont);
        // 生成并设置另一个样式
        HSSFCellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setFillForegroundColor(IndexedColors.WHITE1.getIndex());
        bodyStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        HSSFFont bodyFont = workbook.createFont();
        bodyFont.setBold(false);
        // 把字体应用到当前的样式
        bodyFont.setFontName(FONT);
        bodyStyle.setFont(bodyFont);
        bodyStyle.setWrapText(true);
        return new ExcelStyle(titleStyle, bodyStyle);
    }

    @Data
    @AllArgsConstructor
    public static class ExcelStyle {
        private HSSFCellStyle titleStyle;
        private HSSFCellStyle bodyStyle;
    }

}
