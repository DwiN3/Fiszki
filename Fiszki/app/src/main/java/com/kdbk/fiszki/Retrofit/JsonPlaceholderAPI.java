package com.kdbk.fiszki.Retrofit;


import com.kdbk.fiszki.Retrofit.Login;

import java.util.List;

import kotlinx.coroutines.DelicateCoroutinesApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    Call<AddFlashcard> addFlashcard(@Path("collectionName") String collectionName, @Body AddFlashcard addFlashcard);

    @DELETE("flashcards/{flashcardsId}")
    Call<FlashCardsID> deleteFlashcards(@Path("flashcardsId") String flashcardsId, @Body FlashCardsID flashCardsID);

    @PUT("flashcards/{flashcardsId}")
    Call<FlashCardsID> editFlashcards(@Path("flashcardsId") String flashcardsId, @Body FlashCardsID flashCardsID);


    // FLASHCARDS-COLLECTION
    @GET("flashcards-collections")
    Call<FlashCardCollections> flashcardsCollectionsGET(@Body FlashCardCollections flashCardCollections);

    @GET("flashcards-collections/{collectionName}")
    Call<FlashCardCollections> flashcardsCollectionsGETID(@Path("collectionName") String collectionName, @Body FlashCardCollections flashCardCollections);

    @DELETE("flashcards-collections/{collectionName}")
    Call<FlashCardCollections> flashcardsCollectionsDELETE(@Path("collectionName") String collectionName, @Body FlashCardCollections flashCardCollections);
}
