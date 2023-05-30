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
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterShowKitsEdit;
import com.kdbk.fiszki.RecyclerView.Model.ModelShowKitsEdit;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerShowKitsEdit;
import com.kdbk.fiszki.Retrofit.FlashcardCollectionsWords;
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

public class ActivityShowKitsEdit extends AppCompatActivity implements SelectListenerShowKitsEdit {

    NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String selectedMode = "";
    private String selectedLanguage = "";
    private String nameKit="";
    private Token token  = Token.getInstance();
    private FlashcardInfo flashcardInfo  = FlashcardInfo.getInstance();
    private ArrayList<ModelShowKitsEdit> wordsList = new ArrayList<>();
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kits_edit);
        setID();

        nameKit = flashcardInfo.getNameCollection();
        //System.out.println("Nazwa   "+nameKit);
        showKits();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashcardInfo.setId_word("");
                nextActivity.openActivity(ActivityPanelKits.class);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onItemClicked(ModelShowKitsEdit modelShowKitsEdit) {
        flashcardInfo.setId_word(modelShowKitsEdit.get_id());
        System.out.println(modelShowKitsEdit.get_id());
        nextActivity.openActivity(ActivityEditFlashcard.class);
    }

   public void showKits() {
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
        Call<FlashcardCollectionsWords> call = jsonPlaceholderAPI.getKit(nameKit);

        call.enqueue(new Callback<FlashcardCollectionsWords>() {
            @Override
            public void onResponse(Call<FlashcardCollectionsWords> call, Response<FlashcardCollectionsWords> response) {
                if (response.isSuccessful()) {
                    FlashcardCollectionsWords flashcardCollection = response.body();

                    if (flashcardCollection != null) {
                        ArrayList<FlashcardsID> testowalista = flashcardCollection.getFlashcards();
                        if (testowalista != null && !testowalista.isEmpty()) {
                            int id_count=0;
                            for (FlashcardsID collection : testowalista) {
                                System.out.println(collection.getWord() + "    " + collection.getTranslatedWord()+ "    " + collection.getExample() + "    " +collection.getTranslatedExample() + "    " +id_count+ "   "+ collection.get_id());
                                wordsList.add(new ModelShowKitsEdit(collection.getWord(), collection.getTranslatedWord(), collection.getExample(), collection.getExample(),id_count, collection.get_id()));
                                id_count++;
                            }
                            RefreshRecycleView();
                        } else {
                            // Pusta lista fiszek
                            // showInfoZeroCollection();
                        }
                    } else {
                        // Odpowiedź z serwera jest pusta
                    }
                } else {
                    // Niepowodzenie żądania
                    Toast.makeText(ActivityShowKitsEdit.this, "Błędne dane", Toast.LENGTH_SHORT).show();
                }
                RefreshRecycleView();
            }

            @Override
            public void onFailure(Call<FlashcardCollectionsWords> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


    private void RefreshRecycleView() {
        mRecyclerView = findViewById(R.id.showWordKitsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterShowKitsEdit(wordsList, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setID() {
        back = findViewById(R.id.buttonBackShowKits);
    }
}