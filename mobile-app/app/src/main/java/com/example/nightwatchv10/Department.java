package com.example.nightwatchv10;

import org.json.JSONArray;
import org.json.JSONObject;

public class Department {


    /*

{
  "id": 0,
  "name": "string",
  "dailyWatch": 0,
  "manager": {
    "id": 0,
    "firstName": "string",
    "middleName": "string",
    "lastName": "string",
    "phone": "string",
    "mail": "string"
  },
  "employees": [
    {
      "id": 0,
      "firstName": "string",
      "middleName": "string",
      "lastName": "string",
      "phone": "string",
      "mail": "string"
    }
  ]
}



 */

    Integer id;
    String name;
    Integer dailyWatch;
    Manager manager;
    JSONArray employees;

    public Department(int id, String name, int dailyWatch, Manager manager, JSONArray employees) {
        this.id = id;
        this.name = name;
        this.dailyWatch = dailyWatch;
        this.manager = manager;
        this.employees = employees;
    }

    public Department(int id, String name, int dailyWatch) {
        this.id = id;
        this.name = name;
        this.dailyWatch = dailyWatch;
    }

    public Department(JSONObject jsonObject) {
        this.id = (Integer) jsonObject.opt("id");
        this.name = jsonObject.optString("name");
        this.dailyWatch = (Integer) jsonObject.opt("dailyWatch");
        this.manager = jsonObject.optJSONObject("manager") == null ? null : new Manager(jsonObject.optJSONObject("manager"));
        this.employees = jsonObject.optJSONArray("employees");
    }

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDailyWatch() {
        return dailyWatch;
    }

    public void setDailyWatch(Integer dailyWatch) {
        this.dailyWatch = dailyWatch;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public JSONArray getEmployees() {
        return employees;
    }

    public void setEmployees(JSONArray employees) {
        this.employees = employees;
    }
}
