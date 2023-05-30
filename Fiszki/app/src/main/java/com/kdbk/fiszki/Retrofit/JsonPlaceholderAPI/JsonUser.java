package com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI;

import com.kdbk.fiszki.Retrofit.Models.Login;
import com.kdbk.fiszki.Retrofit.Models.Register;
import com.kdbk.fiszki.Retrofit.Models.UserLVL;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface JsonUser {
    @POST("users/login")
    Call<Login> login(@Body Login login);
    @POST("users/sing-up")
    Call<Register> register(@Body Register register);
    @PUT("users/users-level")
    Call<UserLVL> userLVL(@Body UserLVL userLVL);
    @GET("users/users-level")
    Call<UserLVL> getUserLVL();
}
