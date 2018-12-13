
package Book.javavt.view;

        import java.util.List;
        import java.util.Map;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import com.itextpdf.text.*;
        import com.itextpdf.text.pdf.PdfPCell;
        import com.itextpdf.text.pdf.PdfPTable;
        import com.itextpdf.text.pdf.PdfWriter;
        import Book.javavt.title.BookAutor;
        import Book.javavt.title.BookTitle;

public class PDFBuilder extends AbstractTextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> title, Document doc,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        // get data model which is passed by the Spring container
        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        paragraphFont.setSize(14);

        List<BookAutor> bookAutors = (List<BookAutor>) title.get("bookAutors");
        Paragraph autorParagraph = new Paragraph("Book Autors",paragraphFont);
        autorParagraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(autorParagraph);
        PdfPTable bookAutorsTable = getBookAutorTable(bookAutors);
        doc.add(bookAutorsTable);

        List<BookTitle> bookTitles = (List<BookTitle>) title.get("bookTitles");
        Paragraph titleParagraph = new Paragraph("Book title",paragraphFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(titleParagraph);
        PdfPTable bookTitlesTable = getBookTitleTable(bookTitles);
        doc.add(bookTitlesTable);
    }


    private PdfPTable getBookAutorTable(List<BookAutor> bookAutors) throws Exception {

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{0.4f, 2.0f, 1.0f, 4.0f});
        table.setSpacingBefore(10);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA, "ISO-8859-5");
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        // write table header
        cell.setPhrase(new Phrase("#", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Book name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Publisher", font));
        table.addCell(cell);

        // write table row data
        int index = 1;
        for (BookAutor bookAutor : bookAutors) {
            table.addCell("" + index);
            table.addCell(bookAutor.getName());
            table.addCell(bookAutor.getPublisher());
            index++;
        }
        return table;
    }

    private PdfPTable getBookTitleTable(List<BookTitle> bookTitles) throws Exception {

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{0.4f, 1.5f, 1.5f, 2.0f, 0.7f, 0.7f, 0.7f, 0.7f});
        table.setSpacingBefore(5);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA, "ISO-8859-5");
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        // write table header
        cell.setPhrase(new Phrase("#", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Autor", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Title", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Genre", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of pages", font));
        table.addCell(cell);

        int index = 1;
        // write table row data
        for (BookTitle bookTitle : bookTitles) {
            table.addCell("" + index);
            table.addCell(bookTitle.getBookAutor().getName());
            table.addCell(bookTitle.getTitleName());
            table.addCell(bookTitle.getGenre());
            table.addCell("" + bookTitle.getNumberofpages());
            index++;
        }
        return table;
    }
}