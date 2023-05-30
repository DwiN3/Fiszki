package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.kdbk.fiszki.RecyclerView.Adaper.AdapterCategories;
import com.kdbk.fiszki.RecyclerView.Model.ModelCategories;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerCategories;
import java.util.ArrayList;

public class ActivityCategories extends AppCompatActivity implements SelectListenerCategories {
    private NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String selectedMode = "", selectedLanguage = "";

    // Do wywalnienia
    private com.kdbk.fiszki.Arrays.CategoriesArray CategoriesArray = com.kdbk.fiszki.Arrays.CategoriesArray.getInstance();
    private ArrayList<ModelCategories> list = CategoriesArray.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent intent = getIntent();
        selectedMode = intent.getStringExtra("SelectedMode");
        selectedLanguage = intent.getStringExtra("SelectLanguage");

        mRecyclerView = findViewById(R.id.categoriesRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterCategories(list, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(ModelCategories modelCategories) {
        Intent intent = new Intent();
        intent.putExtra("SelectLanguage", selectedLanguage);
        intent.putExtra("SelectID", ""+modelCategories.getID());
        intent.putExtra("SelectData", "category");

        if(selectedMode.equals("quiz")){
            nextActivity.openActivity(ActivityQuizScreen.class, intent);
        } else if(selectedMode.equals("learn")){
            nextActivity.openActivity(ActivityLearningScreen.class, intent);
        }
    }
}