package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.kdbk.fiszki.Retrofit.Flashcards;
import com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.widget.Spinner;
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
    private Button add;
    private String[] newFlashcard;
    private String nrKit, word, translateWord, sampleSentence, translateSampleSentence, category="inne";
    private EditText  kitText, wordText, translateWordText,exampleText, translateExampleText;
    private Spinner categorySpiner;

    private FlashcardArray flashcardArray = FlashcardArray.getInstance();
    private ArrayList<ModelAddFlashcard> list = flashcardArray.getList();
    private Token token  = Token.getInstance();
    ArrayList<ModelEditFlashcard> subList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);
        setID();

        List<String> categories = new ArrayList<>();
        categories.add("zwierzęta");
        categories.add("przedmioty");
        categories.add("miejsca");
        categories.add("inne");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpiner.setAdapter(adapter);
        categorySpiner.setSelection(adapter.getPosition("inne"));

        add.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
              //setFlashcardRecycle();
              //addFlashcardToBase();
                Toast.makeText(ActivityAddFlashcard.this, "Trwa dodawanie fiszki", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getWord(){

    }


    private void setFlashcardRecycle(){
        EditFlashcardArray editFlashcardArray = EditFlashcardArray.getInstance();
        int nextIndex = editFlashcardArray.getWords() + 1;
        subList.add(new ModelEditFlashcard(R.drawable.flagpl, word,1, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagang, translateWord, 2, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagpl, sampleSentence, 3, nextIndex));
        subList.add(new ModelEditFlashcard(R.drawable.flagang, translateSampleSentence, 4, nextIndex));
        editFlashcardArray.getAllList().put(nextIndex, subList);
        newFlashcard = new String[]{nrKit, word, translateWord, sampleSentence, translateSampleSentence, category};

        // Lokalne fiszki
        newFlashcard = new String[]{nrKit, word, translateWord, sampleSentence, translateSampleSentence};
        WordsArray wordsArray = new WordsArray();
        wordsArray.addWord(newFlashcard);
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
        nrKit = "";
        //category="";
        word = "";
        translateWord = "";
        sampleSentence = "";
        translateSampleSentence = "";
    }


    @Override
    public void onItemClicked(ModelAddFlashcard modelAddFlashcard) {

    }

    public void addFlashcardToBase() {
        String collectionName = "1";
        String language = "english";

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
        Flashcards post = new Flashcards(nrKit, language, category, word, translateWord, sampleSentence, translateSampleSentence);
        Call<Flashcards> call = jsonPlaceholderAPI.addFlashcard(nrKit, post);

        call.enqueue(new Callback<Flashcards>() {
            @Override
            public void onResponse(Call<Flashcards> call, Response<Flashcards> response) {
                //System.out.println("KODZIK =" + response);
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityAddFlashcard.this, "Błędne dane", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Flashcards> call, Throwable t) {
                Toast.makeText(ActivityAddFlashcard.this, "Poprawnie dodano fiszkę", Toast.LENGTH_SHORT).show();
                resetFlashcardRecycle();
            }
        });
    }


        private void setID() {
        add = findViewById(R.id.buttonAcceptFlashcard);
        kitText = findViewById(R.id.kit_text_add);
        categorySpiner = findViewById(R.id.category_spinner_add);
        wordText = findViewById(R.id.word_text_add);
        translateWordText = findViewById(R.id.translate_text_add);
        exampleText = findViewById(R.id.example_text_add);
        translateExampleText = findViewById(R.id.translate_example_text_add);
    }

    @Override
    public void onEditTextChanged(int cardId, String newText) {

    }
}