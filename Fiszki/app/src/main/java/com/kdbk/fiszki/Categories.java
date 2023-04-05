package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ArrayList<ModelCategories> modelCategoriesArray = new ArrayList<>();
        modelCategoriesArray.add(new ModelCategories(R.drawable.flagpl,"Polska"));
        modelCategoriesArray.add(new ModelCategories(R.drawable.flagang,"Wielka Brytania"));
        modelCategoriesArray.add(new ModelCategories(R.drawable.flagszw,"Szwecja"));
        modelCategoriesArray.add(new ModelCategories(R.drawable.reverse_button,"Zamiana"));
        modelCategoriesArray.add(new ModelCategories(R.drawable.arrow,"Strzala"));

        mRecyclerView = findViewById(R.id.categoriesRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterCategories(modelCategoriesArray);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}