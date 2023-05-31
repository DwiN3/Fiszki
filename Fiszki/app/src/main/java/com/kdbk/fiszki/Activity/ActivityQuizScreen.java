package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class ActivityQuizScreen extends AppCompatActivity implements View.OnClickListener {
    private TokenInstance tokenInstance = TokenInstance.getInstance();
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();
    private NextActivity nextActivity = new NextActivity(this);
    private SetGameClass game;
    private Button next, exit;
    private ImageView imageWordQuiz;
    private TextView answerText1, answerText2, answerText3, answerText4, nameWordQuizText, sticksLeftQuizText, scorePKT, userPKTQuiz;
    private ImageButton answerButton1, answerButton2, answerButton3, answerButton4;
    private String selectedLanguage = "", selectedName = "", selectedData = "", correctAnswer;
    private boolean isBackPressedBlocked = true, markTheAnswer = false;
    private int nrWords, allWords;
    private int points = 0, scoreTrain = 0;
    private ArrayList<ModelShowKitsEdit> wordsListKit = new ArrayList<>();
//private ArrayList<ModelShowKitsEdit> wordsListCategory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        setID();
        selectedLanguage = gameSettingsInstance.getLanguage();
        selectedName = gameSettingsInstance.getName();
        selectedData = gameSettingsInstance.getSelectData();

        getWordFromKitRetrofit();

        next.setOnClickListener(this);
        exit.setOnClickListener(this);
        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);
    }

    private void setEmoji() {
        if (scoreTrain <= -5) imageWordQuiz.setBackgroundResource(R.drawable.emoji_m5);
        if (scoreTrain == -4) imageWordQuiz.setBackgroundResource(R.drawable.emoji_m4);
        if (scoreTrain == -3) imageWordQuiz.setBackgroundResource(R.drawable.emoji_m3);
        if (scoreTrain == -2) imageWordQuiz.setBackgroundResource(R.drawable.emoji_m2);
        if (scoreTrain == -1) imageWordQuiz.setBackgroundResource(R.drawable.emoji_m1);
        if (scoreTrain == 0) imageWordQuiz.setBackgroundResource(R.drawable.flashcard_icon);
        if (scoreTrain == 1) imageWordQuiz.setBackgroundResource(R.drawable.emoji_1);
        if (scoreTrain == 2) imageWordQuiz.setBackgroundResource(R.drawable.emoji_2);
        if (scoreTrain == 3) imageWordQuiz.setBackgroundResource(R.drawable.emoji_3);
        if (scoreTrain == 4) imageWordQuiz.setBackgroundResource(R.drawable.emoji_4);
        if (scoreTrain >= 5) imageWordQuiz.setBackgroundResource(R.drawable.emoji_5);
    }

    public void clearButtons() {
        answerButton1.setBackgroundResource(R.drawable.rounded_button);
        answerButton2.setBackgroundResource(R.drawable.rounded_button);
        answerButton3.setBackgroundResource(R.drawable.rounded_button);
        answerButton4.setBackgroundResource(R.drawable.rounded_button);
    }

    void setQuestion(int numberWord) {
        markTheAnswer = false;
        nameWordQuizText.setText(game.getNameWord(numberWord));
        answerText1.setText(game.getAns1(numberWord));
        answerText2.setText(game.getAns2(numberWord));
        answerText3.setText(game.getAns3(numberWord));
        answerText4.setText(game.getAns4(numberWord));
        correctAnswer = game.getCorrectANS(numberWord);
        userPKTQuiz.setText("/" + allWords + " PKT");
        sticksLeftQuizText.setText("" + (game.getBorrder() - nrWords));

    }

    public void onClick(View view) {
        if (nrWords == game.getBorrder() - 1) next.setText("PODSUMOWANIE");
        sticksLeftQuizText.setText(String.valueOf(game.getBorrder() - nrWords - 1));

        switch (view.getId()) {
            case R.id.imageButtonAnswerQuiz1:
                if (answerText1.getText().equals(correctAnswer) && !markTheAnswer) {
                    if(scoreTrain < 0) scoreTrain = 1;
                    else scoreTrain += 1;
                    setEmoji();
                    points += 1;
                    scorePKT.setText("" + points);
                    answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    if (scoreTrain > 0) scoreTrain = -1;
                    else scoreTrain--;
                    setEmoji();
                    answerButton1.setBackgroundResource(R.drawable.rounded_button_red);
                    if (answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if (answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if (answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                    markTheAnswer = true;
                }
                break;
            case R.id.imageButtonAnswerQuiz2:
                if (answerText2.getText().equals(correctAnswer) && !markTheAnswer) {
                    if(scoreTrain < 0) scoreTrain = 1;
                    else scoreTrain += 1;
                    setEmoji();
                    points += 1;
                    scorePKT.setText("" + points);
                    answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    if (scoreTrain > 0) scoreTrain = -1;
                    else scoreTrain--;
                    setEmoji();
                    answerButton2.setBackgroundResource(R.drawable.rounded_button_red);
                    if (answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if (answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if (answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                    markTheAnswer = true;
                }
                break;
            case R.id.imageButtonAnswerQuiz3:
                if (answerText3.getText().equals(correctAnswer) && !markTheAnswer) {
                    if(scoreTrain < 0) scoreTrain = 1;
                    else scoreTrain += 1;
                    setEmoji();
                    points += 1;
                    scorePKT.setText("" + points);
                    answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    if (scoreTrain > 0) scoreTrain = -1;
                    else scoreTrain--;
                    setEmoji();
                    answerButton3.setBackgroundResource(R.drawable.rounded_button_red);
                    if (answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if (answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if (answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                    markTheAnswer = true;
                }
                break;
            case R.id.imageButtonAnswerQuiz4:
                if (answerText4.getText().equals(correctAnswer) && !markTheAnswer) {
                    if(scoreTrain < 0) scoreTrain = 1;
                    else scoreTrain += 1;
                    setEmoji();
                    points += 1;
                    scorePKT.setText("" + points);
                    answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    if (scoreTrain > 0) scoreTrain = -1;
                    else scoreTrain--;
                    setEmoji();
                    answerButton4.setBackgroundResource(R.drawable.rounded_button_red);
                    if (answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if (answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if (answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                    markTheAnswer = true;
                }
                break;
            case R.id.buttonNextQuiz:
                imageWordQuiz.setBackgroundResource(R.drawable.flashcard_icon);
                if (nrWords != game.getBorrder() - 1) {
                    nrWords += 1;
                    clearButtons();
                    setQuestion(nrWords);
                } else {
                    gameSettingsInstance.setPoints(points);
                    gameSettingsInstance.setAllWords(allWords);
                    nextActivity.openActivity(ActivityQuizEnd.class);
                }
                break;
            case R.id.buttonExitQuiz:
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
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
                            int id_count = 0;
                            for (FlashcardID collection : flashcardsList) {
                                wordsListKit.add(new ModelShowKitsEdit(collection.getWord(), collection.getTranslatedWord(), collection.getExample(), collection.getTranslatedExample(), id_count, collection.get_id()));
                                //System.out.println("Słowo:      "+collection.getWord()+"Tłumaczenie "+collection.getTranslatedWord()+"Zadanie "+collection.getExample()+"Przet   "+collection.getTranslatedExample());
                                id_count++;
                            }
                            game = new SetGameClass(selectedData,"quiz", wordsListKit);
                            scoreTrain = 0;
                            nrWords = 0;
                            allWords = game.getWords();
                            setEmoji();
                            setQuestion(nrWords);
                        }
                    }
                } else {
                    Toast.makeText(ActivityQuizScreen.this, "Błąd danych", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlashcardCollectionsWords> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void setID() {
        next = findViewById(R.id.buttonNextQuiz);
        exit = findViewById(R.id.buttonExitQuiz);
        answerButton1 = findViewById(R.id.imageButtonAnswerQuiz1);
        answerButton2 = findViewById(R.id.imageButtonAnswerQuiz2);
        answerButton3 = findViewById(R.id.imageButtonAnswerQuiz3);
        answerButton4 = findViewById(R.id.imageButtonAnswerQuiz4);
        answerText1 = findViewById(R.id.textAnswerQuiz1);
        answerText2 = findViewById(R.id.textAnswerQuiz2);
        answerText3 = findViewById(R.id.textAnswerQuiz3);
        answerText4 = findViewById(R.id.textAnswerQuiz4);
        nameWordQuizText = findViewById(R.id.nameWordQuiz);
        imageWordQuiz = findViewById(R.id.imageWordQuiz);
        sticksLeftQuizText = findViewById(R.id.sticksLeftQuiz);
        scorePKT = findViewById(R.id.userPKTScoreQuiz);
        userPKTQuiz = findViewById(R.id.userPKTQuizText);
    }
}