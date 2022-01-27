package com.example.nightwatchv10;

import com.example.nightwatchv10.Employee;

import org.json.JSONObject;

public class OffDay {
    Integer id;
    String begin;
    String end;
    Employee employee;
    public OffDay(JSONObject object) {
        this.id = (Integer) object.opt("id");
        this.begin = object.optString("begin");
        this.end = object.optString("end");
        this.employee = object.optJSONObject("employee") == null ? null:new Employee(object.optJSONObject("employee"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public OffDay() {
    }

}
