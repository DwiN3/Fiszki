package com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI;

import com.kdbk.fiszki.Retrofit.FlashcardCollections;
import com.kdbk.fiszki.Retrofit.FlashcardCollectionsWords;
import com.kdbk.fiszki.Retrofit.FlashcardID;
import com.kdbk.fiszki.Retrofit.Flashcards;
import com.kdbk.fiszki.Retrofit.Login;
import com.kdbk.fiszki.Retrofit.Register;
import com.kdbk.fiszki.Retrofit.UserLVL;
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
    @GET("users/users-level")
    Call<UserLVL> getUserLVL();


    // FLASHCARDS
    @POST("flashcards/{collectionName}")
    Call<Flashcards> addFlashcard(@Path("collectionName") String collectionName, @Body Flashcards flashcards);
    @DELETE("flashcards/{flashcardsId}")
    Call<FlashcardID> deleteFlashcards(@Path("flashcardsId") String flashcardsId);
    @PUT("flashcards/{flashcardsId}")
    Call<FlashcardID> editFlashcards(@Path("flashcardsId") String flashcardsId, @Body FlashcardID flashCardsID);
    @GET("flashcards/{collectionName}")
    Call<FlashcardID> getFlashcard(@Path("collectionName") String flashcardsId);


    // FLASHCARDS-COLLECTION
    @GET("flashcards-collections")
    Call <List<FlashcardCollections>> getAllFlashcardsCollections();
    @GET("flashcards-collections/{collectionName}")
    Call<FlashcardCollectionsWords> getKit(@Path("collectionName") String collectionName);
    @DELETE("flashcards-collections/{collectionName}")
    Call<FlashcardCollections> deleteFlashcardsCollections(@Path("collectionName") String collectionName);
}
