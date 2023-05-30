package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kdbk.fiszki.Other.FlashcardInfo;
import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterKits;
import com.kdbk.fiszki.Arrays.KitsArray;
import com.kdbk.fiszki.RecyclerView.Model.ModelKits;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerKits;
import com.kdbk.fiszki.Retrofit.FlashcardCollections;
import com.kdbk.fiszki.Retrofit.Flashcards;
import com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI;
import com.kdbk.fiszki.Retrofit.Login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityPanelKits extends AppCompatActivity implements SelectListenerKits, View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);
    private Token token  = Token.getInstance();
    private Button edit, del, menu;
    private TextView numberKit, timesPlayed, nextLvl;
    private int ID = 1, playedGames;
    private String _id="";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean isBackPressedBlocked = true;
    private ArrayList<ModelKits> collectionList = new ArrayList<>();
    private FlashcardInfo flashcardInfo  = FlashcardInfo.getInstance();
    private String collectionName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_kits);
        setID();

        fetchFlashcardsCollections();
        resetAfterDelate();
        edit.setOnClickListener(this);
        del.setOnClickListener(this);
        menu.setOnClickListener(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }

    private void RefreshRecycleView() {
        mRecyclerView = findViewById(R.id.kitsPanelRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new AdapterKits(collectionList, this, R.layout.recycler_view_kits_small);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonEditKitPanel:
                //System.out.println("Do wysłania nazwa"+collectionName);
                flashcardInfo.setNameCollection(collectionName);
                nextActivity.openActivity(ActivityShowKitsEdit.class);
                break;
            case R.id.buttonDeleteKitPanel:
                ModelKits modelKits = collectionList.stream()
                        .filter(m -> m.getID() == ID)
                        .findFirst()
                        .orElse(null);
                if (modelKits != null) {
                    collectionList.remove(modelKits);
                    deleteFlashcardsCollections(modelKits.getTextNumberKit());
                }
                RefreshRecycleView();
                resetAfterDelate();
                break;
            case R.id.buttonBackToMenuPanel:
                nextActivity.openActivity(ActivityMainMenu.class);
                flashcardInfo.setNameCollection("");
                flashcardInfo.setId_word("");
                break;
        }
    }

    public void resetAfterDelate(){
        if (collectionList.isEmpty()) {
            numberKit.setText("Brak dostępnych zestawów");
            timesPlayed.setText("");
            ID = 0;
            collectionName ="";
        } else {
            numberKit.setText(collectionList.get(0).getTextNumberKit());
            collectionName = collectionList.get(0).getTextNumberKit();
            timesPlayed.setText(collectionList.get(0).getGamesPlayed() + " razy");
            ID = collectionList.get(0).getID();
            _id = collectionList.get(0).get_id();
        }
    }

    @Override
    public void onItemClicked(ModelKits modelKits) {
        ID = modelKits.getID();
        playedGames = modelKits.getGamesPlayed();
        timesPlayed.setText(playedGames+" razy");
        _id = modelKits.get_id();
        collectionName = modelKits.getTextNumberKit();
        numberKit.setText(modelKits.getTextNumberKit());
    }

    private void setID() {
        edit = findViewById(R.id.buttonEditKitPanel);
        del = findViewById(R.id.buttonDeleteKitPanel);
        menu = findViewById(R.id.buttonBackToMenuPanel);
        numberKit = findViewById(R.id.textNumberKitPanel);
        nextLvl = findViewById(R.id.textNextLVLEndQuiz);
        timesPlayed = findViewById(R.id.textTimesEndQuiz);
    }

    private void fetchFlashcardsCollections() {

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
        Call<List<FlashcardCollections>> call = jsonPlaceholderAPI.getAllFlashcardsCollections();

        call.enqueue(new Callback<List<FlashcardCollections>>() {
            @Override
            public void onResponse(Call<List<FlashcardCollections>> call, Response<List<FlashcardCollections>> response) {
                collectionList.clear();
                List<FlashcardCollections> list = response.body();
                if (list == null || list.isEmpty()) {
                    showInfoZeroCollection();
                    return;
                }
                else{
                    edit.setVisibility(View.VISIBLE);
                    del.setVisibility(View.VISIBLE);
                }
                int id = 0;
                for (FlashcardCollections collection : list) {
                    collectionList.add(new ModelKits(collection.getCollectionName(), "ILOSC FISZEK", "11", id, 23, collection.getId()));
                    id++;
                }
                collectionName = collectionList.get(0).getTextNumberKit();
                ID = collectionList.get(0).getID();
                timesPlayed.setText(String.valueOf(collectionList.get(0).getGamesPlayed()+" razy"));
                numberKit.setText(String.valueOf(list.get(0).getCollectionName()));
                nextLvl.setText("230/500 pkt");
                RefreshRecycleView();
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityPanelKits.this, "Błędne dane", Toast.LENGTH_SHORT).show();
                    edit.setVisibility(View.INVISIBLE);
                    del.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<FlashcardCollections>> call, Throwable t) {

            }
        });
    }

    private void deleteFlashcardsCollections(String collectionName) {

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
        Call<FlashcardCollections> call = jsonPlaceholderAPI.deleteFlashcardsCollections(collectionName);


        call.enqueue(new Callback<FlashcardCollections>() {
            @Override
            public void onResponse(Call<FlashcardCollections> call, Response<FlashcardCollections> response) {
                System.out.println("TUTAJ                                  "+response.code());
                System.out.println(collectionName);
            }

            @Override
            public void onFailure(Call<FlashcardCollections> call, Throwable t) {
                RefreshRecycleView();
                resetAfterDelate();
            }
        });
    }

    private void showInfoZeroCollection() {
        numberKit.setText("Brak dostępnych zestawów");
        timesPlayed.setText("");
        ID = 0;
        collectionName = "";
        _id = "";
        edit.setVisibility(View.INVISIBLE);
        del.setVisibility(View.INVISIBLE);
    }
}
