package com.example.user.iakpopularmovie.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 27/01/2018.
 */

public class RetroConfig {
    private static Retrofit getRetrofit(){
        //inisisasi Retrofit
        Retrofit r= new Retrofit.Builder().
                baseUrl("https://api.themoviedb.org/3/")//harus dikasih slash
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return r;
    }

    public static ApiServices getApiServices(){
        return getRetrofit().create(ApiServices.class);
    }
}
