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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<JSONObject> objList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<User> usersList = new ArrayList<>();
    HttpUtils http =new HttpUtils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        listView = (ListView)findViewById(R.id.listview);
        btnAdd = findViewById(R.id.btnUserAdd);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nameList);
        try {
            JSONArray jsonArr1 = new JSONArray(http.SendGetRequest("http://10.0.2.2:8080/api/v1/users/",null));
            for (int i = 0; i < jsonArr1.length(); i++)
            {
                JSONObject jsonObj = jsonArr1.getJSONObject(i);
                objList.add(jsonObj);
                usersList.add(new User(jsonObj));
                nameList.add(jsonObj.getInt("id") + " "+jsonObj.getString("username"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage() + " xxxxxxxxxxxxxxxx");
        }
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                    workerpopup.showUserPopupWindow(view, new User(objList.get(position)));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerPopupActivity workerpopup = new WorkerPopupActivity();
                workerpopup.showUserPopupWindow(v, new User());
            }
        });


    }

}