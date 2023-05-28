package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView numberKit, timesPlayed;
    private int ID = 1, playedGames;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private KitsArray kitsArray = KitsArray.getInstance();
    private ArrayList<ModelKits> list = kitsArray.getList();
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private ArrayList<ModelKits> collectionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_kits);
        setID();

        fetchFlashcardsCollections();
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

    private void RefreshRecycleView(){
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
                nextActivity.openActivity(ActivityShowKitsEdit.class);

                break;
            case R.id.buttonDeleteKitPanel:
                ModelKits modelKits = list.stream()
                        .filter(m -> m.getID() == ID)
                        .findFirst()
                        .orElse(null);
                if (modelKits != null) {
                    list.remove(modelKits);
                }
                RefreshRecycleView();
                resetAfterDelate();
                break;
            case R.id.buttonBackToMenuPanel:
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
    }

    public void resetAfterDelate(){
        if (list.isEmpty()) {
            numberKit.setText("Brak dostępnych zestawów");
            timesPlayed.setText("");
        } else {
            numberKit.setText("Zestaw " + (list.get(0).getID()+1));
            timesPlayed.setText(list.get(0).getGamesPlayed() + " razy");
            ID = list.get(0).getID();
        }
    }

    @Override
    public void onItemClicked(ModelKits modelKits) {
        ID = modelKits.getID();
        playedGames = modelKits.getGamesPlayed();
        timesPlayed.setText(playedGames+" razy");
        numberKit.setText(collectionList.get(ID).getTextNumberKit());
    }

    private void setID() {
        edit = findViewById(R.id.buttonEditKitPanel);
        del = findViewById(R.id.buttonDeleteKitPanel);
        menu = findViewById(R.id.buttonBackToMenuPanel);
        numberKit = findViewById(R.id.textNumberKitPanel);
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

        call.enqueue(new Callback <List<FlashcardCollections>>() {
            @Override
            public void onResponse(Call <List<FlashcardCollections>> call, Response <List<FlashcardCollections>> response) {
                collectionList.clear();
                List<FlashcardCollections> list = response.body();
                int id = 0;
                for(FlashcardCollections collection : list){
                    collectionList.add(new ModelKits(collection.getCollectionName(), "ILOSC FISZEK", "30", id, 30,collection.getId()));
                    id++;
                }
                RefreshRecycleView();
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityPanelKits.this, "Błędne dane", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FlashcardCollections>> call, Throwable t) {

            }
        });
        RefreshRecycleView();
    }
}