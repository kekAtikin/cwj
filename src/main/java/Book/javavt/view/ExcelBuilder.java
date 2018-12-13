package Book.javavt.view;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Book.javavt.title.BookAutor;
import Book.javavt.title.BookTitle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBuilder extends AbstractPOIExcelView {
    @Override
    protected XSSFWorkbook createWorkbook() {
        return new XSSFWorkbook();
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> title, XSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring container
        List<BookAutor> bookAutors = (List<BookAutor>) title.get("bookAutors");
        List<BookTitle> bookTitles = (List<BookTitle>) title.get("bookTitles");

        generateAutorSheet(workbook, bookAutors);
        generateModelsSheet(workbook, bookTitles);
    }

    private void generateAutorSheet(XSSFWorkbook workbook, List<BookAutor> bookAutors){

        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("Books");
        sheet.setColumnWidth(0,2000);
        sheet.setColumnWidth(1,5000);
        sheet.setColumnWidth(2,3000);
        sheet.setColumnWidth(3,10000);

        Font font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        font.setFontName("Helvetica");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(font);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);

        // create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("№");
        headerRow.getCell(0).setCellStyle(headerStyle);

        headerRow.createCell(1).setCellValue("Book name");
        headerRow.getCell(1).setCellStyle(headerStyle);

        headerRow.createCell(3).setCellValue("Publisher");
        headerRow.getCell(3).setCellStyle(headerStyle);

        CellStyle rowCellStyle = workbook.createCellStyle();
        rowCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        // create data rows
        int rowCount = 1;
        for (BookAutor bookAutor : bookAutors) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(rowCount-1);
            row.getCell(0).setCellStyle(rowCellStyle);

            row.createCell(1).setCellValue(bookAutor.getName());
            row.getCell(1).setCellStyle(rowCellStyle);

            row.createCell(3).setCellValue(bookAutor.getPublisher());
            row.getCell(3).setCellStyle(rowCellStyle);
        }
    }

    private void generateModelsSheet(XSSFWorkbook workbook, List<BookTitle> bookTitles){

        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("Models");
        sheet.setColumnWidth(0,2000);
        sheet.setColumnWidth(1,5000);
        sheet.setColumnWidth(2,5000);
        sheet.setColumnWidth(3,7000);
        sheet.setColumnWidth(4,2500);
        sheet.setColumnWidth(5,2000);
        sheet.setColumnWidth(6,2000);
        sheet.setColumnWidth(7,2500);

        Font font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        font.setFontName("Helvetica");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(font);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);

        // create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("№");
        headerRow.getCell(0).setCellStyle(headerStyle);

        headerRow.createCell(1).setCellValue("Autor");
        headerRow.getCell(1).setCellStyle(headerStyle);

        headerRow.createCell(2).setCellValue("Model");
        headerRow.getCell(2).setCellStyle(headerStyle);

        headerRow.createCell(3).setCellValue("Generation");
        headerRow.getCell(3).setCellStyle(headerStyle);

        headerRow.createCell(4).setCellValue("Prod year");
        headerRow.getCell(4).setCellStyle(headerStyle);

        headerRow.createCell(5).setCellValue("Doors");
        headerRow.getCell(5).setCellStyle(headerStyle);

        headerRow.createCell(6).setCellValue("Seats");
        headerRow.getCell(6).setCellStyle(headerStyle);

        headerRow.createCell(7).setCellValue("Max speed");
        headerRow.getCell(7).setCellStyle(headerStyle);

        CellStyle rowCellStyle = workbook.createCellStyle();
        rowCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        // create data rows
        int rowCount = 1;
        for (BookTitle bookTitle : bookTitles) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(rowCount-1);
            row.getCell(0).setCellStyle(rowCellStyle);

            row.createCell(1).setCellValue(bookTitle.getBookAutor().getName());
            row.getCell(1).setCellStyle(rowCellStyle);

            row.createCell(2).setCellValue(bookTitle.getTitleName());
            row.getCell(2).setCellStyle(rowCellStyle);

            row.createCell(3).setCellValue(bookTitle.getGenre());
            row.getCell(3).setCellStyle(rowCellStyle);

            row.createCell(4).setCellValue(bookTitle.getNumberofpages());
            row.getCell(4).setCellStyle(rowCellStyle);
        }
    }

}
