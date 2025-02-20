package com.example.StudentsRecords.Service;

import com.example.StudentsRecords.model.StudentDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentRecordService {

    private final List<StudentDetails> records = new ArrayList<>();
    private static final String UPLOAD_DIR = "C:/Users/CUI/uploads/"; // Change as needed

    public StudentDetails uploadRecord(StudentDetails record) {
        records.add(record);
        return record;
    }

    public String uploadFile(MultipartFile file) {
        try {
            // Ensure upload directory exists
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                return "Failed to create upload directory!";
            }

            // Generate unique filename
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File destFile = new File(uploadDir, uniqueFileName);
            file.transferTo(destFile);

            // Store metadata in records list
            StudentDetails record = new StudentDetails();
            record.setFileName(uniqueFileName);
            record.setStudentName("Test Student"); // You may replace this dynamically
            record.setUploadDate(LocalDate.now().toString());

            records.add(record);

            return "File uploaded successfully: " + destFile.getAbsolutePath();
        } catch (IOException e) {
            return "File upload failed: " + e.getMessage();
        }
    }

    public List<StudentDetails> getAllRecords() {
        return new ArrayList<>(records);
    }

    public StudentDetails getRecordByFileName(String fileName) {
        return records.stream()
                .filter(record -> record.getFileName().equalsIgnoreCase(fileName)) // ✅ Case-insensitive
                .findFirst()
                .orElse(null);
    }

    public String deleteRecord(String fileName) {
        boolean removed = records.removeIf(record -> record.getFileName().equalsIgnoreCase(fileName));
        return removed ? "Record deleted successfully" : "Record not found";
    }

    public String exportRecordsToCSV(String filePath) {
        File file = new File(filePath);
        try {
            // Ensure parent directory exists
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
                return "Error: Unable to create directory " + parentDir.getAbsolutePath();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // CSV Header
                writer.write("Student Name,File Name,Upload Date");
                writer.newLine();

                // CSV Data
                for (StudentDetails record : records) {
                    writer.write(record.getStudentName() + "," + record.getFileName() + "," + record.getUploadDate());
                    writer.newLine();
                }
            }

            return "CSV file created successfully: " + filePath;
        } catch (IOException e) {
            return "Error writing CSV file: " + e.getMessage();
        }
    }
}
