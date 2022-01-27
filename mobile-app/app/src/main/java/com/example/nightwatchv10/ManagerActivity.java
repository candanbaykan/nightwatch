package com.example.nightwatchv10;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    Button workerlistbutton, datepickerbutton,addWatchButton;
    CalendarView calender;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<JSONObject> objList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> watchNameList = new ArrayList<>();
    ArrayList<Employee> employeesList = new ArrayList<>();
    ArrayList<Watch> watchList = new ArrayList<>();
    HttpUtils http = new HttpUtils();
    ListView listViewWatch;
    ArrayAdapter<String> arrayAdapterWatch;

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
        setContentView(R.layout.activity_manager);

        addWatchButton = findViewById(R.id.addWatch);
        listView = (ListView) findViewById(R.id.listview);
        listViewWatch = findViewById(R.id.listviewWatch);
        workerlistbutton = findViewById(R.id.workerlist);
        datepickerbutton = findViewById(R.id.datepicker);
        calender = findViewById(R.id.dateLayout);
        calender.setVisibility(View.GONE);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        arrayAdapterWatch = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, watchNameList);

        try {
            JSONObject jsonObj = new JSONObject(http.SendGetRequest("http://10.0.2.2:8080/api/v1/departments/", Session.selectedDepartmentId));
            JSONArray jsonArr1 = jsonObj.getJSONArray("employees");

            for (int j = 0; j < jsonArr1.length(); j++) {
                JSONObject jsonObject2 = jsonArr1.getJSONObject(j);
                objList.add(jsonObject2);
                employeesList.add(new Employee(jsonObject2));
                nameList.add(jsonObject2.getString("firstName") + " " + jsonObject2.getString("middleName") + " " + jsonObject2.getString("lastName"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "  xxxxxxxxxxxxxxxx");
        }

        listView.setAdapter(arrayAdapter);

        listViewWatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showWatchPopupWindow(view, watchList.get(position), employeesList);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),OffDayActivity.class);
                Session.selectOffDayEmployeeId = employeesList.get(position).getId().toString();
                startActivity(intent);

            }
        });

        addWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showWatchPopupWindow(v,new Watch(), employeesList);

            }
        });
        workerlistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);
                calender.setVisibility(View.GONE);
                listViewWatch.setVisibility(View.GONE);
                addWatchButton.setVisibility(View.GONE);
            }
        });
        datepickerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender.setVisibility(View.VISIBLE);
                listViewWatch.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                addWatchButton.setVisibility(View.VISIBLE);
            }
        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //http://localhost:8080/api/v1/departments/6/watches
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                try {
                    watchList = new ArrayList<>();
                    watchNameList = new ArrayList<>();
                    // TODO: id null geliyor
                    JSONArray watches = new JSONArray(http.SendGetRequest(Constant.API_URL + "/departments/" +
                            Session.selectedDepartmentId + "/", "watches"));
                    for (int i = 0; i < watches.length(); i++) {
                        JSONObject obj = watches.getJSONObject(i);
                        String date = obj.getString("date");
                        String[] split = date.split("-");
                        if (Integer.parseInt(split[0]) == year && Integer.parseInt(split[1]) == (month + 1) && Integer.parseInt(split[2]) == dayOfMonth) {
                            watchList.add(new Watch(obj));
                            watchNameList.add(obj.getJSONObject("employee").getString("firstName") + " " + obj.getJSONObject("employee").getString("middleName") + " " + obj.getJSONObject("employee").getString("lastName"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listViewWatch.setVisibility(View.VISIBLE);
                arrayAdapterWatch = new ArrayAdapter<String>(ManagerActivity.this, android.R.layout.simple_list_item_1, watchNameList);

                listViewWatch.setAdapter(arrayAdapterWatch);

            }

        });
    }
}