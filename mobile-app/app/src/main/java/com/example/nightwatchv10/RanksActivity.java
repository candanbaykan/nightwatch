package com.example.nightwatchv10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RanksActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<JSONObject> objList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<Employee> employeesList = new ArrayList<>();
    ArrayList<Rank> ranksList = new ArrayList<>();
    HttpUtils http = new HttpUtils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranks);
        listView = (ListView) findViewById(R.id.listview);
        btnAdd = findViewById(R.id.btnRankAdd);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        try {
            JSONArray jsonArr1 = new JSONArray(http.SendGetRequest("http://10.0.2.2:8080/api/v1/ranks", null));
            for (int i = 0; i < jsonArr1.length(); i++) {
                JSONObject jsonObj = jsonArr1.getJSONObject(i);
                jsonObj = new JSONObject(http.SendGetRequest("http://10.0.2.2:8080/api/v1/ranks/", ((Integer) jsonObj.getInt("id")).toString()));
                ranksList.add(new Rank(jsonObj));
                objList.add(jsonObj);
                nameList.add(jsonObj.getString("name"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage() + " xxxxxxxxxxxxxxxx");
        }
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showRankPopupWindow(view, new Rank(objList.get(position)));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showRankPopupWindow(v, new Rank());
            }
        });

    }
}