package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Categories extends AppCompatActivity implements SelectListenerCategories {
    NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String selectedMode = "";
    private String selectedLanguage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent intent = getIntent();
        selectedMode = intent.getStringExtra("SelectedMode");
        selectedLanguage = intent.getStringExtra("SelectLanguage");

        ArrayList<ModelCategories> modelCategoriesArray = new ArrayList<>();
        modelCategoriesArray.add(new ModelCategories(R.drawable.flagpl,"Polska"));
        modelCategoriesArray.add(new ModelCategories(R.drawable.flagang,"Wielka Brytania"));
        modelCategoriesArray.add(new ModelCategories(R.drawable.flagszw,"Szwecja"));
        modelCategoriesArray.add(new ModelCategories(R.drawable.reverse_button,"Zamiana"));
        modelCategoriesArray.add(new ModelCategories(R.drawable.arrow,"Strzala"));

        mRecyclerView = findViewById(R.id.categoriesRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterCategories(modelCategoriesArray, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(ModelCategories modelCategories) {
        Intent intent = new Intent();
        intent.putExtra("SelectedMode", selectedMode);
        intent.putExtra("SelectLanguage", selectedLanguage);
        if(selectedMode.equals("quiz")){
            nextActivity.openActivity(QuizScreen.class);
        } else if(selectedMode.equals("learn")){
            nextActivity.openActivity(LearningScreen.class);
        }
    }
}