package com.example.nightwatchv10;

import org.json.JSONArray;
import org.json.JSONObject;

public class Manager {

    /*
    {
      "id": 0,
      "firstName": "string",
      "middleName": "string",
      "lastName": "string",
      "phone": "string",
      "mail": "string",
      "departments": [
        {
          "id": 0,
          "name": "string",
          "dailyWatch": 0
        }
      ],
      "user": {
        "id": 0,
        "username": "string"
      }
    }
     */
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String mail;
    JSONArray departments;
    User user;


    public Manager(Integer id, String firstName, String middleName, String lastName, String phone, String mail, JSONArray departments, User user) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
        this.departments = departments;
        this.user = user;
    }

    public Manager() {
    }

    public Manager(JSONObject object) {
        this.id = (Integer) object.opt("id");
        this.firstName = object.optString("firstName");
        this.middleName = object.optString("middleName");
        this.lastName = object.optString("lastName");
        this.phone = object.optString("phone");
        this.mail = object.optString("mail");
        this.departments = object.optJSONArray("departments");
        JSONObject obj = object.optJSONObject("user");
        if (obj != null) this.user = new User(obj);
        if (this.user != null)
            this.user.setRole(new Role(2, "MANAGER"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public JSONArray getDepartments() {
        return departments;
    }

    public void setDepartments(JSONArray departments) {
        this.departments = departments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
