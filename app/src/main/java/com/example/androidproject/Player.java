package com.example.androidproject;

import java.util.List;

public class Player {

    private String id;
    private String first_name;
    private String last_name;
    private String height_feet;
    private String height_inches;
    private String weight_pounds;
    private String position;
    private PlayerDataTeam team;

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getHeight_feet() {
        return height_feet;
    }

    public String getHeight_inches() {
        return height_inches;
    }

    public String getWeight_pounds() {
        return weight_pounds;
    }

    public String getPosition() {
        return position;
    }

    public PlayerDataTeam getTeam() {
        return team;
    }
}
