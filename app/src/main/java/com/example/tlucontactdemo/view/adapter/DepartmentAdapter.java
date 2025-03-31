package com.example.tlucontactdemo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontactdemo.R;
import com.example.tlucontactdemo.model.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {
    private List<Department> departments = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Department department);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_department, parent, false);
        return new DepartmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        Department currentDepartment = departments.get(position);
        holder.textViewName.setText(currentDepartment.getName());
        holder.textViewCode.setText(currentDepartment.getCode());
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    class DepartmentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewCode;

        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_department_name);
            textViewCode = itemView.findViewById(R.id.text_view_department_code);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(departments.get(position));
                }
            });
        }
    }
}