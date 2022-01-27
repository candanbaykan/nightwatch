package com.example.nightwatchv10;

import org.json.JSONObject;

public class PreferredDay {

    Integer id;
    String date;
    Employee employee;

    public PreferredDay() {
    }

    public PreferredDay(JSONObject object) {
        this.id = (Integer) object.opt("id");
        this.date = object.optString("date");
        this.employee = object.optJSONObject("employee") == null ? null : new Employee(object);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
