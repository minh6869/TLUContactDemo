package com.example.tlucontactdemo.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tlucontactdemo.model.Student;
import com.example.tlucontactdemo.util.FirestoreInstance;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static StudentRepository instance;
    private MutableLiveData<List<Student>> studentsLiveData = new MutableLiveData<>();
    private FirebaseFirestore db = FirestoreInstance.getInstance();

    private StudentRepository() {
        // Simulate data from a database or API
        loadStudents();
    }

    public static synchronized StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    private void loadStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Nguyen Van A", "SV001", "test@gmail.com", "0123456789", 1));
        studentList.add(new Student(1, "Nguyen Van b", "SV001", "test@gmail.com", "0123456adsf789", 2));

        studentList.add(new Student(1, "Nguyen Van Ac", "SV001", "test@gmail.com", "01234dafasd56789", 3));

        studentList.add(new Student(1, "Nguyen Van Ad", "SV001", "test@gmail.com", "0123452232326789", 1));

        studentList.add(new Student(1, "Nguyen Van Aa", "SV001", "test@gmail.com", "0123gadvcz456789", 3));

        studentList.add(new Student(1, "Nguyen Van Adasf", "SV001", "test@gmail.com", "01234yjytj56789", 2));
        db.collection("students")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        studentList.clear(); // Xóa danh sách hiện tại nếu có
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            int id = document.getLong("id").intValue();
                            String name = document.getString("name");
                            String studentId = document.getString("studentId");
                            String email = document.getString("email");
                            String phone = document.getString("phone");
                            int departmentId = document.getLong("departmentId").intValue();
                            Log.d("DataRepository", "Student: " + name + ", ID: " + id);
                            studentList.add(new Student(id, name, studentId, email, phone, departmentId));
                        }
                    } else {
                        // Xử lý lỗi
                        Log.w("DataRepository", "Error getting documents.", task.getException());
                    }
                });

        studentsLiveData.setValue(studentList);
    }

    public LiveData<List<Student>> getAllStudents() {
        return studentsLiveData;
    }

    public LiveData<List<Student>> getStudentsByDepartment(int departmentId) {
        List<Student> allStudents = studentsLiveData.getValue();
        List<Student> filteredStudents = new ArrayList<>();

        if (allStudents != null) {
            for (Student student : allStudents) {
                if (student.getDepartmentId() == departmentId) {
                    filteredStudents.add(student);
                }
            }
        }

        MutableLiveData<List<Student>> result = new MutableLiveData<>();
        result.setValue(filteredStudents);
        return result;
    }
}