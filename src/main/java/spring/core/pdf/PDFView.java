package spring.core.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import spring.core.data.UserTicket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PDFView extends AbstractPdfView {

    protected void buildPdfDocument(
            Map<String, Object> model,
            Document document,
            PdfWriter writer,
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {


        // Get data "articles" from model
        @SuppressWarnings("unchecked")
        List<UserTicket> articles = (List<UserTicket>) model.get("tickets");

        // Fonts
        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
        Font fontTag = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);

        for (UserTicket article : articles) {

            // 1.Title
            document.add(new Chunk("Event: "));
            Chunk title = new Chunk(article.getShowEvent().getEvent().getName(), fontTitle);
            document.add(title);
            document.add(new Chunk(" "));

            // -- newline
            document.add(Chunk.NEWLINE);

            // 2.URL
            document.add(new Chunk("Auditorium: "));
            Chunk audit = new Chunk(article.getShowEvent().getAuditorium().getName(), fontTitle);
            document.add(audit);
            document.add(new Chunk(" "));

            // -- newline
            document.add(Chunk.NEWLINE);

            document.add(new Chunk("Seat: "));
            Chunk seat = new Chunk(article.getSeat().getId().toString(), fontTitle);
            document.add(seat);
            document.add(new Chunk(" "));

            document.add(Chunk.NEWLINE);

            document.add(new Chunk("Price: "));
            Chunk price = new Chunk(article.getPrice().getValue().toString(), fontTitle);
            document.add(price);
            document.add(new Chunk(" "));

            // -- newline
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

        }


    }


}
