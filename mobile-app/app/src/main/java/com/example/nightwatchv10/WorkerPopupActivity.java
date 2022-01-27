package com.example.nightwatchv10;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerPopupActivity {


    //PopupWindow display method
    EditText nametxt;
    EditText mnametxt;
    EditText lnametxt;
    EditText mailtxt;
    EditText phonetxt;
    EditText usernametxt;
    EditText passwordtxt;

    Spinner roleIdtxt;
    Spinner userIdtxt;
    Spinner depIdtxt;
    Spinner rankIdtxt;

    private String[] roles;
    private String[] users;
    private String[] deps;
    private String[] ranks;
    private String[] mans;


    JSONArray deparr = new JSONArray();
    JSONArray rankarr = new JSONArray();
    JSONArray userarr = new JSONArray();
    JSONArray rolesarr = new JSONArray();
    JSONArray managersarr = new JSONArray();


    HttpUtils http = new HttpUtils();

    RadioButton rbmanager;
    RadioButton rbemployee;


    Button buttonEdit;
    Button buttonDelete;


    public void showPopupWindow(final View view, JSONObject object) {

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);

        rbmanager = popupView.findViewById(R.id.rdbmanager);
        rbemployee = popupView.findViewById(R.id.rdbemployee);
        rbmanager.setVisibility(View.VISIBLE);
        rbemployee.setVisibility(View.VISIBLE);

        rbemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmployeePopupWindow(view, new Employee());

            }
        });
        rbmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showManagerPopupWindow(view, new Manager());

            }
        });
        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        //Initialize the elements of our window, install the handler
        buttonEdit = popupView.findViewById(R.id.updateButton);
        buttonEdit.setVisibility(View.GONE);


        //Initialize the elements of our window, install the handler
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        buttonDelete.setVisibility(View.GONE);
    }

    public void showUserPopupWindow(final View view, User user) {

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);

        try {
            rolesarr = new JSONArray(http.SendGetRequest(Constant.API_URL + "/roles", ""));
            roles = new String[rolesarr.length()];
            for (int i = 0; i < rolesarr.length(); i++) {
                roles[i] = rolesarr.getJSONObject(i).getString("name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        usernametxt = popupView.findViewById(R.id.UsernameTextInput);
        passwordtxt = popupView.findViewById(R.id.passeordTextInput);
        roleIdtxt = popupView.findViewById(R.id.RoleIdTextInput);

        ArrayAdapter adapter = new ArrayAdapter((Activity) popupView.getContext(), android.R.layout.simple_spinner_item, roles);
        roleIdtxt.setAdapter(adapter);
        usernametxt.setVisibility(View.VISIBLE);
        passwordtxt.setVisibility(View.VISIBLE);
        roleIdtxt.setVisibility(View.VISIBLE);


        if (user.getUsername() != null) {
            usernametxt.setText(user.getUsername());
            int rolepos = 0;
            for (int i = 0; i < rolesarr.length(); i++) {
                if (roles[i].equals(user.getRole().getName())) rolepos = i;
            }
            roleIdtxt.setSelection(rolepos);
        }


        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        //Initialize the elements of our window, install the handler
        buttonEdit = popupView.findViewById(R.id.updateButton);
        if (user.getUsername() == null)
            buttonEdit.setText("Add");
        else
            buttonEdit.setText("Edit");
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("username", usernametxt.getText().toString());
                map.put("password", passwordtxt.getText().toString());
                for (int i = 0; i < rolesarr.length(); i++) {
                    if (roleIdtxt.getSelectedItem().equals(rolesarr.optJSONObject(i).optString("name"))) {
                        map.put("roleId", rolesarr.optJSONObject(i).opt("id").toString());
                    }
                }
                JSONObject jsonObject = new JSONObject(map);
                if (buttonEdit.getText().equals("Add")) {
                    http.SendPostRequest("http://10.0.2.2:8080/api/v1/users", jsonObject.toString());

                } else {
                    http.SendPutRequest("http://10.0.2.2:8080/api/v1/users/" + user.getId().toString(), jsonObject.toString());

                }
                Intent intent = new Intent(v.getContext(), UserActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });

        //Initialize the elements of our window, install the handler
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        buttonDelete.setText("Delete");
        if (user.getUsername() == null)
            buttonDelete.setVisibility(View.GONE);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http.SendDeleteRequest("http://10.0.2.2:8080/api/v1/users/" + user.getId().toString());
                Intent intent = new Intent(v.getContext(), UserActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });
    }

    public void showEmployeePopupWindow(final View view, Employee employee) {

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);
        try {
            deparr = new JSONArray(http.SendGetRequest(Constant.API_URL + "/departments", ""));

            deps = new String[deparr.length()];
            for (int i = 0; i < deparr.length(); i++) {
                deps[i] = deparr.getJSONObject(i).getString("name");
            }
            rankarr = new JSONArray(http.SendGetRequest(Constant.API_URL + "/ranks", ""));
            ranks = new String[rankarr.length()];
            for (int i = 0; i < rankarr.length(); i++) {
                ranks[i] = rankarr.getJSONObject(i).getString("name");
            }
            userarr = new JSONArray(http.SendGetRequest(Constant.API_URL + "/users", "?roleId=3&employeeId=null"));
            users = new String[userarr.length()];
            for (int i = 0; i < userarr.length(); i++) {
                users[i] = userarr.getJSONObject(i).getString("username");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        nametxt = popupView.findViewById(R.id.NameTextInput);
        nametxt.setVisibility(View.VISIBLE);
        mnametxt = popupView.findViewById(R.id.MiddleNameTextInput);
        mnametxt.setVisibility(View.VISIBLE);
        lnametxt = popupView.findViewById(R.id.LastNameTextInput);
        lnametxt.setVisibility(View.VISIBLE);
        phonetxt = popupView.findViewById(R.id.PhoneTextInput);
        phonetxt.setVisibility(View.VISIBLE);
        mailtxt = popupView.findViewById(R.id.EmailTextInput);
        mailtxt.setVisibility(View.VISIBLE);
        depIdtxt = popupView.findViewById(R.id.DepartmantIdTextInput);
        depIdtxt.setVisibility(View.VISIBLE);
        rankIdtxt = popupView.findViewById(R.id.RankIdTextInput);
        rankIdtxt.setVisibility(View.VISIBLE);
        userIdtxt = popupView.findViewById(R.id.UserIdTextInput);
        userIdtxt.setVisibility(View.VISIBLE);
        ArrayAdapter adapter = new ArrayAdapter((Activity) popupView.getContext(), android.R.layout.simple_spinner_item, deps);
        ArrayAdapter adapter1 = new ArrayAdapter((Activity) popupView.getContext(), android.R.layout.simple_spinner_item, ranks);
        ArrayAdapter adapter2 = new ArrayAdapter((Activity) popupView.getContext(), android.R.layout.simple_spinner_item, users);
        depIdtxt.setAdapter(adapter);
        rankIdtxt.setAdapter(adapter1);
        userIdtxt.setAdapter(adapter2);

        if (employee.getFirstName() != null) {
            nametxt.setText(employee.getFirstName());
            mnametxt.setText(employee.getMiddleName());
            lnametxt.setText(employee.getLastName());
            phonetxt.setText(employee.getPhone());
            mailtxt.setText(employee.getMail());
            depIdtxt.setSelection(employee.getDepartment().getId());
            int rankpos = 0;
            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i].equals(employee.getRank().getName())) rankpos = i;
            }
            rankIdtxt.setSelection(rankpos);
            int userpos = 0;
            for (int i = 0; i < users.length; i++) {
                if (users[i].equals(employee.getUser().getUsername())) userpos = i;
            }
            userIdtxt.setSelection(userpos);
            int deppos = 0;
            for (int i = 0; i < deps.length; i++) {
                if (deps[i].equals(employee.getDepartment().getName())) deppos = i;
            }
            depIdtxt.setSelection(deppos);
        }

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        //Initialize the elements of our window, install the handler
        buttonEdit = popupView.findViewById(R.id.updateButton);
        if (employee.getFirstName() != null)
            buttonEdit.setText("Edit");
        else
            buttonEdit.setText("Add");
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> map = new HashMap<>();
                map.put("firstName", nametxt.getText().toString());
                map.put("middleName", mnametxt.getText().toString());
                map.put("lastName", lnametxt.getText().toString());
                map.put("phone", phonetxt.getText().toString());
                map.put("mail", mailtxt.getText().toString());
                for (int i = 0; i < deps.length; i++) {
                    if (depIdtxt.getSelectedItem().equals(deparr.optJSONObject(i).optString("name"))) {
                        map.put("departmentId", deparr.optJSONObject(i).opt("id").toString());
                    }
                }
                for (int i = 0; i < ranks.length; i++) {
                    if (rankIdtxt.getSelectedItem().equals(rankarr.optJSONObject(i).optString("name"))) {
                        map.put("rankId", rankarr.optJSONObject(i).opt("id").toString());
                    }
                }
                for (int i = 0; i < userarr.length(); i++) {
                    if (userIdtxt.getSelectedItem().equals(userarr.optJSONObject(i).optString("username"))) {
                        map.put("userId", userarr.optJSONObject(i).opt("id").toString());
                    }
                }
                JSONObject jsonObject = new JSONObject(map);

                if (buttonEdit.getText().equals("Edit"))
                    http.SendPutRequest(Constant.EMPLOYEE_URL + "/" + employee.getId().toString(), jsonObject.toString());
                else http.SendPostRequest(Constant.EMPLOYEE_URL, jsonObject.toString());

                Intent intent = new Intent(v.getContext(), WorkersActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);

            }
        });

        //Initialize the elements of our window, install the handler
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        buttonDelete.setText("Delete");
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http.SendDeleteRequest("http://10.0.2.2:8080/api/v1/employees/" + employee.getId().toString());
                Intent intent = new Intent(v.getContext(), WorkersActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });
    }

    public void showManagerPopupWindow(final View view, Manager manager) {

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);
        try {
            userarr = new JSONArray(http.SendGetRequest(Constant.API_URL + "/users", "?roleId=2&managerId=null"));
            users = new String[userarr.length()];
            for (int i = 0; i < userarr.length(); i++) {
                users[i] = userarr.getJSONObject(i).getString("username");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        nametxt = popupView.findViewById(R.id.NameTextInput);
        nametxt.setVisibility(View.VISIBLE);
        mnametxt = popupView.findViewById(R.id.MiddleNameTextInput);
        mnametxt.setVisibility(View.VISIBLE);
        lnametxt = popupView.findViewById(R.id.LastNameTextInput);
        lnametxt.setVisibility(View.VISIBLE);
        phonetxt = popupView.findViewById(R.id.PhoneTextInput);
        phonetxt.setVisibility(View.VISIBLE);
        mailtxt = popupView.findViewById(R.id.EmailTextInput);
        mailtxt.setVisibility(View.VISIBLE);
        userIdtxt = popupView.findViewById(R.id.UserIdTextInput);
        userIdtxt.setVisibility(View.VISIBLE);

        ArrayAdapter adapter2 = new ArrayAdapter((Activity) popupView.getContext(), android.R.layout.simple_spinner_item, users);
        userIdtxt.setAdapter(adapter2);

        if (manager.getFirstName() != null) {
            nametxt.setText(manager.getFirstName());
            mnametxt.setText(manager.getMiddleName());
            lnametxt.setText(manager.getLastName());
            phonetxt.setText(manager.getPhone());
            mailtxt.setText(manager.getMail());
            int userpos = 0;
            for (int i = 0; i < users.length; i++) {
                if (users[i].equals(manager.getUser().getUsername())) userpos = i;
            }
            userIdtxt.setSelection(userpos);
        }

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        //Initialize the elements of our window, install the handler
        buttonEdit = popupView.findViewById(R.id.updateButton);
        if (manager.getFirstName() != null) buttonEdit.setText("Edit");
        else buttonEdit.setText("Add");
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Map<String, String> map = new HashMap<>();
                map.put("firstName", nametxt.getText().toString());
                map.put("middleName", mnametxt.getText().toString());
                map.put("lastName", lnametxt.getText().toString());
                map.put("phone", phonetxt.getText().toString());
                map.put("mail", mailtxt.getText().toString());
                for (int i = 0; i < userarr.length(); i++) {
                    if (userIdtxt.getSelectedItem().equals(userarr.optJSONObject(i).optString("username"))) {
                        map.put("userId", userarr.optJSONObject(i).opt("id").toString());
                    }
                }
                JSONObject jsonObject = new JSONObject(map);
                if (buttonEdit.getText().equals("Edit")) {
                    http.SendPutRequest("http://10.0.2.2:8080/api/v1/managers/" + manager.getId().toString(), jsonObject.toString());
                } else {
                    http.SendPostRequest("http://10.0.2.2:8080/api/v1/managers", jsonObject.toString());
                }
                Intent intent = new Intent(v.getContext(), WorkersActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);

            }
        });

        //Initialize the elements of our window, install the handler
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        buttonDelete.setText("Delete");
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http.SendDeleteRequest("http://10.0.2.2:8080/api/v1/managers/" + manager.getId().toString());
                Intent intent = new Intent(v.getContext(), WorkersActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });
    }

    public void showDepartmentPopupWindow(final View view, Department department) {
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);
        try {
            managersarr = new JSONArray(http.SendGetRequest(Constant.API_URL + "/managers", ""));
            mans = new String[managersarr.length()];
            for (int i = 0; i < managersarr.length(); i++) {
                mans[i] = managersarr.getJSONObject(i).getString("firstName") + " " +
                        managersarr.getJSONObject(i).getString("middleName") + " " +
                        managersarr.getJSONObject(i).getString("lastName");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        nametxt = popupView.findViewById(R.id.NameTextInput);
        nametxt.setHint("Name");
        nametxt.setVisibility(View.VISIBLE);
        lnametxt = popupView.findViewById(R.id.LastNameTextInput);
        lnametxt.setHint("Daily Watch");
        lnametxt.setVisibility(View.VISIBLE);
        userIdtxt = popupView.findViewById(R.id.UserIdTextInput);
        userIdtxt.setVisibility(View.VISIBLE);

        ArrayAdapter adapter2 = new ArrayAdapter((Activity) popupView.getContext(), android.R.layout.simple_spinner_item, mans);
        userIdtxt.setAdapter(adapter2);

        if (department.getName() != null) {
            nametxt.setText(department.getName());
            lnametxt.setText(department.getDailyWatch().toString());
            int userpos = 0;
            for (int i = 0; i < mans.length; i++) {
                if (mans[i].equals(department.getManager().getFirstName() + " " + department.getManager().getMiddleName() + " " + department.getManager().getLastName()))
                    userpos = i;
            }
            userIdtxt.setSelection(userpos);
        }
        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        //Initialize the elements of our window, install the handler
        buttonEdit = popupView.findViewById(R.id.updateButton);
        if (department.getName() != null) buttonEdit.setText("Edit");
        else buttonEdit.setText("Add");
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Map<String, String> map = new HashMap<>();
                map.put("name", nametxt.getText().toString());
                map.put("dailyWatch", lnametxt.getText().toString());
                for (int i = 0; i < managersarr.length(); i++) {
                    if (userIdtxt.getSelectedItem().equals(
                            managersarr.optJSONObject(i).optString("firstName") + " " +
                                    managersarr.optJSONObject(i).optString("middleName") + " " +
                                    managersarr.optJSONObject(i).optString("lastName"))) {
                        map.put("managerId", managersarr.optJSONObject(i).opt("id").toString());
                    }
                }
                JSONObject jsonObject = new JSONObject(map);
                if (buttonEdit.getText().equals("Edit")) {
                    http.SendPutRequest("http://10.0.2.2:8080/api/v1/departments/" + department.getId().toString(), jsonObject.toString());
                } else {
                    http.SendPostRequest("http://10.0.2.2:8080/api/v1/departments", jsonObject.toString());
                }
                Intent intent = new Intent(v.getContext(), DepartmentActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);

            }
        });

        //Initialize the elements of our window, install the handler
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        buttonDelete.setText("Delete");
        if (department.getName() == null) buttonDelete.setVisibility(View.GONE);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http.SendDeleteRequest("http://10.0.2.2:8080/api/v1/departments/" + department.getId().toString());
                Intent intent = new Intent(v.getContext(), DepartmentActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });


    }

    public void showRankPopupWindow(final View view, Rank rank) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);

        nametxt = popupView.findViewById(R.id.NameTextInput);
        nametxt.setHint("Name");
        nametxt.setVisibility(View.VISIBLE);
        lnametxt = popupView.findViewById(R.id.LastNameTextInput);
        lnametxt.setHint("Mandatory Day");
        lnametxt.setVisibility(View.VISIBLE);
        mailtxt = popupView.findViewById(R.id.EmailTextInput);
        mailtxt.setHint("Level");
        mailtxt.setVisibility(View.VISIBLE);
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        buttonEdit = popupView.findViewById(R.id.updateButton);
        buttonDelete.setText("DELETe");


        buttonDelete.setVisibility(View.GONE);
        buttonEdit.setText("Add");
        if (rank.getName() != null) {
            nametxt.setText(rank.getName());
            lnametxt.setText(rank.getMandatoryDay().toString());
            mailtxt.setText(rank.getLevel().toString());
            buttonDelete.setVisibility(View.VISIBLE);
            buttonEdit.setText("Edit");
        }


        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("name", nametxt.getText().toString());
                map.put("mandatoryDay", lnametxt.getText().toString());
                map.put("level", mailtxt.getText().toString());
                JSONObject jsonObject = new JSONObject(map);

                if (buttonEdit.getText().equals("Add")) {
                    http.SendPostRequest(Constant.API_URL + "/ranks", jsonObject.toString());
                } else {
                    http.SendPutRequest(Constant.API_URL + "/ranks/" + rank.getId().toString(), jsonObject.toString());
                }
                Intent intent = new Intent(v.getContext(), RanksActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http.SendDeleteRequest(Constant.API_URL + "/ranks/" + rank.getId().toString());

                Intent intent = new Intent(v.getContext(), RanksActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }

        });


    }


    public void showWatchPopupWindow(final View view, Watch watch, ArrayList<Employee> employee) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);

        nametxt = popupView.findViewById(R.id.NameTextInput);
        nametxt.setHint("Date");
        nametxt.setVisibility(View.VISIBLE);
        userIdtxt = popupView.findViewById(R.id.UserIdTextInput);
        userIdtxt.setVisibility(View.VISIBLE);

        List<String> employees = new ArrayList<>();
        for (Employee employee1 : employee) {
            employees.add(employee1.getFirstName() + " " + employee1.getMiddleName() + " " + employee1.getLastName());
        }

        ArrayAdapter adapter2 = new ArrayAdapter((Activity) popupView.getContext(), android.R.layout.simple_spinner_item, employees);
        userIdtxt.setAdapter(adapter2);

        if (watch.getId() != null) {
            nametxt.setText(watch.getDate());

            int userpos = 0;
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).equals(watch.getEmployee().getFirstName() + " " + watch.getEmployee().getMiddleName() + " " + watch.getEmployee().getLastName()))
                    userpos = i;
            }
            userIdtxt.setSelection(userpos);
        }

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        //Initialize the elements of our window, install the handler
        buttonEdit = popupView.findViewById(R.id.updateButton);
        if (watch.getDate() != null) buttonEdit.setText("Watch Edit");
        else {
            buttonEdit.setText("Watch Add");
        }
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Map<String, String> map = new HashMap<>();
                map.put("date", nametxt.getText().toString());
                for (int i = 0; i < employee.size(); i++) {
                    if (userIdtxt.getSelectedItem().equals(
                            employee.get(i).getFirstName() + " " +
                                    employee.get(i).getMiddleName() + " " +
                                    employee.get(i).getLastName())) {
                        map.put("employeeId", employee.get(i).getId().toString());
                    }
                }
                JSONObject jsonObject = new JSONObject(map);
                if (buttonEdit.getText().equals("Watch Edit")) {
                    http.SendPutRequest("http://10.0.2.2:8080/api/v1/watches/" + watch.getId().toString(), jsonObject.toString());
                } else {
                    http.SendPostRequest("http://10.0.2.2:8080/api/v1/watches", jsonObject.toString());
                }
                Intent intent = new Intent(v.getContext(), ManagerActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);

            }
        });

        //Initialize the elements of our window, install the handler
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        if (watch.getDate() == null) buttonDelete.setVisibility(View.GONE);
        buttonDelete.setText("Watch Delete");
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http.SendDeleteRequest("http://10.0.2.2:8080/api/v1/watches/" + watch.getId().toString());
                Intent intent = new Intent(v.getContext(), ManagerActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });
    }

    public void showOffDayPopupWindow(final View view, OffDay offDay) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);

        nametxt = popupView.findViewById(R.id.NameTextInput);
        nametxt.setHint("Begin");
        nametxt.setVisibility(View.VISIBLE);
        lnametxt = popupView.findViewById(R.id.LastNameTextInput);
        lnametxt.setHint("end");
        lnametxt.setVisibility(View.VISIBLE);

        if (offDay.getBegin() != null) {
            nametxt.setText(offDay.getBegin());
            lnametxt.setText(offDay.getEnd());


        }

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        //Initialize the elements of our window, install the handler
        buttonEdit = popupView.findViewById(R.id.updateButton);
        if (offDay.getBegin() != null) buttonEdit.setText("Off Day Edit");
        else buttonEdit.setText("Off Day Add");
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Map<String, String> map = new HashMap<>();
                map.put("begin", nametxt.getText().toString());
                map.put("end", lnametxt.getText().toString());
                map.put("employeeId", Session.selectOffDayEmployeeId);
                JSONObject jsonObject = new JSONObject(map);
                if (buttonEdit.getText().equals("Off Day Edit")) {
                    http.SendPutRequest("http://10.0.2.2:8080/api/v1/off-days/" + offDay.getId().toString(), jsonObject.toString());
                } else {
                    http.SendPostRequest("http://10.0.2.2:8080/api/v1/off-days", jsonObject.toString());
                }
                Intent intent = new Intent(v.getContext(), OffDayActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);

            }
        });

        //Initialize the elements of our window, install the handler
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        if (offDay.getBegin() == null) buttonDelete.setVisibility(View.GONE);
        buttonDelete.setText("Off Day Delete");
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http.SendDeleteRequest("http://10.0.2.2:8080/api/v1/off-days/" + offDay.getId().toString());
                Intent intent = new Intent(v.getContext(), OffDayActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });
    }

    public void showPrefferedDayPopupWindow(final View view, PreferredDay preferredDay) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_worker_popup, null);

        nametxt = popupView.findViewById(R.id.NameTextInput);
        nametxt.setHint("Date");
        nametxt.setVisibility(View.VISIBLE);


        if (preferredDay.getDate() != null) {
            nametxt.setText(preferredDay.getDate());
        }

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        //Initialize the elements of our window, install the handler
        buttonEdit = popupView.findViewById(R.id.updateButton);
        if (preferredDay.getDate() != null) buttonEdit.setText("Preferred Day Edit");
        else buttonEdit.setText("Preferred Day Add");
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Map<String, String> map = new HashMap<>();
                map.put("date", nametxt.getText().toString());
                map.put("employeeId", Session.employeeId.toString());
                JSONObject jsonObject = new JSONObject(map);
                if (buttonEdit.getText().equals("Preferred Day Edit")) {
                    http.SendPutRequest(Constant.PREFERRED_DAY_URL +"/"+ preferredDay.getId().toString(), jsonObject.toString());
                } else {
                    http.SendPostRequest(Constant.PREFERRED_DAY_URL, jsonObject.toString());
                }
                Intent intent = new Intent(v.getContext(), PreferredDayActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);

            }
        });

        //Initialize the elements of our window, install the handler
        buttonDelete = popupView.findViewById(R.id.deleteButton);
        if (preferredDay.getDate() == null) buttonDelete.setVisibility(View.GONE);
        buttonDelete.setText("Preferred Day Delete");
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http.SendDeleteRequest(Constant.PREFERRED_DAY_URL+"/" + preferredDay.getId().toString());
                Intent intent = new Intent(v.getContext(), PreferredDayActivity.class);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(intent);
            }
        });
    }

}