package com.example.nightwatchv10;

import org.json.JSONObject;

public class Role {

    /*
    {
  "id": 0,
  "name": "string"
}
     */

    private Integer id;
    private  String name;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role( String name) {
        this.name = name;
    }
    public Role(JSONObject object) {
        this.id = (Integer)object.opt("id");
        this.name = object.optString("name");
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
}
