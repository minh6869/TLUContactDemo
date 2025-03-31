package com.example.tlucontactdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tlucontactdemo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private static String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userRole = intent.getStringExtra("userRole");

        // Set up Navigation Controller with Bottom Navigation
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            // Setup top level destinations for AppBar
            AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(
                            R.id.departmentListFragment,
                            R.id.studentListFragment)
                            .build();

            // Setup ActionBar with NavController
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // Setup Bottom Navigation
            BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            NavigationUI.setupWithNavController(bottomNav, navController);
        }

        handleUserRole();
    }


    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    private void handleUserRole() {
        if ("admin".equals(userRole)) {
            // Thực hiện các hành động dành riêng cho admin
            navigateToAdminDashboard();
        } else if ("user".equals(userRole)) {
            // Thực hiện các hành động dành riêng cho user
            navigateToUserDashboard();
        }
    }

    private void navigateToAdminDashboard() {
        // Điều hướng hoặc hiển thị nội dung dành riêng cho admin
        Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();
        Log.d("AdminDashboard", "Admin dashboard opened");
        // Ví dụ: Hiển thị fragment hoặc activity dành riêng cho admin
    }

    private void navigateToUserDashboard() {
        // Điều hướng hoặc hiển thị nội dung dành riêng cho user
        Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
        // Ví dụ: Hiển thị fragment hoặc activity dành riêng cho user
    }
}