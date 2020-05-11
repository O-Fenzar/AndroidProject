package com.example.androidproject.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.R;
import com.example.androidproject.Singletons;
import com.example.androidproject.presentation.model.Player;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String playerJson = intent.getStringExtra("playerKey");
        Player player = Singletons.getGson().fromJson(playerJson, Player.class);
        showDetail(player);

    }

    private void showDetail(Player player) {
        txtDetail.setText(player.getFirst_name());
    }
}
