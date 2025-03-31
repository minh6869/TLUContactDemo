package com.example.tlucontactdemo.model;

public class Student {
    private int id;
    private String name;
    private String studentId;

    private String email;
    private String phoneNumber;
    private int departmentId;

    public Student(int id, String name, String studentId, String email, String phoneNumber, int departmentId) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}