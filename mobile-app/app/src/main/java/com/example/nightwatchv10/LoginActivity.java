package com.example.nightwatchv10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText usernametxt, passwordtxt;
    HttpUtils httpUtils = new HttpUtils();

    public Map<String, Object> SendPostRequest(String urlString, String body) {
        Map<String, Object> response = new HashMap<>();
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");

            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            JSONObject jsonObject = new JSONObject(content.toString());

            String token = jsonObject.optString("token");


            response.put("code", con.getResponseCode());
            response.put("token", token);
            return response;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("code", 401);
            return response;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btnLogin = findViewById(R.id.btnLogin);
        usernametxt = findViewById(R.id.userName);
        passwordtxt = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> jsonMap = new HashMap<>();
                jsonMap.put("username", usernametxt.getText().toString());
                jsonMap.put("password", passwordtxt.getText().toString());
                JSONObject jsonObject = new JSONObject(jsonMap);
                Map<String, Object> result = SendPostRequest(Constant.API_URL + "/authentication", jsonObject.toString());
                if (result.get("code").equals(200)) {
                    Session.username = usernametxt.getText().toString();
                    Session.password = passwordtxt.getText().toString();
                    Session.token = "Bearer " + result.get("token");

                    try {
                        JSONArray arr = new JSONArray(httpUtils.SendGetRequest(Constant.API_URL + "/users?username=", Session.username).toString());
                        Session.userId = (Integer) arr.getJSONObject(0).opt("id");
                        Session.roleId = (Integer) arr.getJSONObject(0).optJSONObject("role").opt("id");
                        if (Session.roleId.equals(1)) {
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else if (Session.roleId.equals(2)) {
                            Session.managerId = (Integer) new JSONArray(httpUtils.SendGetRequest(Constant.MANAGER_URL, "?userId=" + Session.userId)).getJSONObject(0).opt("id");
                            Intent intent = new Intent(getApplicationContext(), ManagerDepartmentActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else if (Session.roleId.equals(3)) {
                            Session.employeeId = (Integer) new JSONArray(httpUtils.SendGetRequest(Constant.EMPLOYEE_URL, "?userId=" + Session.userId)).getJSONObject(0).opt("id");
                            Intent intent = new Intent(getApplicationContext(), WorkerScreenActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            System.out.println("Hiç Bişey olmadıysa mutlaka bişey olmuştur!");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else if (result.get("code").equals(401)) {
                    Toast.makeText(getApplicationContext(), "Username or password is incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}