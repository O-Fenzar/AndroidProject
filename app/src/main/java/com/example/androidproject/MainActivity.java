package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    private SharedPreferences sharedPreferences;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.fragment_first);
        sharedPreferences = getSharedPreferences("application_projet3A", Context.MODE_PRIVATE);

         gson = new GsonBuilder()
                .setLenient()
                .create();

         List<Player> playerDataList = getDataFromCache();
         if(playerDataList != null){

            showList(playerDataList);
         }else{

             makeApiPlayerCall();
         }

    }

    private List<Player> getDataFromCache(){

        String jsonPlayer = sharedPreferences.getString("jsonPlayerList", null);

        if(jsonPlayer == null) {
            return null;

        }else {
            Type listType = new TypeToken<List<Player>>(){}.getType();
            return gson.fromJson(jsonPlayer, listType);
        }
    }

    private void showList(List<Player> playerDataList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(playerDataList);
        recyclerView.setAdapter(mAdapter);

    }

    static final String BASE_URL = "https://www.balldontlie.io/";

    private void makeApiPlayerCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NBAApi nbaApi = retrofit.create(NBAApi.class);

        Call<RestApiResponse> call = nbaApi.getPlayerResponse();

        call.enqueue(new Callback<RestApiResponse>() {

            @Override
            public void onResponse(Call<RestApiResponse> call, Response<RestApiResponse> response) {

                if (response.isSuccessful() && response.body() != null){

                    List<Player> playerDataList = response.body().getData();
                    MetaData playerMetaList = response.body().getMeta();

                    //Toast.makeText(getApplicationContext(), "API Success ", Toast.LENGTH_SHORT).show();
                    saveList(playerDataList);
                    showList(playerDataList);

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

    /*private void makeApiTeamCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NBAApi nbaApi = retrofit.create(NBAApi.class);
        Call<RestApiResponse> call = nbaApi.getTeamResponse();
        call.enqueue(new Callback<RestApiResponse>() {
            @Override
            public void onResponse(Call<RestApiResponse> call, Response<RestApiResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<Team> teamDataList = response.body().getTeamData();
                    MetaTeam teamMetaList = response.body().getTeamMeta();
                    Toast.makeText(getApplicationContext(), "API Success ", Toast.LENGTH_SHORT).show();
                    showList(teamDataList);
                } else {
                    showError();
                }
            }
            @Override
            public void onFailure(Call<RestApiResponse> call, Throwable t) {
                showError();
            }
        });
    }*/

    private void saveList(List<Player> playerDataList){

        String jsonString = gson.toJson(playerDataList);
        sharedPreferences
                .edit()
                .putString("jsonPlayerList", jsonString)
                .apply();

        //Toast.makeText(getApplicationContext(), "List Saved ", Toast.LENGTH_SHORT).show();
    }

    private void showError(){

        Toast.makeText(getApplicationContext(), "API Error ", Toast.LENGTH_SHORT).show();

    }

}