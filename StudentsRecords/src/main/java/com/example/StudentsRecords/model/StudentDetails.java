package com.example.StudentsRecords.model;

public class StudentDetails {
    private String studentName;
    private String fileName;
    private String uploadDate;

    //Default constructor (Needed for Spring Boot serialization)
    public StudentDetails() {
    }

    // Constructor with parameters
    public StudentDetails(String studentName, String fileName, String uploadDate) {
        this.studentName = studentName;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }


    @Override
    public String toString() {
        return "StudentDetails{" +
                "studentName='" + studentName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                '}';
    }
}
