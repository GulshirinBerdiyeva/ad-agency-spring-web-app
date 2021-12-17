package com.bsu.project.data;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Gulshirin Berdiyeva
 */
public interface Writer {
    void write(MultipartFile multipartFile, String fileName) throws DataException;
}
