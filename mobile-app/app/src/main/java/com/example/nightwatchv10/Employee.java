package com.example.nightwatchv10;

import org.json.JSONObject;

public class Employee {


    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String mail;
    private Department department;
    private Rank rank;
    private User user;

    public Employee() {
    }

    public Employee(Integer id, String firstName, String middleName, String lastName, String phone, String mail, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee(JSONObject object) {
        this.id = (Integer) object.opt("id");
        this.firstName = object.optString("firstName");
        this.middleName = object.optString("middleName");
        this.lastName = object.optString("lastName");
        this.phone = object.optString("phone");
        this.mail = object.optString("mail");
        this.user = object.optJSONObject("user") != null ? new User(object.optJSONObject("user")) : null;
        this.department = object.optJSONObject("department") != null ? new Department(object.optJSONObject("department")) : null;
        this.rank = object.optJSONObject("rank") != null ? new Rank(object.optJSONObject("rank")) : null;
        if (this.user != null)
            this.user.setRole(new Role(3, "EMPLOYEE"));
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
