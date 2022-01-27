package com.example.nightwatchv10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String mes = intent.getStringExtra("adı");
        Toast.makeText(this,mes,Toast.LENGTH_SHORT).show();

//        btnss = findViewById(R.id.asd);
//
//        btnss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"sadfdsfdsfds",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}