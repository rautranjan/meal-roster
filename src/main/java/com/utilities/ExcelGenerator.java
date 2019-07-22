package com.utilities;

import com.meal.Shift;
import com.meal.Week;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelGenerator {

    Week week;

    public ExcelGenerator(Week week) {
        this.week = week;
    }

    public void printExcel(String defaultLocation, String archiveLocation) {
        try (OutputStream fileOut = new FileOutputStream("/Users/ranjanraut/IdeaProjects/roster/src/main/resources/MyFirstExcel.xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sheet");


            XSSFCellStyle style = workbook.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setAlignment(HorizontalAlignment.CENTER);

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(2, 6, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(2, 8, 5, 5));

            Row[] rows = new Row[9];
            rows[0] = sheet.createRow(0);
            Cell cell = rows[0].createCell(0);
            cell.setCellValue("Day");
            cell.setCellStyle(style);

            cell = rows[0].createCell(1);
            cell.setCellValue("Evening");
            cell.setCellStyle(style);

            cell = rows[0].createCell(3);
            cell.setCellValue("Afternoon");
            cell.setCellStyle(style);

            rows[1] = sheet.createRow(1);
            cell = rows[1].createCell(1);
            cell.setCellValue("Cooking");
            cell.setCellStyle(style);

            cell = rows[1].createCell(2);
            cell.setCellValue("Cleaning");
            cell.setCellStyle(style);

            cell = rows[1].createCell(3);
            cell.setCellValue("Cooking");
            cell.setCellStyle(style);

            cell = rows[1].createCell(4);
            cell.setCellValue("Cleaning");
            cell.setCellStyle(style);

            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            Shift[] shifts = {this.week.getMonday(), this.week.getTuesday(), this.week.getWednesday(), this.week.getThursday(),
                    this.week.getFriday(), this.week.getSaturdayNight(), this.week.getSundayNight()};

            for (int i = 0; i < days.length; i++) {

                rows[i + 2] = sheet.createRow(i + 2);

                cell = rows[i + 2].createCell(0);
                cell.setCellValue(days[i]);
                cell.setCellStyle(style);

                cell = rows[i + 2].createCell(1);
                cell.setCellValue(shifts[i].getChef1() + ", " + shifts[i].getChef2());
                cell.setCellStyle(style);

                cell = rows[i + 2].createCell(2);
                cell.setCellValue(shifts[i].getCleaner());
                cell.setCellStyle(style);

                cell = rows[i + 2].createCell(3);
                cell.setCellValue("-");
                cell.setCellStyle(style);

            }

            cell = rows[7].createCell(3);
            cell.setCellValue(this.week.getSaturday().getChef1() + ", " + this.week.getSaturday().getChef2());
            cell.setCellStyle(style);

            cell = rows[7].createCell(4);
            cell.setCellValue(this.week.getSaturday().getCleaner());
            cell.setCellStyle(style);

            cell = rows[8].createCell(3);
            cell.setCellValue(this.week.getSunday().getChef1() + ", " + this.week.getSunday().getChef2());
            cell.setCellStyle(style);

            cell = rows[8].createCell(4);
            cell.setCellValue(this.week.getSunday().getCleaner());
            cell.setCellStyle(style);


            rows[1].createCell(5).setCellStyle(style);
            cell = rows[0].createCell(5);
            cell.setCellValue("Trash Picker | Micro-oven Cleaner");
            cell.setCellStyle(style);


            rows[2].createCell(5).setCellStyle(style);
            rows[3].createCell(5).setCellStyle(style);
            rows[4].createCell(5).setCellStyle(style);
            rows[5].createCell(5).setCellStyle(style);
            rows[6].createCell(5).setCellStyle(style);
            rows[7].createCell(5).setCellStyle(style);
            rows[8].createCell(5).setCellStyle(style);


            cell = rows[2].createCell(5);
            cell.setCellValue(this.week.getTrashPicker());
            cell.setCellStyle(style);


            sheet.autoSizeColumn(0, true);
            sheet.autoSizeColumn(1, true);
            sheet.autoSizeColumn(2, true);
            sheet.autoSizeColumn(3, true);
            sheet.autoSizeColumn(4, true);
            sheet.autoSizeColumn(5, true);

            workbook.write(fileOut);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
