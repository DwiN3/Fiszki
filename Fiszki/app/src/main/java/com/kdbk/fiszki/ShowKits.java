package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ShowKits extends AppCompatActivity implements SelectListenerWordsKits {

    NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String selectedMode = "";
    private String selectedLanguage = "";
    private ArrayList<ModelWordsKits> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kits);

        list.add(new ModelWordsKits("1",1));
        list.add(new ModelWordsKits("2",2));
        list.add(new ModelWordsKits("3",3));
        list.add(new ModelWordsKits("4",4));

        mRecyclerView = findViewById(R.id.showWordKitsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterWordsKits(list, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onItemClicked(ModelWordsKits modelWordsKits) {
        System.out.println(modelWordsKits.getID());
        nextActivity.openActivity(EditFlashcard.class);
    }
}