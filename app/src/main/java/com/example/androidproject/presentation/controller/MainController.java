package com.example.androidproject.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidproject.Constants;
import com.example.androidproject.data.NBAApi;
import com.example.androidproject.presentation.model.MetaData;
import com.example.androidproject.presentation.model.Player;
import com.example.androidproject.presentation.model.RestApiResponse;
import com.example.androidproject.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {

        List<Player> playerDataList = getDataFromCache();
        if (playerDataList != null) {

            view.showList(playerDataList);
        } else {

            makeApiPlayerCall();
        }
    }

        private void makeApiPlayerCall(){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
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
                        view.showList(playerDataList);
                    } else {
                        view.showError();
                    }
                }

                @Override
                public void onFailure(Call<RestApiResponse> call, Throwable t) {

                    view.showError();
                }
            });



        }

    private void saveList(List<Player> playerDataList){

        String jsonString = gson.toJson(playerDataList);
        sharedPreferences
                .edit()
                .putString("jsonPlayerList", jsonString)
                .apply();

        //Toast.makeText(getApplicationContext(), "List Saved ", Toast.LENGTH_SHORT).show();
    }

        private List<Player> getDataFromCache(){

            String jsonPlayer = sharedPreferences.getString(Constants.KEY_PLAYER_LIST, null);

            if(jsonPlayer == null) {
                return null;

            }else {
                Type listType = new TypeToken<List<Player>>(){}.getType();
                return gson.fromJson(jsonPlayer, listType);
            }
        }






    public void onItemClick(Player player){


    }

    public void onButtonAClick(){


    }

    public void onButtonBClick(){


    }


}
