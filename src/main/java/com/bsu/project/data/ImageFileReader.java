package com.bsu.project.data;

import com.bsu.project.entity.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
@PropertySource("classpath:appResourceLocation.properties")
public class ImageFileReader implements Reader {
    private final Environment environment;

    @Autowired
    public ImageFileReader(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void read(Ad ad, HttpServletResponse response) throws DataException {
        try {
            String fileName = ad.getFileName();
            String imageDirectoryPath = environment.getProperty("resource.imageDirectory");

            File file = new File(imageDirectoryPath + File.separator + fileName);

            byte[] fileContent = Files.readAllBytes(file.toPath());

            response.setContentLength(fileContent.length);
            response.getOutputStream().write(fileContent);

        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }

    }
}
