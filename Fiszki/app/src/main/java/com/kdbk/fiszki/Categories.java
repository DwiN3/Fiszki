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
    private ArrayList<ModelCategories> modelCategoriesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent intent = getIntent();
        selectedMode = intent.getStringExtra("SelectedMode");
        selectedLanguage = intent.getStringExtra("SelectLanguage");

        modelCategoriesArray.add(new ModelCategories(R.drawable.flagpl,"Polska", 1));
        modelCategoriesArray.add(new ModelCategories(R.drawable.flagang,"Wielka Brytania", 2));
        modelCategoriesArray.add(new ModelCategories(R.drawable.flagszw,"Szwecja", 3));
        modelCategoriesArray.add(new ModelCategories(R.drawable.reverse_button,"Zamiana",4));
        modelCategoriesArray.add(new ModelCategories(R.drawable.arrow,"Strzala",5));

        mRecyclerView = findViewById(R.id.categoriesRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterCategories(modelCategoriesArray, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(ModelCategories modelCategories) {
        //Toast.makeText(this, modelCategories.getNameCategory(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("SelectLanguage", selectedLanguage);
        if(selectedMode.equals("quiz")){
            nextActivity.openActivity(QuizScreen.class, intent);
        } else if(selectedMode.equals("learn")){
            nextActivity.openActivity(LearningScreen.class, intent);
        }
    }
}