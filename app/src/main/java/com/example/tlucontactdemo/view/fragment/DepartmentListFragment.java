package com.example.tlucontactdemo.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontactdemo.R;
import com.example.tlucontactdemo.view.adapter.DepartmentAdapter;
import com.example.tlucontactdemo.viewmodel.DepartmentViewModel;

public class DepartmentListFragment extends Fragment {
    private DepartmentViewModel viewModel;
    private DepartmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_department_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_departments);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new DepartmentAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(DepartmentViewModel.class);
        viewModel.getAllDepartments().observe(getViewLifecycleOwner(), departments -> {
            adapter.setDepartments(departments);
        });

        adapter.setOnItemClickListener(department -> {
            Bundle bundle = new Bundle();
            bundle.putInt("departmentId", department.getId());
            Navigation.findNavController(view).navigate(
                    R.id.action_departmentListFragment_to_studentListFragment, bundle);
        });
    }
}