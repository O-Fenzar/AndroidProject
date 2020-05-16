package com.example.androidproject.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.R;
import com.example.androidproject.Singletons;
import com.example.androidproject.presentation.model.Player;

public class DetailActivity extends AppCompatActivity {

    private TextView txtTeamDetail;
    private TextView txtNameDetail;
    private TextView txtAbbrDetail;
    private TextView txtFullNameDetail;
    private TextView txtCityDetail;
    private TextView txtConferenceDetail;
    private TextView txtDivisionDetail;

    private TextView txtPlayerDetail;
    private TextView txtHeight_feetDetail;
    private TextView txtHeight_inchDetail;
    private TextView txtWeightPoundDetail;
    private TextView txtPositionTitle;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_detail);

        txtTeamDetail = findViewById(R.id.team_detail);
        txtNameDetail = findViewById(R.id.name_txt);
        txtAbbrDetail = findViewById(R.id.abbreviation_txt);
        txtFullNameDetail = findViewById(R.id.full_name_txt);
        txtCityDetail = findViewById(R.id.city_txt);
        txtConferenceDetail = findViewById(R.id.conference_txt);
        txtDivisionDetail = findViewById(R.id.division_txt);

        txtPlayerDetail = findViewById(R.id.player_detail);
        txtPositionTitle = findViewById(R.id.position_detail);
        txtHeight_feetDetail = findViewById(R.id.height_feet);
        txtHeight_inchDetail = findViewById(R.id.height_inches);
        txtWeightPoundDetail = findViewById(R.id.weight_pounds);


        Intent intent = getIntent();
        String playerJson = intent.getStringExtra("playerKey");
        Player player = Singletons.getGson().fromJson(playerJson, Player.class);
        showDetail(player);
    }

    private void showDetail(Player player) {


        String pos = player.getPosition();

        String textPos = new String();
        if(pos.contains("C")){
            textPos = "Pivot " ;
        }
        if(pos.contains("F")){
            textPos  = textPos + "Ailier Fort " ;

        }
        if(pos.contains("G")){
            textPos  = textPos + "Meneur " ;

        }

        // Team Details
        txtTeamDetail.setText("Team Details");
        txtNameDetail.setText("Team : " + player.getTeam().getName());
        txtFullNameDetail.setText(player.getTeam().getFull_name());
        txtAbbrDetail.setText(player.getTeam().getAbbreviation());
        txtCityDetail.setText("city : " + player.getTeam().getCity());
        txtConferenceDetail.setText("Conference : " + player.getTeam().getConference());
        txtDivisionDetail.setText("Division : " + player.getTeam().getDivision());

        String textFeet = new String();
        if(player.getHeight_feet() == null){
            textFeet = "noData " ;
        }else{
            textFeet = player.getHeight_feet();
        }

        String textInches = new String();
        if(player.getHeight_inches() == null){
            textInches = "noData " ;
        }else{
            textInches = player.getHeight_inches();
        }

        String textPounds = new String();
        if( player.getWeight_pounds() == null){
            textPounds = "noData " ;
        }else{
            textPounds = player.getWeight_pounds();
        }

        //Player Details
        txtPlayerDetail.setText("Player Details");
        txtPositionTitle.setText("Position : " + textPos);
        txtHeight_feetDetail.setText("Height : " + textFeet + " feets ");
        txtHeight_inchDetail.setText("Height : " + textInches + " inches ");
        txtWeightPoundDetail.setText("Weight : " + textPounds + " pounds ");

        }
    }

