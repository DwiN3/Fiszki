package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class YourKits extends AppCompatActivity implements SelectListenerKits{

    NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView noKitsInfo;
    private String selectedMode = "";
    private String selectedLanguage = "";
    private KitsArray kitsArray = KitsArray.getInstance();
    private ArrayList<ModelKits> list = kitsArray.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_kits);
        setID();

        Intent intent = getIntent();
        selectedMode = intent.getStringExtra("SelectedMode");
        selectedLanguage = intent.getStringExtra("SelectLanguage");

        mRecyclerView = findViewById(R.id.kitsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterKit(list, this, R.layout.recycler_view_kits);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if(list.isEmpty()) noKitsInfo.setVisibility(View.VISIBLE);
        else noKitsInfo.setVisibility(View.GONE);
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

    private void setID() {
        noKitsInfo = findViewById(R.id.textNoKits);
    }
}