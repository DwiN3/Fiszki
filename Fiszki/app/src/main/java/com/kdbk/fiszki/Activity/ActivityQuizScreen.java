package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kdbk.fiszki.Instance.GameSettingsInstance;
import com.kdbk.fiszki.Instance.TokenInstance;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.Other.SetGameClass;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.Model.ModelShowKitsEdit;

import java.util.ArrayList;

public class ActivityQuizScreen extends AppCompatActivity implements View.OnClickListener {
    private TokenInstance tokenInstance = TokenInstance.getInstance();
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();
    private NextActivity nextActivity = new NextActivity(this);
    private SetGameClass t;
    private Button next, exit;
    private ImageView imageWordQuiz;
    private TextView answerText1, answerText2, answerText3, answerText4, nameWordQuizText, sticksLeftQuizText, scorePKT, userPKTQuiz;
    private ImageButton answerButton1, answerButton2, answerButton3, answerButton4;
    private String selectedLanguage = "", selectedName ="", selectedData="", correctAnswer;
    private boolean isBackPressedBlocked = true, markTheAnswer = false;
    private int nrWords, allWords;
    private int points = 0;
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

        t =  new SetGameClass(selectedData, wordsListKit);

        nrWords = t.getWords()-1;
        allWords = t.getWords();
        userPKTQuiz.setText("/"+allWords+" PKT");
        sticksLeftQuizText.setText(""+(nrWords+1));

        next.setOnClickListener(this);
        exit.setOnClickListener(this);
        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);
        setQuestion(nrWords);
    }

    public void clearButtons(){
        answerButton1.setBackgroundResource(R.drawable.rounded_button);
        answerButton2.setBackgroundResource(R.drawable.rounded_button);
        answerButton3.setBackgroundResource(R.drawable.rounded_button);
        answerButton4.setBackgroundResource(R.drawable.rounded_button);
    }

    void setQuestion(int numberWord){
        markTheAnswer = false;
        nameWordQuizText.setText(t.getNameWord(numberWord));
        answerText1.setText(t.getAns1(numberWord));
        answerText2.setText(t.getAns2(numberWord));
        answerText3.setText(t.getAns3(numberWord));
        answerText4.setText(t.getAns4(numberWord));
        correctAnswer = t.getCorrectANS(numberWord);
        imageWordQuiz.setBackgroundResource(t.getImg(numberWord));
    }

    public void onClick(View view) {
        if(nrWords == 0) next.setText("PODSUMOWANIE");
        sticksLeftQuizText.setText(""+(nrWords));

        switch (view.getId()) {
            case R.id.imageButtonAnswerQuiz1:
                if(answerText1.getText().equals(correctAnswer) && !markTheAnswer) {
                    points +=1;
                    scorePKT.setText(""+points);
                    answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    answerButton1.setBackgroundResource(R.drawable.rounded_button_red);
                    if(answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                    markTheAnswer = true;
                }
                break;
            case R.id.imageButtonAnswerQuiz2:
                if(answerText2.getText().equals(correctAnswer) && !markTheAnswer) {
                    points +=1;
                    scorePKT.setText(""+points);
                    answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    answerButton2.setBackgroundResource(R.drawable.rounded_button_red);
                    if(answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                    markTheAnswer = true;
                }
                break;
            case R.id.imageButtonAnswerQuiz3:
                if(answerText3.getText().equals(correctAnswer) && !markTheAnswer) {
                    points +=1;
                    scorePKT.setText(""+points);
                    answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    answerButton3.setBackgroundResource(R.drawable.rounded_button_red);
                    if(answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                    markTheAnswer = true;
                }
                break;
            case R.id.imageButtonAnswerQuiz4:
                if(answerText4.getText().equals(correctAnswer) && !markTheAnswer) {
                    points +=1;
                    scorePKT.setText(""+points);
                    answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    answerButton4.setBackgroundResource(R.drawable.rounded_button_red);
                    if(answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                    markTheAnswer = true;
                }
                break;
            case R.id.buttonNextQuiz:
                if(nrWords > 0){
                    nrWords -=1;
                    clearButtons();
                    setQuestion(nrWords);
                }
                else{
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