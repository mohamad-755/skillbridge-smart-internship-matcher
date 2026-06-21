package com.skillbridge.backend.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Major is required")
    private String major;

    @NotBlank(message = "Academic year is required")
    private String academicYear;

    @NotBlank(message = "Location is required")
    private String location;

    @NotEmpty(message = "At least one skill is required")
    @ElementCollection
    private List<String> skills;

    @NotEmpty(message = "At least one interest is required")
    @ElementCollection
    private List<String> interests;

    public Student() {
    }

    public Student(Integer id, String name, String major, String academicYear, String location,
            List<String> skills, List<String> interests) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.academicYear = academicYear;
        this.location = location;
        this.skills = skills;
        this.interests = interests;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}