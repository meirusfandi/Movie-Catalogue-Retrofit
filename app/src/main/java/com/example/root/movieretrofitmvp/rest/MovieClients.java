package com.example.root.movieretrofitmvp.rest;import retrofit2.Retrofit;import retrofit2.converter.gson.GsonConverterFactory;import static com.example.root.movieretrofitmvp.BuildConfig.BASE_URL;public class MovieClients {    private static final String URL = BASE_URL;    private static Retrofit retrofit = null;    public static Retrofit getMovieClients(){        if (retrofit == null){            retrofit = new Retrofit.Builder()                    .baseUrl(URL)                    .addConverterFactory(GsonConverterFactory.create())                    .build();        }        return retrofit;    }}