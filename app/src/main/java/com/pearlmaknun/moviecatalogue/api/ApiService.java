package com.pearlmaknun.moviecatalogue.api;

import com.pearlmaknun.moviecatalogue.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService  {

    @GET("movie/popular")
    Call<MovieResponse> getPopular(@Query("api_key") String apiKey);

    @GET("/3/search/movie")
    Call<MovieResponse> searchMovie(@Query("api_key") String api_key,
                                    @Query("query") String query);
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcoming(@Query("api_key") String api_key);

}
