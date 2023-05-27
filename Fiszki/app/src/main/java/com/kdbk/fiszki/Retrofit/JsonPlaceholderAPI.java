package com.kdbk.fiszki.Retrofit;


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


    // FLASHCARDS-COLLECTION
    @GET("flashcards-collections")
    Call<FlashcardCollections> getAllFlashcardsCollections(@Body FlashcardCollections flashCardCollections);
    @GET("flashcards-collections/{collectionName}")
    Call<FlashcardCollections> getByIDflashcardsCollections(@Path("collectionName") String collectionName, @Body FlashcardCollections flashCardCollections);
    @DELETE("flashcards-collections/{collectionName}")
    Call<FlashcardCollections> deleteFlashcardsCollections(@Path("collectionName") String collectionName, @Body FlashcardCollections flashCardCollections);
}
