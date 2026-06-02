package com.skillbridge.backend;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private String major;
    private String academicYear;
    private String location;
    private List<String> skills;
    private List<String> interests;

    public Student() {
    }

    public Student(int id, String name, String major, String academicYear, String location,
            List<String> skills, List<String> interests) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.academicYear = academicYear;
        this.location = location;
        this.skills = skills;
        this.interests = interests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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