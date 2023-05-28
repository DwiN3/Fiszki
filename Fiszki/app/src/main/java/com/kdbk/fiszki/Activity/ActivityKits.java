package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityKits extends AppCompatActivity implements SelectListenerKits {

    NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView noKitsInfo;
    private String selectedMode = "";
    private String selectedLanguage = "";
    private KitsArray kitsArray = KitsArray.getInstance();
    private ArrayList<ModelKits> list = kitsArray.getList();
    private ArrayList<ModelKits> collectionList = new ArrayList<>();
    private Token token  = Token.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_kits);
        setID();
        fetchFlashcardsCollections();

        Intent intent = getIntent();
        selectedMode = intent.getStringExtra("SelectedMode");
        selectedLanguage = intent.getStringExtra("SelectLanguage");
    }

    @Override
    public void onItemClicked(ModelKits modelKits) {
        //Toast.makeText(this, modelKit.getTextNumberKit(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("SelectLanguage", selectedLanguage);
        intent.putExtra("SelectID", ""+ modelKits.get_id());
        intent.putExtra("SelectData", "kit");

        if(selectedMode.equals("quiz")){
            nextActivity.openActivity(ActivityQuizScreen.class, intent);
        } else if(selectedMode.equals("learn")){
            nextActivity.openActivity(ActivityLearningScreen.class, intent);
        }
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
            public void onResponse(Call <List<FlashcardCollections>> call, Response<List<FlashcardCollections>> response) {
                collectionList.clear();
                List<FlashcardCollections> list = response.body();
                int id = 0;
                for(FlashcardCollections collection : list){
                    collectionList.add(new ModelKits(collection.getCollectionName(), "ILOSC FISZEK", "30", id, 30,collection.getId()));
                    id++;
                }
                RefreshRecycleView();
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityKits.this, "Błędne dane", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FlashcardCollections>> call, Throwable t) {

            }
        });
    }
    private void RefreshRecycleView(){
        mRecyclerView = findViewById(R.id.kitsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterKits(collectionList, this, R.layout.recycler_view_kits);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        if(collectionList.isEmpty()) noKitsInfo.setVisibility(View.VISIBLE);
        else noKitsInfo.setVisibility(View.GONE);
    }

    private void setID() {
        noKitsInfo = findViewById(R.id.textNoKits);
    }
}