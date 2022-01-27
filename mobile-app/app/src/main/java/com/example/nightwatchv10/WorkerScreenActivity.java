package com.example.nightwatchv10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class WorkerScreenActivity extends AppCompatActivity {
    HttpUtils httpUtils = new HttpUtils();
    List<Watch> watchList = new ArrayList<>();
    ListView listView;
    Button btnPrefDay;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> nameList = new ArrayList<>();

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
        setContentView(R.layout.activity_worker_screen);

        listView = findViewById(R.id.listview);
        btnPrefDay = findViewById(R.id.btnPrefDay);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nameList);

        try {
            JSONArray jsonArray = new JSONArray(httpUtils.SendGetRequest(Constant.WATCH_URL, "?sort=date,desc&employeeId=" + Session.employeeId));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                watchList.add(new Watch(jsonObject));
                nameList.add(jsonObject.getString("date"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setAdapter(arrayAdapter);
       btnPrefDay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),PreferredDayActivity.class);
               startActivity(intent);
           }
       });

    }
}