package com.example.tlucontactdemo.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tlucontactdemo.model.Department;
import com.example.tlucontactdemo.util.FirestoreInstance;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {
    private static DepartmentRepository instance;
    private MutableLiveData<List<Department>> departmentsLiveData = new MutableLiveData<>();

    private FirebaseFirestore db = FirestoreInstance.getInstance();

    private DepartmentRepository() {
        // Simulate data from a database or API
        loadDepartments();
    }

    public static synchronized DepartmentRepository getInstance() {
        if (instance == null) {
            instance = new DepartmentRepository();
        }
        return instance;
    }

    private void loadDepartments() {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(new Department(1, "Computer Science", "CS","cs@gmail.com", "0123456789"));
        departmentList.add(new Department(2, "Electrical Engineering", "EE","ee@gmail.com", "0123456789"));
        departmentList.add(new Department(3, "Business Administration", "BA", "ba@gmail.com", "0123456789"));


        departmentsLiveData.setValue(departmentList);
    }

    public LiveData<List<Department>> getAllDepartments() {
        return departmentsLiveData;
    }

    public Department getDepartmentById(int id) {
        List<Department> departments = departmentsLiveData.getValue();
        if (departments != null) {
            for (Department department : departments) {
                if (department.getId() == id) {
                    return department;
                }
            }
        }
        return null;
    }
}