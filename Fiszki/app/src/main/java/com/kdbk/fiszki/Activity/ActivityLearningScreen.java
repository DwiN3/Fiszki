package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.kdbk.fiszki.Instance.GameSettingsInstance;
import com.kdbk.fiszki.Instance.TokenInstance;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.Other.SetGameClass;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.Model.ModelShowKitsEdit;
import com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI.JsonFlashcardsCollections;
import com.kdbk.fiszki.Retrofit.Models.FlashcardCollectionsWords;
import com.kdbk.fiszki.Retrofit.Models.FlashcardID;
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

public class ActivityLearningScreen extends AppCompatActivity implements View.OnClickListener {
    private TokenInstance tokenInstance = TokenInstance.getInstance();
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();
    private NextActivity nextActivity = new NextActivity(this);
    private SetGameClass game;
    private boolean isBackPressedBlocked = true;
    private Button buttonNext, next, exit;
    private TextView nameWord, sticksLeft, textsampleSentence;
    private ImageView imageWord;
    private int nrWords, allWords, countWords = 0;
    private String selectedLanguage = "", selectedName="", selectedData="";
    private ArrayList<ModelShowKitsEdit> wordsListKit = new ArrayList<>();
    //private ArrayList<ModelShowKitsEdit> wordsListCategory = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_screen);
        setID();

        selectedLanguage = gameSettingsInstance.getLanguage();
        selectedName = gameSettingsInstance.getName();
        selectedData = gameSettingsInstance.getSelectData();
        getWordFromKitRetrofit();

        next.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNextLearning:
                if(buttonNext.getText().equals("POKAŻ TŁUMACZENIE")){
                    buttonNext.setText("NASTĘPNE SŁOWO");
                    translateWord(nrWords);
                    if (nrWords == 0) {
                        buttonNext.setVisibility(View.GONE);
                        buttonNext.setEnabled(false);
                    }
                }
                else
                {
                    buttonNext.setText("POKAŻ TŁUMACZENIE");
                    if(nrWords > 0) {
                        nrWords -= 1;
                        setNewWord(nrWords);
                    }
                }
                break;
            case R.id.buttonExitLearning:
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
    }

    private void translateWord(int numberWord){
        imageWord.setBackgroundResource(game.getImgENG());
        nameWord.setText(game.getCorrectANS(numberWord));
        textsampleSentence.setText(game.getSentenseTra(numberWord));
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void setNewWord(int numberWord){
        nameWord.setText(game.getNameWord(numberWord));
        textsampleSentence.setText(game.getSentense(numberWord));
        imageWord.setBackgroundResource(game.getImgPL());
        countWords +=1;
        sticksLeft.setText(countWords+"/"+allWords);
    }


    private void setID() {
        next = findViewById(R.id.buttonNextLearning);
        exit = findViewById(R.id.buttonExitLearning);
        nameWord = findViewById(R.id.textnameWordLearning);
        sticksLeft = findViewById(R.id.textsticksLeftLearning);
        textsampleSentence = findViewById(R.id.textsampleSentenceLearning);
        imageWord = findViewById(R.id.imageWordLearning);
        buttonNext = findViewById(R.id.buttonNextLearning);
    }

    public void getWordFromKitRetrofit() {
        wordsListKit.clear();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + tokenInstance.getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonFlashcardsCollections jsonFlashcardsCollections = retrofit.create(JsonFlashcardsCollections.class);
        Call<FlashcardCollectionsWords> call = jsonFlashcardsCollections.getKit(selectedName);

        call.enqueue(new Callback<FlashcardCollectionsWords>() {
            @Override
            public void onResponse(Call<FlashcardCollectionsWords> call, Response<FlashcardCollectionsWords> response) {
                if (response.isSuccessful()) {
                    FlashcardCollectionsWords flashcardCollection = response.body();

                    if (flashcardCollection != null) {
                        ArrayList<FlashcardID> flashcardsList = flashcardCollection.getFlashcards();
                        if (flashcardsList != null && !flashcardsList.isEmpty()) {
                            int id_count=0;
                            for (FlashcardID collection : flashcardsList) {
                                wordsListKit.add(new ModelShowKitsEdit(collection.getWord(), collection.getTranslatedWord(), collection.getExample(), collection.getTranslatedExample(),id_count, collection.get_id()));
                                //System.out.println("Słowo:      "+collection.getWord()+"Tłumaczenie "+collection.getTranslatedWord()+"Zadanie "+collection.getExample()+"Przet   "+collection.getTranslatedExample());
                                id_count++;
                            }
                            game =  new SetGameClass(selectedData, wordsListKit);
                            allWords = game.getWords();
                            nrWords = game.getWords()-1;
                            setNewWord(nrWords);
                        }
                    }
                } else {
                    Toast.makeText(ActivityLearningScreen.this, "Błąd danych", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlashcardCollectionsWords> call, Throwable t) {
            }
        });
    }
}