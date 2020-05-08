package com.example.androidproject;

import java.util.List;

public class RestApiResponse {

    List<Player> data;
    MetaData meta;
    List<Team> teamData;
    MetaTeam teamMeta;

    public List<Player> getData() {

        return data;
    }

    public MetaData getMeta() {
        return meta;
    }

    public List<Team> getTeamData() {
        return teamData;
    }

    public MetaTeam getTeamMeta() {
        return teamMeta;
    }
}
