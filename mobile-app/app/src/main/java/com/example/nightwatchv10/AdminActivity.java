package com.example.nightwatchv10;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    Button btnWorkers, btnDepartments, btnUsers, btnRanks;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            Session.clearSession();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btnDepartments = findViewById(R.id.btnDepartments);
        btnWorkers = findViewById(R.id.btnWorkers);
        btnUsers = findViewById(R.id.btnUser);
        btnRanks = findViewById(R.id.btnRanks);
        btnRanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, RanksActivity.class);
                startActivity(intent);
            }
        });
        btnWorkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminActivity.this, "worker clicked", Toast.LENGTH_SHORT).show();
                System.out.println("sadasdasd");
                Intent intent = new Intent(AdminActivity.this, WorkersActivity.class);
                startActivity(intent);
            }
        });

        btnDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminActivity.this, "departments clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this, DepartmentActivity.class);
                startActivity(intent);
            }
        });
        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, UserActivity.class);
                startActivity(intent);

            }
        });
    }
//
//    public void managerScreen(View view){
//        startActivity(new Intent(this,ManagerActivity.class));
//
//    }

}