package com.example.uploadfile.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    private static final Logger LOGGER = Logger.getLogger(FileService.class.getName());
    private static final Path UPLOAD_DIR = Paths.get("uploads");

    public FileService() throws IOException {
        Files.createDirectories(UPLOAD_DIR);
    }

    // Store the uploaded file
    public String storeFile(MultipartFile file) throws IOException {
        Path targetLocation = UPLOAD_DIR.resolve(file.getOriginalFilename());
        Files.write(targetLocation, file.getBytes(), StandardOpenOption.CREATE);
        LOGGER.info("File saved at: " + targetLocation.toAbsolutePath());
        return file.getOriginalFilename();
    }

    // Get the file path for a given filename
    public Path getFilePath(String fileName) {
        return UPLOAD_DIR.resolve(fileName).toAbsolutePath();
    }

    // List all stored files
    public List<String> listFiles() {
        try (Stream<Path> paths = Files.list(UPLOAD_DIR)) {
            return paths.map(path -> path.getFileName().toString()).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.warning("Failed to list files: " + e.getMessage());
            return List.of();
        }
    }

    // Delete a file
    public void deleteFile(String fileName) throws IOException {
        Path filePath = getFilePath(fileName);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
            LOGGER.info("File deleted: " + fileName);
        } else {
            throw new IOException("File not found");
        }
    }
}
