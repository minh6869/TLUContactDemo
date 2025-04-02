package com.example.tlucontactdemo.view;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tlucontactdemo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        buttonLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String emailOrUsername = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(emailOrUsername)) {
            editTextEmail.setError("Email or Username is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Password is required");
            return;
        }

        // Check if input is an email or username
        if (emailOrUsername.contains("@")) {
            // Email login
            loginWithEmail(emailOrUsername, password);
        } else {
            // Username login
            loginWithUsername(emailOrUsername, password);
        }
    }

    private void loginWithEmail(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        checkUserRole();
                    } else {
                        // Log the error message
                        Log.e("LoginActivity", "Login failed", task.getException());
                        Toast.makeText(LoginActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loginWithUsername(String username, String password) {
        db.collection("users")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        String email = document.getString("email");
                        loginWithEmail(email, password);
                    } else {
                        Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUserRole() {
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("users").document(userId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    String role = documentSnapshot.getString("role");
                    if ("admin".equals(role)) {
                        navigateToMainActivity("admin");
                    } else {
                        navigateToMainActivity("user");
                    }
                });
    }

    private void navigateToMainActivity(String role) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("userRole", role);
        startActivity(intent);
        finish();
    }
}