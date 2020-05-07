package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
    }

    private void showList() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }
        // define an adapter
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);

    }

    static final String BASE_URL = "https://www.balldontlie.io/";

    private void makeApiCall(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NBAApi nbaApi = retrofit.create(NBAApi.class);

         Call<RestApiResponse> call = nbaApi.getPlayerResponse();
         call = nbaApi.getTeamResponse();

        call.enqueue(new Callback<RestApiResponse>() {
            @Override
            public void onResponse(Call<RestApiResponse> call, Response<RestApiResponse> response) {

                if (response.isSuccessful() && response.body() != null){

                    List<Player_data> playerDataList = response.body().getPlayerData();
                    List<Player_meta> playerMetaList = response.body().getPlayerMeta();
                    List<Team_data> teamDataList = response.body().getTeamData();
                    List<Team_meta> teamMetaList = response.body().getTeamMeta();
                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();

                } else {

                    showError();

                }

            }

            @Override
            public void onFailure(Call<RestApiResponse> call, Throwable t) {

                showError();

            }
        });

    }

    private void showError(){

        Toast.makeText(getApplicationContext(), "API Error ", Toast.LENGTH_SHORT).show();

    }

}
