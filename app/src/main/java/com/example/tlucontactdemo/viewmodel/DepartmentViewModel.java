package com.example.tlucontactdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tlucontactdemo.model.Department;
import com.example.tlucontactdemo.repository.DepartmentRepository;

import java.util.List;

public class DepartmentViewModel extends ViewModel {
    private final DepartmentRepository repository;
    private final LiveData<List<Department>> allDepartments;

    public DepartmentViewModel() {
        repository = DepartmentRepository.getInstance();
        allDepartments = repository.getAllDepartments();
    }

    public LiveData<List<Department>> getAllDepartments() {
        return allDepartments;
    }

    public Department getDepartmentById(int id) {
        return repository.getDepartmentById(id);
    }
}
