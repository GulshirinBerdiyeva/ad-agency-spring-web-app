package com.bsu.project.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
@PropertySource("classpath:appResourceLocation.properties")
public class ImageFileWriter implements Writer {
    private final Environment environment;

    @Autowired
    public ImageFileWriter(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void write(MultipartFile multipartFile, String fileName) throws DataException {
        String imageDirectoryPath = environment.getProperty("resource.imageDirectory");

        try {
            Path path = Path.of(imageDirectoryPath + File.separator + fileName);

            Files.write(path, multipartFile.getBytes());

        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}
