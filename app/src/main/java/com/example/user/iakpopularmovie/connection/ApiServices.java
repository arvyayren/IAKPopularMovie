package com.example.user.iakpopularmovie.connection;


import com.example.user.iakpopularmovie.model.ModelListMovie;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 27/01/2018.
 */

public interface ApiServices {

    //sort by popular
    @GET("movie/popular?api_key=5fa56c8e1cf58926aa909f0fc1d69efc&language=en-US&page=1")
    Call <ModelListMovie> getPopular();

    //sort by toprated
    @GET("movie/upcoming?api_key=6faf3907bbbde8f8af6fea8cc8ba5831&language=en-US&page=1&region=US")
    Call <ModelListMovie> getUpComing();

    //sort by nowplaying
    @GET("movie/now_playing?api_key=6faf3907bbbde8f8af6fea8cc8ba5831&language=en-US&page=1")
    Call <ModelListMovie> getNowPlaying();

}
