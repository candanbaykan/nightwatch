package com.example.nightwatchv10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DepartmentEditActivity extends AppCompatActivity {
    ListView listView;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<JSONObject> objList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<Employee> employeesList = new ArrayList<>();
    ArrayList<Manager> managersList = new ArrayList<>();
    Department dep;

    Button btnEditDepartment;
    Button btnDeleteDepartment;

    HttpUtils http = new HttpUtils();
    @Override
    public void onBackPressed() {
       Intent intent = new Intent(getApplicationContext(),DepartmentActivity.class);
       startActivity(intent);
       finishAffinity();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departmentedit);

        listView = findViewById(R.id.listviewDepartment);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nameList);
        listView.setAdapter(arrayAdapter);
        btnEditDepartment = findViewById(R.id.btnDepartmentEdit);
        btnDeleteDepartment = findViewById(R.id.btnDepartmentDelete);



        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        try {
            JSONObject jobj = new JSONObject(http.SendGetRequest("http://10.0.2.2:8080/api/v1/departments/"+id,null));
            dep = new Department(jobj);
            JSONObject jsonObj = jobj.getJSONObject("manager");
            objList.add(jsonObj);
            managersList.add(new Manager(jsonObj));
            nameList.add("Yönetici: " + jsonObj.getString("firstName")+" "+ (jsonObj.getString("middleName") == "null"? "":jsonObj.getString("middleName")+" ")+ jsonObj.getString("lastName"));
            JSONArray jarr = new JSONArray(jobj.getString("employees"));
            for (int i = 0; i < jarr.length(); i++)
            {
                jsonObj = jarr.getJSONObject(i);
                jsonObj.put("department",jobj);
                objList.add(jsonObj);
                employeesList.add(new Employee(jsonObj));
                nameList.add("Çalışan: " + jsonObj.getString("firstName")+" "+ (jsonObj.getString("middleName") == "null"? "":jsonObj.getString("middleName")+" ")+ jsonObj.getString("lastName"));

                //workerList.add(jsonObj.getString("firstName") + " " + jsonObj.getString("middleName")+ " " + jsonObj.getString("lastName"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage() + "  xxxxxxxxxxxxxxxx");
        }

        listView.setAdapter(arrayAdapter);

        btnEditDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showDepartmentPopupWindow(v, dep);
            }
        });
        btnDeleteDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               http.SendDeleteRequest(Constant.API_URL+"/departments/"+dep.getId().toString());
                Intent intent = new Intent(v.getContext(), DepartmentActivity.class);
                ((Activity) v.getContext()).finish();
                startActivity(intent);
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
//                if (nameList.get(position).startsWith("Yönetici"))
//                    workerpopup.showManagerPopupWindow(view, new Manager(objList.get(position)));
//                else
//                    workerpopup.showEmployeePopupWindow(view, new Employee(objList.get(position-managersList.size()+1)));
//            }
//        });

    }
}