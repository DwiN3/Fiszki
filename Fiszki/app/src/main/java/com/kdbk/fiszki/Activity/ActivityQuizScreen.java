package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.Other.SetGameClass;
import com.kdbk.fiszki.R;

public class ActivityQuizScreen extends AppCompatActivity implements View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);
    private Button next, exit;
    private ImageView imageWordQuiz;
    private TextView answerText1, answerText2, answerText3, answerText4, nameWordQuizText, sticksLeftQuizText, scorePKT, userPKTQuiz;
    private ImageButton answerButton1, answerButton2, answerButton3, answerButton4;
    private String selectedLanguage = "", selectedID="", selectedData="";
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private boolean markTheAnswer = false;
    private String correctAnswer;
    private int nrWords, allWords;
    SetGameClass t;


    // TEST QUIZU
    private int points = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        setID();

        Intent intent = getIntent();
        selectedLanguage = intent.getStringExtra("SelectLanguage");
        selectedID = intent.getStringExtra("SelectID");
        selectedData = intent.getStringExtra("SelectData");
        t =  new SetGameClass(selectedData);

        //System.out.println(selectedID);
        //System.out.println(selectedData);


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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
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
                    Intent intent = new Intent();
                    intent.putExtra("Points", String.valueOf(points));
                    intent.putExtra("Words", String.valueOf(allWords));
                    nextActivity.openActivity(ActivityQuizEnd.class, intent);
                }
                break;
            case R.id.buttonExitQuiz:
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
    }

    public void clearButtons(){
        answerButton1.setBackgroundResource(R.drawable.rounded_button);
        answerButton2.setBackgroundResource(R.drawable.rounded_button);
        answerButton3.setBackgroundResource(R.drawable.rounded_button);
        answerButton4.setBackgroundResource(R.drawable.rounded_button);
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