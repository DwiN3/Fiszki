package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterAddFlashcard;
import com.kdbk.fiszki.Arrays.EditFlashcardArray;
import com.kdbk.fiszki.Arrays.FlashcardArray;
import com.kdbk.fiszki.RecyclerView.Model.ModelAddFlashcard;
import com.kdbk.fiszki.RecyclerView.Model.ModelEditFlashcard;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.Arrays.WordsArray;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerAddFlashcard;
import com.kdbk.fiszki.Retrofit.AddFlashcard;
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

public class ActivityAddFlashcard extends AppCompatActivity implements SelectListenerAddFlashcard, AdapterAddFlashcard.OnEditTextChangeListener {


    private NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private Button add, backToMenu;
    private RecyclerView.Adapter mAdapter;
    private String[] newFlashcard;
    private String nrKit, word, translateWord, sampleSentence, translateSampleSentence;
    private RecyclerView.LayoutManager mLayoutManager;

    private FlashcardArray flashcardArray = FlashcardArray.getInstance();
    private ArrayList<ModelAddFlashcard> list = flashcardArray.getList();
    private Token token  = Token.getInstance();
    ArrayList<ModelEditFlashcard> subList = new ArrayList<>();
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);
        setID();

        mRecyclerView = findViewById(R.id.addFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterAddFlashcard(list, this, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        takeWords();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFlashcardRecycle();
                addFlashcardToBase();
                Toast.makeText(ActivityAddFlashcard.this, "Trwa dodawanie fiszki", Toast.LENGTH_SHORT).show();
            }
        });
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityMainMenu.class);
            }
        });
    }

    void takeWords() {
        if (!list.isEmpty()) {
            nrKit = String.valueOf(list.get(0).getcardId());
            word = list.get(1).getEditFlashcard();
            translateWord = list.get(2).getEditFlashcard();
            sampleSentence = list.get(3).getEditFlashcard();
            translateSampleSentence = list.get(4).getEditFlashcard();
        }
    }

    private void setFlashcardRecycle(){
        EditFlashcardArray editFlashcardArray = EditFlashcardArray.getInstance();
        int nextIndex = editFlashcardArray.getWords() + 1;
        subList.add(new ModelEditFlashcard(R.drawable.flagpl, word,1, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagang, translateWord, 2, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagpl, sampleSentence, 3, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagang, translateSampleSentence, 4, nextIndex));
        editFlashcardArray.getAllList().put(nextIndex, subList);
        newFlashcard = new String[]{nrKit, word, translateWord, sampleSentence, translateSampleSentence};


        for (int i = 0; i < newFlashcard.length; i++) {
            Log.d("AddFlashcard", newFlashcard[i]);
        }
    }

    private void resetFlashcardRecycle() {
        EditFlashcardArray editFlashcardArray = EditFlashcardArray.getInstance();
        int nextIndex = editFlashcardArray.getWords() + 1;

        // Clear the previous subList and create a new one
        subList = new ArrayList<>();

        subList.add(new ModelEditFlashcard(R.drawable.flagpl, "", 1, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagang, "", 2, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagpl, "", 3, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagang, "", 4, nextIndex));

        // Update the EditFlashcardArray with the new subList
        editFlashcardArray.getAllList().put(nextIndex, subList);

        // Clear the field values
        word = "";
        translateWord = "";
        sampleSentence = "";
        translateSampleSentence = "";

        // Notify the adapter that the data set has changed
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClicked(ModelAddFlashcard modelAddFlashcard) {

    }

    public void addFlashcardToBase() {
        String collectionName = "1";
        String language = "english";
        String category = "inne";

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
        AddFlashcard post = new AddFlashcard(nrKit, language, category, word, translateWord, sampleSentence, translateSampleSentence);
        Call<AddFlashcard> call = jsonPlaceholderAPI.addFlashcard(nrKit, post);

        call.enqueue(new Callback<AddFlashcard>() {
            @Override
            public void onResponse(Call<AddFlashcard> call, Response<AddFlashcard> response) {
                //System.out.println("KODZIK =" + response);
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityAddFlashcard.this, "Błędne dane", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddFlashcard> call, Throwable t) {
                Toast.makeText(ActivityAddFlashcard.this, "Poprawnie dodano fiszkę", Toast.LENGTH_SHORT).show();
                resetFlashcardRecycle();
            }
        });
    }


        private void setID() {
        add = findViewById(R.id.buttonAcceptFlashcard);
        backToMenu = findViewById(R.id.buttonBackToMenuFlashcard);
    }

    @Override
    public void onEditTextChanged(int cardId, String newText) {
        switch (cardId) {
            case 1:
                nrKit = newText;
                break;
            case 2:
                word = newText;
                break;
            case 3:
                translateWord = newText;
                break;
            case 4:
                sampleSentence = newText;
                break;
            case 5:
                translateSampleSentence = newText;
                break;
            default:
                break;
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }
}