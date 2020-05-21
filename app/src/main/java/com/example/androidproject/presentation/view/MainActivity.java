package com.example.androidproject.presentation.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.Singletons;
import com.example.androidproject.presentation.controller.MainController;
import com.example.androidproject.presentation.model.Player;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MaterialSearchView materialSearchView;

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

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Player");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        materialSearchView = (MaterialSearchView)findViewById(R.id.search_view);

        controller.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

         getMenuInflater().inflate(R.menu.menu_item, menu);
         MenuItem searchItem = menu.findItem(R.id.action_search);
         materialSearchView.setMenuItem(searchItem);

        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
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
