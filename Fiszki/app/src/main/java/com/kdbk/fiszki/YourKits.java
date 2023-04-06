package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class YourKits extends AppCompatActivity implements SelectListenerKits{

    NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String selectedMode = "";
    private String selectedLanguage = "";
    private ArrayList<ModelKits> modelKitsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_kits);

        Intent intent = getIntent();
        selectedMode = intent.getStringExtra("SelectedMode");
        selectedLanguage = intent.getStringExtra("SelectLanguage");

        modelKitsArray.add(new ModelKits("Zestaw 1","ILOSC FISZEK", "30", 1, 5));
        modelKitsArray.add(new ModelKits("Zestaw 2","ILOSC FISZEK", "18", 2, 31));
        modelKitsArray.add(new ModelKits("Zestaw 3","ILOSC FISZEK", "25", 3,21));
        modelKitsArray.add(new ModelKits("Zestaw 4","ILOSC FISZEK", "30", 4,3));
        modelKitsArray.add(new ModelKits("Zestaw 5","ILOSC FISZEK", "11", 5,15));

        mRecyclerView = findViewById(R.id.kitsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterKit(modelKitsArray, this, R.layout.recycler_view_kits);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(ModelKits modelKit) {
        //Toast.makeText(this, modelKit.getTextNumberKit(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("SelectLanguage", selectedLanguage);
        if(selectedMode.equals("quiz")){
            nextActivity.openActivity(QuizScreen.class, intent);
        } else if(selectedMode.equals("learn")){
            nextActivity.openActivity(LearningScreen.class, intent);
        }
    }
}