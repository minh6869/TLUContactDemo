package com.example.tlucontactdemo.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontactdemo.R;
import com.example.tlucontactdemo.model.Department;
import com.example.tlucontactdemo.view.adapter.StudentAdapter;
import com.example.tlucontactdemo.viewmodel.DepartmentViewModel;
import com.example.tlucontactdemo.viewmodel.StudentViewModel;

public class StudentListFragment extends Fragment {
    private StudentViewModel studentViewModel;
    private DepartmentViewModel departmentViewModel;
    private StudentAdapter adapter;
    private TextView textViewTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewTitle = view.findViewById(R.id.text_view_title);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_students);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);

        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
        departmentViewModel = new ViewModelProvider(requireActivity()).get(DepartmentViewModel.class);

        int departmentId = -1;
        if (getArguments() != null) {
            departmentId = getArguments().getInt("departmentId", -1);
        }

        if (departmentId != -1) {
            // Show students for a specific department
            Department department = departmentViewModel.getDepartmentById(departmentId);
            if (department != null) {
                textViewTitle.setText("Students in " + department.getName());
            }

            studentViewModel.getStudentsByDepartment(departmentId).observe(
                    getViewLifecycleOwner(), students -> {
                        adapter.setStudents(students);
                    });
        } else {
            // Show all students
            textViewTitle.setText("All Students");
            studentViewModel.getAllStudents().observe(getViewLifecycleOwner(), students -> {
                adapter.setStudents(students);
            });
        }
    }
}