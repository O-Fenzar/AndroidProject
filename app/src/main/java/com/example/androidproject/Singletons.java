package com.example.androidproject;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidproject.data.NBAApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static NBAApi nbaApiInstance;
    private static SharedPreferences sharedPreferencesInstance;


    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();

        }
        return gsonInstance;
    }

    public static NBAApi getNbaApi(){

        if(nbaApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            nbaApiInstance = retrofit.create(NBAApi.class);
        }
        return nbaApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){

        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences("application_projet3A", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }

}
