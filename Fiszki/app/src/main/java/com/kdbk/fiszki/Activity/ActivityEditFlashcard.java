package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.kdbk.fiszki.Other.FlashcardInfo;
import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.Retrofit.FlashcardsID;
import com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityEditFlashcard extends AppCompatActivity  {

    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Token token  = Token.getInstance();
    private FlashcardInfo flashcardInfo  = FlashcardInfo.getInstance();
    private String _id_word="";
    Button accept, back, delete;
    private String[] words;
    String word, translateWord,sentens,sentensTranslate;
    private EditText wordText, translateWordText,exampleText, translateExampleText;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);
        setID();

        _id_word = flashcardInfo.getId_word();
        System.out.println("ID=              "+_id_word);


        setFlashcard();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word = String.valueOf(wordText.getText());
                translateWord = String.valueOf(translateWordText.getText());
                sentens = String.valueOf(exampleText.getText());
                sentensTranslate = String.valueOf(translateExampleText.getText());
                String tabwords[] = {word,translateWord, sentens,sentensTranslate};
                editFlashcard();
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFlashcard();
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstęcz
        }
        return super.dispatchKeyEvent(event);
    }

    public void deleteFlashcard() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token.getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        Call<FlashcardsID> call = jsonPlaceholderAPI.deleteFlashcards(_id_word);

        call.enqueue(new Callback<FlashcardsID>() {
            @Override
            public void onResponse(Call<FlashcardsID> call, Response<FlashcardsID> response) {
                //System.out.println("KODZIK =" + response);
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityEditFlashcard.this, "Błąd operacji", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlashcardsID> call, Throwable t) {
                Toast.makeText(ActivityEditFlashcard.this, "Poprawnie usunięto fiszkę", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editFlashcard() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token.getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        FlashcardsID post = new FlashcardsID(word,translateWord, sentens, sentensTranslate);
        Call<FlashcardsID> call = jsonPlaceholderAPI.editFlashcards(_id_word, post);

        call.enqueue(new Callback<FlashcardsID>() {
            @Override
            public void onResponse(Call<FlashcardsID> call, Response<FlashcardsID> response) {
                //System.out.println("KODZIK =" + response);

                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityEditFlashcard.this, "Błąd operacji", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlashcardsID> call, Throwable t) {
                Toast.makeText(ActivityEditFlashcard.this, "Poprawnie zmodyfikowano fiszkę", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setFlashcard() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token.getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        Call<FlashcardsID> call = jsonPlaceholderAPI.getFlashcard(_id_word);

        call.enqueue(new Callback<FlashcardsID>() {
            @Override
            public void onResponse(Call<FlashcardsID> call, Response<FlashcardsID> response) {
                //System.out.println("KODZIK =" + response);
                wordText.setText(response.body().getWord());
                translateWordText.setText(response.body().getTranslatedWord());
                exampleText.setText(response.body().getExample());
                translateExampleText.setText(response.body().getTranslatedExample());
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityEditFlashcard.this, "Błąd operacji", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlashcardsID> call, Throwable t) {

            }
        });
    }



    private void setID() {
        wordText = findViewById(R.id.word_text_edit);
        translateWordText = findViewById(R.id.translate_text_edit);
        exampleText = findViewById(R.id.example_text_edit);
        translateExampleText = findViewById(R.id.translate_example_text_edit);
        accept = findViewById(R.id.buttonEditFlashCardAccept);
        back = findViewById(R.id.buttonEditFlashCardBack);
        delete = findViewById(R.id.buttonEditFlashCardDelate);
    }
}
