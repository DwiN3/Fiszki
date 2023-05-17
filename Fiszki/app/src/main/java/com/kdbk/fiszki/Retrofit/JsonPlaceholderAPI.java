package com.kdbk.fiszki.Retrofit;


import com.kdbk.fiszki.Retrofit.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceholderAPI {
    @POST("login")
    Call<Login> login(@Body Login login);

    @POST("sing-up")
    Call<Register> register(@Body Register register);

    @POST("flashcards/{collectionName}")
    Call<AddFlashcard> addFlashcard(@Path("collectionName") String collectionName, @Body AddFlashcard addFlashcard);

    
}
