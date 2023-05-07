package com.kdbk.fiszki.Other;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceholderAPI {
    @POST("login")
    Call<List<Login>> createMessage(@Body Login login);
}
