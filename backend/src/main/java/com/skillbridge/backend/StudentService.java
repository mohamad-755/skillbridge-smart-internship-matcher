package com.skillbridge.backend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student(1, "Hussein", "Computer Science", "Sophomore", "Beirut",
                Arrays.asList("Java", "Python", "Git"),
                Arrays.asList("Backend", "AI", "Internship")));
        students.add(new Student(2, "Rawad", "Computer Science", "Sophomore", "Beirut",
                Arrays.asList("Java", "SQL", "React"),
                Arrays.asList("Frontend", "Volunteering", "Internship")));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }
}