package com.example.tlucontactdemo.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tlucontactdemo.model.Student;
import com.example.tlucontactdemo.util.FirestoreInstance;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

    public void getDepartmentId(DocumentReference departmentRef, OnDepartmentIdReceived callback) {
        if (departmentRef != null) {
            departmentRef.get().addOnSuccessListener(departmentDoc -> {
                if (departmentDoc.exists()) {
                    int departmentId = departmentDoc.getLong("id").intValue();
                    callback.onReceived(departmentId); // Gọi callback khi có dữ liệu
                }
            }).addOnFailureListener(e -> {
                Log.w("DataRepository", "Error getting department data", e);
            });
        }
    }

    // Định nghĩa interface callback
    public interface OnDepartmentIdReceived {
        void onReceived(int departmentId);
    }

    private void loadStudents() {
        List<Student> studentList = new ArrayList<>();
        List<Task<DocumentSnapshot>> taskList = new ArrayList<>();


        db.collection("students")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        studentList.clear(); // Xóa danh sách hiện tại nếu có
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            int id = document.getLong("id").intValue();
                            String name = document.getString("name");
                            Log.d("DataRepository", "Student name: " + name);
                            String studentId = document.getString("studentId");
                            String email = document.getString("email");
                            String phone = document.getString("phone");
                            DocumentReference departmentRef = document.getDocumentReference("departmentId");


                            if (departmentRef != null) {
                                // Lưu lại Task truy vấn department
                                Task<DocumentSnapshot> departmentTask = departmentRef.get();
                                taskList.add(departmentTask);

                                departmentTask.addOnSuccessListener(departmentDoc -> {
                                    int departmentId = departmentDoc.getLong("id").intValue();
                                    studentList.add(new Student(id, name, studentId, email, phone, departmentId));
                                });
                            }
                            Tasks.whenAllComplete(taskList).addOnCompleteListener(task1 -> {
                                Log.d("DataRepository", "All students loaded: " + studentList.size());
                                // Ở đây bạn có thể cập nhật UI hoặc xử lý tiếp

                            });


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