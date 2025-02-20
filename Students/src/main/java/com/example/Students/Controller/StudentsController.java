package com.example.Students.Controller;

import com.example.Students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired  // Fix: Inject the StudentService properly
    private StudentService studentService;

    // GET all students
    @GetMapping
    public List<com.example.Students.model.Students> getStudentList() {
        return studentService.getStudentsList();
    }

    // GET student by ID
    @GetMapping("/{id}")
    public com.example.Students.model.Students getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    // POST: Add a new student
    @PostMapping
    public com.example.Students.model.Students addStudent(@RequestBody com.example.Students.model.Students student) {
        return studentService.addStudent(student);
    }

    // PUT: Update student by ID
    @PutMapping("/{id}")
    public com.example.Students.model.Students updateStudent(@PathVariable int id, @RequestBody com.example.Students.model.Students updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    // DELETE: Remove a student by ID
    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }

    // DELETE: Remove all students
    @DeleteMapping
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }
}
