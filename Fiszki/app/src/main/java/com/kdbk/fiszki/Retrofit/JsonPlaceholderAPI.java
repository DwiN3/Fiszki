package com.kdbk.fiszki.Retrofit;


import com.kdbk.fiszki.Retrofit.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceholderAPI {
    @POST("login")
    Call<List<Login>> login(@Body Login login);
}
