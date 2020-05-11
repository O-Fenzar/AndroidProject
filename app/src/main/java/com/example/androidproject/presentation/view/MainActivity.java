package com.example.androidproject.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.Singletons;
import com.example.androidproject.presentation.controller.MainController;
import com.example.androidproject.presentation.model.Player;
import com.example.androidproject.presentation.model.PlayerDataTeam;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.fragment_first);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext())
        );

        controller.onStart();
    }
        public void showList(List<Player> playerDataList){

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            mAdapter = new ListAdapter(playerDataList, new ListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Player item) {
                    controller.onItemClick(item);
                }
            });
            recyclerView.setAdapter(mAdapter);
        }

        public void showError(){
            Toast.makeText(getApplicationContext(), "API Error ", Toast.LENGTH_SHORT).show();
        }

    public void navigateToDetails(Player player) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("playerKey", Singletons.getGson().toJson(player));

        MainActivity.this.startActivity(myIntent);

        //Toast.makeText(getApplicationContext(), " NAVIGATE SUCCESS ", Toast.LENGTH_SHORT).show();

    }
}
