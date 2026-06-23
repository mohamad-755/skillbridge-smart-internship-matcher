package com.skillbridge.backend.controller;

import com.skillbridge.backend.model.Student;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.security.AuthContext;
import com.skillbridge.backend.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final AuthContext authContext;

    public StudentController(StudentService studentService, AuthContext authContext) {
        this.studentService = studentService;
        this.authContext = authContext;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/me")
    public Student getMyProfile(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        return studentService.getStudentByUserId(user.getId());
    }

    @PostMapping("/me")
    public Student saveMyProfile(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @Valid @RequestBody Student student) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        return studentService.saveProfileForUser(user, student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }
}