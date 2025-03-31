package com.example.tlucontactdemo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontactdemo.R;
import com.example.tlucontactdemo.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> students = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Student student);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student currentStudent = students.get(position);
        holder.textViewName.setText(currentStudent.getName());
        holder.textViewId.setText(currentStudent.getStudentId());
        holder.imageButtonCall.setOnClickListener(v -> {
            // Handle call action
            Toast.makeText(v.getContext(), "Calling " + currentStudent.getPhoneNumber(), Toast.LENGTH_SHORT).show();
        });
        holder.imageButtonMessage.setOnClickListener(v -> {
            // Handle message action
        });
        holder.imageButtonEmail.setOnClickListener(v -> {
            // Handle email action
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewId;

        private ImageButton imageButtonCall;
        private ImageButton imageButtonMessage;
        private ImageButton imageButtonEmail;



        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_student_name);
            textViewId = itemView.findViewById(R.id.text_view_student_id);
            imageButtonCall = itemView.findViewById(R.id.image_button_call);
            imageButtonMessage = itemView.findViewById(R.id.image_button_message);
            imageButtonEmail = itemView.findViewById(R.id.image_button_email);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(students.get(position));
                }
            });
        }
    }
}