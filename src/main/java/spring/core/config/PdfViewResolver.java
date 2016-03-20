package spring.core.config;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import spring.core.pdf.PDFView;

public class PdfViewResolver implements ViewResolver{

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        PDFView view = new PDFView();
        return view;
    }

}