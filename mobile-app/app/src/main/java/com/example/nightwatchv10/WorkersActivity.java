package com.example.nightwatchv10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WorkersActivity extends AppCompatActivity {
    ListView listView;
    Button btnAdd;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<JSONObject> objList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<Employee> employeesList = new ArrayList<>();
    ArrayList<Manager> managersList = new ArrayList<>();
    HttpUtils http = new HttpUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);
        listView = (ListView)findViewById(R.id.listview);
        btnAdd = findViewById(R.id.btnWorkerAdd);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nameList);

        try {
            JSONArray jsonArr1 = new JSONArray(http.SendGetRequest("http://10.0.2.2:8080/api/v1/managers/",null));
            for (int i = 0; i < jsonArr1.length(); i++)
            {
                JSONObject jsonObj = jsonArr1.getJSONObject(i);
                jsonObj =new JSONObject(http.SendGetRequest("http://10.0.2.2:8080/api/v1/managers/",((Integer)jsonObj.getInt("id")).toString()));
                managersList.add(new Manager(jsonObj));
                objList.add(jsonObj);
                nameList.add("Manager: " + jsonObj.getString("firstName")+" "+ (jsonObj.getString("middleName") == "null"? "":jsonObj.getString("middleName")+" ")+ jsonObj.getString("lastName"));
            }
            JSONArray jsonArr = new JSONArray(http.SendGetRequest("http://10.0.2.2:8080/api/v1/employees/",null));
            for (int i = 0; i < jsonArr.length(); i++)
            {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                jsonObj =new JSONObject(http.SendGetRequest("http://10.0.2.2:8080/api/v1/employees/",((Integer)jsonObj.getInt("id")).toString()));
                employeesList.add(new Employee(jsonObj));
                objList.add(jsonObj);
                nameList.add("Employee: " + jsonObj.getString("firstName")+" "+ jsonObj.getString("middleName")+" "+ jsonObj.getString("lastName"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage() + " xxxxxxxxxxxxxxxx");
        }

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                if (nameList.get(position).startsWith("Manager"))
                    workerpopup.showManagerPopupWindow(view, new Manager(objList.get(position)));
                else
                    workerpopup.showEmployeePopupWindow(view, new Employee(objList.get(position)));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showPopupWindow(v, null);
            }
        });
    }

}