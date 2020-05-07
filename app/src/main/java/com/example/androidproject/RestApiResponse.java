package com.example.androidproject;

import java.util.List;

public class RestApiResponse {

    List<Player_data> playerData;
    List<Player_meta> playerMeta;
    List<Team_data> teamData;
    List<Team_meta> teamMeta;

    public List<Player_data> getPlayerData() {
        return playerData;
    }

    public List<Player_meta> getPlayerMeta() {
        return playerMeta;
    }

    public List<Team_data> getTeamData() {
        return teamData;
    }

    public List<Team_meta> getTeamMeta() {
        return teamMeta;
    }
}
