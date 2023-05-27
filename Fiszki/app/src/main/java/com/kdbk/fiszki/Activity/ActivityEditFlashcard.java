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

import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterEditFlashcard;
import com.kdbk.fiszki.Arrays.EditFlashcardArray;
import com.kdbk.fiszki.RecyclerView.Model.ModelEditFlashcard;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerEditFlashcard;
import com.kdbk.fiszki.Retrofit.AddFlashcard;
import com.kdbk.fiszki.Retrofit.FlashCardsID;
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
    private int nrWord, lastWords;
    private  String flashcardsID = "";
    Button accept, back, delete;
    private String[] words;
    String word, translateWord,sentens,sentensTranslate;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);
        setID();

        Intent intent = getIntent();
        nrWord = intent.getIntExtra("NrWordID", 1);
        EditFlashcardArray instance = EditFlashcardArray.getInstance();
        ArrayList<ModelEditFlashcard> list = instance.getList(nrWord);

        ModelEditFlashcard wordElement = list.get(0);
        word = wordElement.getEditWord();
        ModelEditFlashcard editTranslateWordElement = list.get(1);
        translateWord = editTranslateWordElement.getEditWord();
        ModelEditFlashcard sentensElement = list.get(2);
        sentens = sentensElement.getEditWord();
        ModelEditFlashcard sentensTranslateElement = list.get(3);
        sentensTranslate = sentensTranslateElement.getEditWord();

        mRecyclerView = findViewById(R.id.editFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterEditFlashcard(list, this, this);
        mRecyclerView.setAdapter(mAdapter);

        takeWords();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ModelEditFlashcard> list = instance.getList(nrWord);
                ModelEditFlashcard wordElement = list.get(0);
                wordElement.setEditWord(word);
                ModelEditFlashcard editTranslateWordElement = list.get(1);
                editTranslateWordElement.setEditWord(translateWord);
                ModelEditFlashcard sentensElement = list.get(2);
                sentensElement.setEditWord(sentens);
                ModelEditFlashcard sentensTranslateElement = list.get(3);
                sentensTranslateElement.setEditWord(sentensTranslate);

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
        FlashCardsID post = new FlashCardsID();
        Call<FlashCardsID> call = jsonPlaceholderAPI.deleteFlashcards(flashcardsID, post);

        call.enqueue(new Callback<FlashCardsID>() {
            @Override
            public void onResponse(Call<FlashCardsID> call, Response<FlashCardsID> response) {
                //System.out.println("KODZIK =" + response);
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityEditFlashcard.this, "Błąd operacji", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlashCardsID> call, Throwable t) {
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
        FlashCardsID post = new FlashCardsID(word,translateWord, sentens, sentensTranslate);
        Call<FlashCardsID> call = jsonPlaceholderAPI.editFlashcards(flashcardsID, post);

        call.enqueue(new Callback<FlashCardsID>() {
            @Override
            public void onResponse(Call<FlashCardsID> call, Response<FlashCardsID> response) {
                //System.out.println("KODZIK =" + response);
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityEditFlashcard.this, "Błąd operacji", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlashCardsID> call, Throwable t) {
                Toast.makeText(ActivityEditFlashcard.this, "Poprawnie zmodyfikowano fiszkę", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setID() {
        accept = findViewById(R.id.buttonEditFlashCardAccept);
        back = findViewById(R.id.buttonEditFlashCardBack);
        delete = findViewById(R.id.buttonEditFlashCardDelate);
    }
}
