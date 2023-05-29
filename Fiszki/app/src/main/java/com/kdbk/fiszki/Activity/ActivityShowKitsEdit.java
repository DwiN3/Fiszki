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

import com.kdbk.fiszki.Other.FlashcardsCollectionsList;
import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterKits;
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterShowKitsEdit;
import com.kdbk.fiszki.Arrays.EditFlashcardArray;
import com.kdbk.fiszki.RecyclerView.Model.ModelEditFlashcard;
import com.kdbk.fiszki.RecyclerView.Model.ModelKits;
import com.kdbk.fiszki.RecyclerView.Model.ModelShowKitsEdit;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerShowKitsEdit;
import com.kdbk.fiszki.Retrofit.FlashcardCollections;
import com.kdbk.fiszki.Retrofit.Flashcards;
import com.kdbk.fiszki.Retrofit.FlashcardsID;
import com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private ArrayList<ModelShowKitsEdit> list = new ArrayList<>();
    private ArrayList<ModelShowKitsEdit> wordsList = new ArrayList<>();
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kits_edit);
        setID();

        Intent intent = getIntent();
        nameKit = intent.getStringExtra("name_kit");
        //System.out.println("Nazwa   "+nameKit);
        showWords();

        EditFlashcardArray editFlashcardArray = EditFlashcardArray.getInstance();
        Map<Integer, ArrayList<ModelEditFlashcard>> allList = editFlashcardArray.getAllList();

        Integer[] Ids = new Integer[allList.size()];
        String[] words = new String[allList.size()];
        String[] translateWord = new String[allList.size()];
        String[] sentens = new String[allList.size()];
        String[] translateSentens = new String[allList.size()];


        Integer i = 0;

        for (Integer index : allList.keySet()) {
            ArrayList<ModelEditFlashcard> list = allList.get(index);
            if (!list.isEmpty()) {
                ModelEditFlashcard wordElement = list.get(0);
                words[i] = wordElement.getEditWord();
                ModelEditFlashcard translateWordElement = list.get(1);
                translateWord[i] = translateWordElement.getEditWord();
                ModelEditFlashcard sentensElement = list.get(2);
                sentens[i] = sentensElement.getEditWord();
                ModelEditFlashcard translateSentensElement = list.get(3);
                translateSentens[i] = translateSentensElement.getEditWord();
                Ids[i] = index;
                i++;
            }
        }

        for(int n=0 ; n < Ids.length ; n++){
            list.add(new ModelShowKitsEdit(words[n], translateWord[n], sentens[n], translateSentens[n],Ids[n]));
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        Intent intent = getIntent();
        //intent.putExtra("LastWords", lastWords);
        intent.putExtra("NrWordID", modelShowKitsEdit.getID());
        nextActivity.openActivity(ActivityEditFlashcard.class, intent);
    }

    public void showWords() {

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
        Call<FlashcardCollections> call = jsonPlaceholderAPI.getKit(nameKit);

        call.enqueue(new Callback<FlashcardCollections>() {
            @Override
            public void onResponse(Call<FlashcardCollections> call, Response<FlashcardCollections> response) {
                System.out.println("TUTAJ                    "+response.body());
                FlashcardCollections flashcardCollection = new FlashcardCollections();
                flashcardCollection = response.body();

                ArrayList<FlashcardsID> Testowalista = flashcardCollection.getList();
                System.out.println("TUTAJ                    "+flashcardCollection.getCollectionName());

                int id = 0;
                for (FlashcardsID collection : Testowalista ) {
                    System.out.println(collection.getWord()+"    "+collection.getTranslatedWord());
                    //wordsList.add(new ModelKits(collection.getCollectionName(), "ILOSC FISZEK", "30", id, 30, collection.getId()));

                    id++;
                }

                if (list == null || list.isEmpty()) {
                    //showInfoZeroCollection();
                    return;
                }

                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityShowKitsEdit.this, "Błędne dane", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlashcardCollections> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println("DZIAALA");
                RefreshRecycleView();
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