package com.example.nightwatchv10;

import org.json.JSONObject;

public class User {

    /*
    {
  "id": 0,
  "username": "string",
  "role": {
    "id": 0,
    "name": "string"
  }
}
     */

    private Integer id;
    private String username;
    Role role;


    public User(Integer id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public User() {
    }

    public User(JSONObject object) {
        this.id = (Integer) object.opt("id");
        this.username = object.optString("username");
        this.role = object.optJSONObject("role") != null ? new Role(object.optJSONObject("role")) : null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
