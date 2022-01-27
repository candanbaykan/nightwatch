package com.example.nightwatchv10;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ManagerDepartmentActivity extends AppCompatActivity {
    TableLayout departmentTable;
    Button addDepartmentBtn;
    HttpUtils http = new HttpUtils();
    ArrayList<String> departmentsIdList = new ArrayList<>();
    ArrayList<String> departmentsNameList = new ArrayList<>();
    ArrayList<Department> departmentsList = new ArrayList<>();
    int buttonIdNumber = 0;

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
        setContentView(R.layout.activity_manager_department);


        try {
            JSONArray jsonArr = new JSONArray(http.SendGetRequest("http://10.0.2.2:8080/api/v1/departments", "?sort=name&managerId=" + Session.managerId));
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                departmentsList.add(new Department(jsonObj));
                departmentsNameList.add(jsonObj.getString("name"));
                departmentsIdList.add(jsonObj.getString("id"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "  xxxxxxxxxxxxxxxx");
        }

        int numberOfRows = ((int) departmentsIdList.toArray().length / 3) + 1;
        int numberOfButtonsPerRow = 3;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        final LinearLayout verticalLayout = (LinearLayout) findViewById(R.id.buttonlayout);
        int count = 0;
        for (int i = 0; i < numberOfRows; i++) {
            LinearLayout newLine = new LinearLayout(this);
            newLine.setLayoutParams(params);
            newLine.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < numberOfButtonsPerRow; j++) {
                if (count == departmentsNameList.toArray().length)
                    break;
                ;
                Button button = new Button(this);
                // You can set button parameters here:
                button.setWidth(475);
                Integer btnId = Integer.parseInt(departmentsIdList.get(count));
                button.setId(btnId);
                button.setLayoutParams(params);
                button.setText(departmentsNameList.get(count++));
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent is = new Intent(getApplicationContext(), ManagerActivity.class);
                        Session.selectedDepartmentId = btnId.toString();

                        startActivity(is);
                    }
                });

                newLine.addView(button);

            }
            verticalLayout.addView(newLine);


        }
    }
}