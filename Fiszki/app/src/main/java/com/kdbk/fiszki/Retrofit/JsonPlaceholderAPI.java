package com.kdbk.fiszki.Retrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceholderAPI {
    // USER
    @POST("users/login")
    Call<Login> login(@Body Login login);
    @POST("users/sing-up")
    Call<Register> register(@Body Register register);
    @PUT("users/users-level")
    Call<UserLVL> userLVL(@Body UserLVL userLVL);


    // FLASHCARDS
    @POST("flashcards/{collectionName}")
    Call<Flashcards> addFlashcard(@Path("collectionName") String collectionName, @Body Flashcards flashcards);
    @DELETE("flashcards/{flashcardsId}")
    Call<FlashcardsID> deleteFlashcards(@Path("flashcardsId") String flashcardsId, @Body FlashcardsID flashCardsID);
    @PUT("flashcards/{flashcardsId}")
    Call<FlashcardsID> editFlashcards(@Path("flashcardsId") String flashcardsId, @Body FlashcardsID flashCardsID);
    @GET("flashcards/{collectionName}")
    Call<List<FlashcardsID>> getFlashcard(@Path("collectionName") String flashcardsId);


    // FLASHCARDS-COLLECTION
    @GET("flashcards-collections")
    Call <List<FlashcardCollections>> getAllFlashcardsCollections();
    @GET("flashcards-collections/{collectionName}")
    Call<FlashcardCollections> getKit(@Path("collectionName") String collectionName);
    @DELETE("flashcards-collections/{collectionName}")
    Call<FlashcardCollections> deleteFlashcardsCollections(@Path("collectionName") String collectionName);
}
