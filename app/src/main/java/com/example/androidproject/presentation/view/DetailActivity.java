package com.example.androidproject.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.R;
import com.example.androidproject.Singletons;
import com.example.androidproject.presentation.model.Player;

public class DetailActivity extends AppCompatActivity {

    private TextView txtNameDetail;
    private TextView txtAbbrDetail;
    private TextView txtFullNameDetail;
    private TextView txtCityDetail;
    private TextView txtConferenceDetail;
    private TextView txtDivisionDetail;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_detail);

        txtNameDetail = findViewById(R.id.name_txt);
        txtAbbrDetail = findViewById(R.id.abbreviation_txt);
        txtFullNameDetail = findViewById(R.id.full_name_txt);
        txtCityDetail = findViewById(R.id.city_txt);
        txtConferenceDetail = findViewById(R.id.conference_txt);
        txtDivisionDetail = findViewById(R.id.division_txt);
        Intent intent = getIntent();
        String playerJson = intent.getStringExtra("playerKey");
        Player player = Singletons.getGson().fromJson(playerJson, Player.class);
        showDetail(player);
    }

    private void showDetail(Player player) {

        txtNameDetail.setText("Team : " + player.getTeam().getName());
        txtFullNameDetail.setText(player.getTeam().getFull_name());
        txtAbbrDetail.setText(player.getTeam().getAbbreviation());
        txtCityDetail.setText("city : " + player.getTeam().getCity());
        txtConferenceDetail.setText("Conference : " + player.getTeam().getConference());
        txtDivisionDetail.setText("Division : " + player.getTeam().getDivision());
    }
}
