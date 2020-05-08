package com.example.androidproject;

import java.util.List;

public class Player {

    private Integer id;
    private String first_name;
    private String last_name;
    private Integer height_feet;
    private Integer height_inches;
    private Integer weight_pounds;
    private String position;
    private PlayerDataTeam team;

    public Integer getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Integer getHeight_feet() {
        return height_feet;
    }

    public Integer getHeight_inches() {
        return height_inches;
    }

    public Integer getWeight_pounds() {
        return weight_pounds;
    }

    public String getPosition() {
        return position;
    }

    public PlayerDataTeam getTeam() {
        return team;
    }
}
