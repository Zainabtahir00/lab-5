package com.example.Students.service;

import com.example.Students.model.Students;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Students> studentsList = new ArrayList<>();

    // Constructor with sample data
    public StudentService() {
        studentsList.add(new Students(32, "xsy", 30));
        studentsList.add(new Students(33, "xza", 20));
    }

    // Get all students
    public List<Students> getStudentsList() {
        return studentsList;
    }

    // Get a single student by ID
    public Students getStudentById(int id) {
        return studentsList.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }
//    public Students getStudentById(int id) {
//        for (Students student : studentsList) {
//            if (student.getId() == id) {
//                return student;  // Agar student mil gaya toh return karo
//            }
//        }
//        return null; // Agar koi student na mile toh null return karo
//    }
    // Add a new student
    public Students addStudent(Students student) {
        studentsList.add(student);
        return student;
    }

    // Update an existing student
    public Students updateStudent(int id, Students updatedStudent) {
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getId() == id) {
                studentsList.set(i, updatedStudent);
                return updatedStudent;
            }
        }
        return null;
    }

    // Delete a student by ID
    public boolean deleteStudent(int id) {
        return studentsList.removeIf(student -> student.getId() == id);
    }

    // Delete all students
    public void deleteAllStudents() {
        studentsList.clear();
    }
}
