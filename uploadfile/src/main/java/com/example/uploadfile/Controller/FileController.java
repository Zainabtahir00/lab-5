package com.example.uploadfile.Controller;

import com.example.uploadfile.Service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/files")
public class FileController {

    private static final Logger LOGGER = Logger.getLogger(FileController.class.getName());
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // Upload File
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileService.storeFile(file);
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            LOGGER.severe("Upload failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    // List All Files
    @GetMapping
    public ResponseEntity<List<String>> listFiles() {
        return ResponseEntity.ok(fileService.listFiles());
    }

    // Delete File
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        try {
            fileService.deleteFile(fileName);
            return ResponseEntity.ok("File deleted successfully: " + fileName);
        } catch (IOException e) {
            LOGGER.warning("File not found: " + fileName);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }
}
