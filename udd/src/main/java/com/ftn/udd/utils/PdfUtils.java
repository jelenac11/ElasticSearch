package com.ftn.udd.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PdfUtils {

	private static final String DOCS_PATH = "src/main/resources/documents/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    
	public static String upload(MultipartFile pdfFile) throws IOException {
        if (!pdfFile.isEmpty()) {
            byte[] bytes = pdfFile.getBytes();

            LocalDateTime created = LocalDateTime.now();
            String pdfName = created.format(FORMATTER) + "_" + pdfFile.getOriginalFilename();

            Path path = Paths.get(DOCS_PATH + File.separator + pdfName);
            
            Files.write(path, bytes);

            return path.toString();
        }
        return null;
    }
	
}
