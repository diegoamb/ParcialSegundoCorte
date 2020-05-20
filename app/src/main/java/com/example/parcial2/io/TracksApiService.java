package com.example.parcial2.io;

import com.example.parcial2.Searching;
import com.example.parcial2.TracksList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TracksApiService {

    @GET("2.0/?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json")
    Call<TracksList> getTracks();

    //@GET("2.0/?method=track.search&track=tusa&api_key=b284db959637031077380e7e2c6f2775&format=json")
    //Call<Searching> getSearching();

    @GET("2.0/")
    Call<Searching> getSearching(@Query("method") String method,@Query("track") String track,@Query("api_key") String api_key,
                                 @Query("format") String format);
}
