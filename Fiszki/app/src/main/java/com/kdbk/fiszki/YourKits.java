package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class YourKits extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_kits);

        ArrayList<ModelKits> modelKitsArray = new ArrayList<>();
        modelKitsArray.add(new ModelKits("Zestaw 1","ILOSC FISZEK", "30"));
        modelKitsArray.add(new ModelKits("Zestaw 2","ILOSC FISZEK", "18"));
        modelKitsArray.add(new ModelKits("Zestaw 3","ILOSC FISZEK", "25"));
        modelKitsArray.add(new ModelKits("Zestaw 4","ILOSC FISZEK", "30"));
        modelKitsArray.add(new ModelKits("Zestaw 5","ILOSC FISZEK", "11"));

        mRecyclerView = findViewById(R.id.kitsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterKit(modelKitsArray);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}