package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kdbk.fiszki.Other.FlashcardInfo;
import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterEditFlashcard;
import com.kdbk.fiszki.Arrays.EditFlashcardArray;
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterKits;
import com.kdbk.fiszki.RecyclerView.Model.ModelEditFlashcard;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.Model.ModelKits;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerEditFlashcard;
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

public class ActivityEditFlashcard extends AppCompatActivity implements SelectListenerEditFlashcard, AdapterEditFlashcard.OnEditTextChangeListener {

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
    private ArrayList<ModelEditFlashcard> wordList = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private int  lastWords, nrWord=0; // do wyrąbania
    EditFlashcardArray instance = EditFlashcardArray.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);
        setID();

        _id_word = flashcardInfo.getId_word();
        System.out.println("ID=              "+_id_word);



        RefreshRecycleView();
        takeWords();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tabwords[] = {word,translateWord, sentens,sentensTranslate};
                for(int n=0;n<tabwords.length;n++) System.out.println(tabwords[n]);
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
                EditFlashcardArray instance = EditFlashcardArray.getInstance();
                instance.remove(nrWord);
                instance.getWords();
                mAdapter.notifyDataSetChanged();

                //System.out.println(instance.getWords());
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });
    }

    void takeWords() {
        //words = new String[]{"word", "translateWord", "sampleSentence", "translateSampleSentence"};

    }

    @Override
    public void onItemClicked(ModelEditFlashcard modelEditFlashcard) {

    }

    @Override
    public void onEditTextChanged(int cardId, String newText) {
        switch (cardId) {
            case 1:
                word = newText;
                break;
            case 2:
                translateWord = newText;
                break;
            case 3:
                sentens = newText;
                break;
            case 4:
                sentensTranslate = newText;
                break;
            default:
                break;
        }
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
        FlashcardsID post = new FlashcardsID();
        Call<FlashcardsID> call = jsonPlaceholderAPI.deleteFlashcards(_id_word, post);

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

    private void RefreshRecycleView() {
        mRecyclerView = findViewById(R.id.editFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterEditFlashcard(wordList, this, this);
        mRecyclerView.setAdapter(mAdapter);
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
                wordList.clear();
                //System.out.println("KODZIK =" + response);
                ArrayList<ModelEditFlashcard> list = new ArrayList<>();
                for(int n=0 ; n<4;n++){

                }
                ModelEditFlashcard wordElement = list.get(0);
                word = wordElement.getEditWord();
                ModelEditFlashcard editTranslateWordElement = list.get(1);
                translateWord = editTranslateWordElement.getEditWord();
                ModelEditFlashcard sentensElement = list.get(2);
                sentens = sentensElement.getEditWord();
                ModelEditFlashcard sentensTranslateElement = list.get(3);
                sentensTranslate = sentensTranslateElement.getEditWord();
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
        accept = findViewById(R.id.buttonEditFlashCardAccept);
        back = findViewById(R.id.buttonEditFlashCardBack);
        delete = findViewById(R.id.buttonEditFlashCardDelate);
    }
}
