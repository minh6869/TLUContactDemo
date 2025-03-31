package com.example.tlucontactdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tlucontactdemo.model.Student;
import com.example.tlucontactdemo.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends ViewModel {
    private StudentRepository repository;
    private LiveData<List<Student>> allStudents;

    public StudentViewModel() {
        repository = StudentRepository.getInstance();
        allStudents = repository.getAllStudents();
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public LiveData<List<Student>> getStudentsByDepartment(int departmentId) {
        return repository.getStudentsByDepartment(departmentId);
    }
}
