package com.skillbridge.backend.service;

import com.skillbridge.backend.model.Student;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.model.UserRole;
import com.skillbridge.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByUserId(Integer userId) {
        return studentRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Student profile not found"));
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student saveProfileForUser(User user, Student student) {
        if (user.getRole() != UserRole.STUDENT) {
            throw new SecurityException("Only students can create a student profile");
        }

        Student profile = studentRepository.findByUserId(user.getId()).orElse(student);

        profile.setUser(user);
        profile.setName(student.getName());
        profile.setMajor(student.getMajor());
        profile.setAcademicYear(student.getAcademicYear());
        profile.setLocation(student.getLocation());
        profile.setSkills(student.getSkills());
        profile.setInterests(student.getInterests());

        return studentRepository.save(profile);
    }
}