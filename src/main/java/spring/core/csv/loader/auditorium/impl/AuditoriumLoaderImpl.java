package spring.core.csv.loader.auditorium.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import spring.core.csv.loader.auditorium.AuditoriumLoader;
import spring.core.csv.reader.CsvFileReader;
import spring.core.data.Auditorium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Component
public class AuditoriumLoaderImpl implements AuditoriumLoader, ResourceLoaderAware {
    @Autowired
    CsvFileReader<Auditorium> csvFileReader;
    private ResourceLoader resourceLoader;
    private String resourceName;

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<Auditorium> loadAuditoriumList() {
        Resource banner = resourceLoader.getResource(getResourceName());

        try {
            return csvFileReader.readCsvFile(banner.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public List<Auditorium> loadAuditoriumList(InputStream fileInputStream) {
        return csvFileReader.readCsvFile(fileInputStream);
    }

    public String getResourceName() {
        return resourceName;
    }

    @Required
    @Override
    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }
}
