package com.vehicleHireCompany.hireService.utils;

import java.io.InputStream;
import java.util.Optional;

public class FileUtils {

    public InputStream getFileFromResourceAsStream(Optional<String> fileName) {

        ClassLoader cl = getClass().getClassLoader();

        if (fileName.isPresent()) {
            InputStream inputStream = cl.getResourceAsStream(fileName.get());

            if (inputStream == null) {
                throw new IllegalArgumentException("File not found! " + fileName.get());
            } else {
                return inputStream;
            }
        }

        throw new IllegalArgumentException("File name is null");
    }
}
