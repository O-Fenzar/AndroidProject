package com.example.androidproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NBAApi {

    @GET("api/v1/players")
    Call<RestApiResponse> getPlayerResponse(@Query("cle") String valeur);

    @GET("api/v1/teams")
    Call<RestApiResponse> getTeamResponse(@Query("cle") String valeur);

    Call<RestApiResponse> getPlayerResponse();

    Call<RestApiResponse> getTeamResponse();
}

