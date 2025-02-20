package com.example.StudentsRecords.controller;

import com.example.StudentsRecords.Service.StudentRecordService;
import com.example.StudentsRecords.model.StudentDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/records")
public class StudentsRecordController {

    private final StudentRecordService studentRecordService;

    public StudentsRecordController(StudentRecordService studentRecordService) {
        this.studentRecordService = studentRecordService;
    }

    @PostMapping("/upload")
    public StudentDetails uploadRecord(@RequestBody StudentDetails record) {
        return studentRecordService.uploadRecord(record);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty. Please upload a valid file.");
        }
        String response = studentRecordService.uploadFile(file);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<StudentDetails> getAllRecords() {
        return studentRecordService.getAllRecords();
    }

    @GetMapping("/{fileName}")
    public StudentDetails getRecordByFileName(@PathVariable String fileName) {
        return studentRecordService.getRecordByFileName(fileName);
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> deleteRecord(@PathVariable String fileName) {
        String response = studentRecordService.deleteRecord(fileName);
        return response.equals("Record deleted successfully") ?
                ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportRecordsToCSV(@RequestParam String filePath) {
        String response = studentRecordService.exportRecordsToCSV(filePath);
        return ResponseEntity.ok(response);
    }
}
