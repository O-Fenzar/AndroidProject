package com.example.androidproject.data;

import com.example.androidproject.presentation.model.RestApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NBAApi {

    @GET("api/v1/players")
    Call<RestApiResponse> getPlayerResponse();
    //Call<RestApiResponse> getPlayerResponse();

    @GET("api/v1/teams")
    Call<RestApiResponse> getTeamResponse();
    //Call<RestApiResponse> getTeamResponse();
}
