package com.utilities;

import com.meal.Shift;
import com.meal.Week;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelGenerator {

    Week week;

    public ExcelGenerator(Week week) {
        this.week = week;
    }

    public void printExcel(String defaultLocation, String archiveLocation) {

        XSSFWorkbook workbook = null;

        archiveLocation = archiveLocation + "/Excels";

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");

        try (OutputStream fileOut = new FileOutputStream(defaultLocation + "/recent.xlsx")) {
            workbook = new XSSFWorkbook();
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

            int rowCount = 5;

            if (this.week.getHome().getVacuumDate().getTime() - new Date().getTime() > 1) {
                rowCount++;
                sheet.addMergedRegion(new CellRangeAddress(0, 1, rowCount, rowCount));
                sheet.addMergedRegion(new CellRangeAddress(2, 8, rowCount, rowCount));
                rows[1].createCell(rowCount).setCellStyle(style);
                cell = rows[0].createCell(rowCount);
                cell.setCellValue("Home Vacuum");
                cell.setCellStyle(style);

                cell = rows[2].createCell(rowCount);
                cell.setCellValue(this.week.getHome().getVacuumCleaner());
                cell.setCellStyle(style);
                rows[3].createCell(rowCount).setCellStyle(style);
                rows[4].createCell(rowCount).setCellStyle(style);
                rows[5].createCell(rowCount).setCellStyle(style);
                rows[6].createCell(rowCount).setCellStyle(style);
                rows[7].createCell(rowCount).setCellStyle(style);
                rows[8].createCell(rowCount).setCellStyle(style);

            }

            if (this.week.getHome().getRoom1().getVacuumLastDate().getTime() - new Date().getTime() > 1) {
                rowCount++;
                sheet.addMergedRegion(new CellRangeAddress(0, 1, rowCount, rowCount));
                sheet.addMergedRegion(new CellRangeAddress(2, 8, rowCount, rowCount));
                rows[1].createCell(rowCount).setCellStyle(style);
                cell = rows[0].createCell(rowCount);
                cell.setCellValue("Room1 Vacuum");
                cell.setCellStyle(style);

                cell = rows[2].createCell(rowCount);
                cell.setCellValue(this.week.getHome().getRoom1().getVacuumCleaner());
                cell.setCellStyle(style);
                rows[3].createCell(rowCount).setCellStyle(style);
                rows[4].createCell(rowCount).setCellStyle(style);
                rows[5].createCell(rowCount).setCellStyle(style);
                rows[6].createCell(rowCount).setCellStyle(style);
                rows[7].createCell(rowCount).setCellStyle(style);
                rows[8].createCell(rowCount).setCellStyle(style);

            }

            if (this.week.getHome().getRoom1().getBathroomCleanLastDate().getTime() - new Date().getTime() > 1) {
                rowCount++;
                sheet.addMergedRegion(new CellRangeAddress(0, 1, rowCount, rowCount));
                sheet.addMergedRegion(new CellRangeAddress(2, 8, rowCount, rowCount));
                rows[1].createCell(rowCount).setCellStyle(style);
                cell = rows[0].createCell(rowCount);
                cell.setCellValue("Room1 Bathroom");
                cell.setCellStyle(style);

                cell = rows[2].createCell(rowCount);
                cell.setCellValue(this.week.getHome().getRoom1().getBathroomCleaner());
                cell.setCellStyle(style);
                rows[3].createCell(rowCount).setCellStyle(style);
                rows[4].createCell(rowCount).setCellStyle(style);
                rows[5].createCell(rowCount).setCellStyle(style);
                rows[6].createCell(rowCount).setCellStyle(style);
                rows[7].createCell(rowCount).setCellStyle(style);
                rows[8].createCell(rowCount).setCellStyle(style);

            }

            if (this.week.getHome().getRoom2().getVacuumLastDate().getTime() - new Date().getTime() > 1) {
                rowCount++;
                sheet.addMergedRegion(new CellRangeAddress(0, 1, rowCount, rowCount));
                sheet.addMergedRegion(new CellRangeAddress(2, 8, rowCount, rowCount));
                rows[1].createCell(rowCount).setCellStyle(style);
                cell = rows[0].createCell(rowCount);
                cell.setCellValue("Room2 Vacuum");
                cell.setCellStyle(style);

                cell = rows[2].createCell(rowCount);
                cell.setCellValue(this.week.getHome().getRoom2().getVacuumCleaner());
                cell.setCellStyle(style);
                rows[3].createCell(rowCount).setCellStyle(style);
                rows[4].createCell(rowCount).setCellStyle(style);
                rows[5].createCell(rowCount).setCellStyle(style);
                rows[6].createCell(rowCount).setCellStyle(style);
                rows[7].createCell(rowCount).setCellStyle(style);
                rows[8].createCell(rowCount).setCellStyle(style);

            }

            if (this.week.getHome().getRoom2().getBathroomCleanLastDate().getTime() - new Date().getTime() > 1) {
                rowCount++;
                sheet.addMergedRegion(new CellRangeAddress(0, 1, rowCount, rowCount));
                sheet.addMergedRegion(new CellRangeAddress(2, 8, rowCount, rowCount));
                rows[1].createCell(rowCount).setCellStyle(style);
                cell = rows[0].createCell(rowCount);
                cell.setCellValue("Room2 Bathroom");
                cell.setCellStyle(style);

                cell = rows[2].createCell(rowCount);
                cell.setCellValue(this.week.getHome().getRoom2().getBathroomCleaner());
                cell.setCellStyle(style);
                rows[3].createCell(rowCount).setCellStyle(style);
                rows[4].createCell(rowCount).setCellStyle(style);
                rows[5].createCell(rowCount).setCellStyle(style);
                rows[6].createCell(rowCount).setCellStyle(style);
                rows[7].createCell(rowCount).setCellStyle(style);
                rows[8].createCell(rowCount).setCellStyle(style);

            }


            for (int i = 0; i <= rowCount; i++)
                sheet.autoSizeColumn(i, true);

            workbook.write(fileOut);


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (!new File(archiveLocation).exists())
            new File(archiveLocation).mkdir();

        try (OutputStream fileOut = new FileOutputStream(archiveLocation + "/recent_" + dateFormat.format(new Date()) + ".xlsx")) {
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
