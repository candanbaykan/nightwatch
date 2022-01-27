package com.example.nightwatchv10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PreferredDayActivity extends AppCompatActivity {

    HttpUtils httpUtils = new HttpUtils();
    ListView listView;
    Button btnAddPrefDay;
    ArrayAdapter<String> arrayAdapter;
    List<String> nameList = new ArrayList<>();
    List<PreferredDay> preferredDays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_day);
        btnAddPrefDay = findViewById(R.id.addPreferredDay);
        listView = findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        try {
            JSONArray jsonArray = new JSONArray(httpUtils.SendGetRequest(Constant.PREFERRED_DAY_URL, "?sort=date,desc&employeeId=" + Session.employeeId));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                preferredDays.add(new PreferredDay(jsonObject));
                nameList.add(jsonObject.getString("date"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showPrefferedDayPopupWindow(view, preferredDays.get(position));
            }
        });
        btnAddPrefDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showPrefferedDayPopupWindow(v, new PreferredDay());
            }
        });

    }
}