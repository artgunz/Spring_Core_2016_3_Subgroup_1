package spring.core.csv.loader.auditorium;

import org.springframework.beans.factory.annotation.Required;
import spring.core.data.Auditorium;

import java.io.InputStream;
import java.util.List;

public interface AuditoriumLoader {
    List<Auditorium> loadAuditoriumList();

    List<Auditorium> loadAuditoriumList(InputStream fileInputStream);

    @Required
    void setResourceName(String resourceName);
}
