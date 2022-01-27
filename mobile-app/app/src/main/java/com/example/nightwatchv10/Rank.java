package com.example.nightwatchv10;

import org.json.JSONObject;

public class Rank {

/*
{
  "id": 0,
  "name": "string",
  "mandatoryDay": 0,
  "level": 0
}
 */
    private Integer id;
    private String name;
    private Integer mandatoryDay;
    private Integer level;

    public Rank() {
    }

    public Rank(Integer id, String name, Integer mandatoryDay, Integer level) {
        this.id = id;
        this.name = name;
        this.mandatoryDay = mandatoryDay;
        this.level = level;
    }
    public Rank(JSONObject object) {
        this.id = (Integer) object.opt("id");
        this.name = object.optString("name");
        this.mandatoryDay = (Integer) object.opt("mandatoryDay");
        this.level = (Integer) object.opt("level");
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

    public Integer getMandatoryDay() {
        return mandatoryDay;
    }

    public void setMandatoryDay(Integer mandatoryDay) {
        this.mandatoryDay = mandatoryDay;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
