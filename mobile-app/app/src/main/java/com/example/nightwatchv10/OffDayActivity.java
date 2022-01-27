package com.example.nightwatchv10;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OffDayActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String> nameList = new ArrayList<>();
    List<OffDay> offDays = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    HttpUtils http = new HttpUtils();
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_day);
        listView = findViewById(R.id.offDayList);
        btnAdd = findViewById(R.id.addOffDay);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        String id = Session.selectOffDayEmployeeId;
        try {
            JSONArray jsonArray = new JSONArray(http.SendGetRequest("http://10.0.2.2:8080/api/v1/off-days", "?employeeId=" + id));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                offDays.add(new OffDay(object));
                nameList.add(object.getString("begin") + " " + object.getString("end"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage() + "  xxxxxxxxxxxxxxxx");
        }

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showOffDayPopupWindow(view, offDays.get(position));
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showOffDayPopupWindow(v, new OffDay());

            }
        });

    }
}