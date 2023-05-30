package com.kdbk.fiszki.LocalDate;

import com.kdbk.fiszki.RecyclerView.Model.ModelCategories;
import com.kdbk.fiszki.R;
import java.util.ArrayList;

public class CategoriesArray {
    private static CategoriesArray instance = null;
    private ArrayList<ModelCategories> list = new ArrayList<>();

    private CategoriesArray(){
        add(new ModelCategories(R.drawable.flagpl,"Polska", 1));
        add(new ModelCategories(R.drawable.flagang,"Wielka Brytania", 2));
        add(new ModelCategories(R.drawable.flagszw,"Szwecja", 3));
        add(new ModelCategories(R.drawable.reverse_button,"Zamiana",4));
        add(new ModelCategories(R.drawable.arrow,"Strzala",5));
        add(new ModelCategories(R.drawable.arrow,"Strzala",6));
        add(new ModelCategories(R.drawable.arrow,"Strzala",7));
        add(new ModelCategories(R.drawable.arrow,"Strzala",8));
    }

    public static CategoriesArray getInstance(){
        if(instance == null){
            instance = new CategoriesArray();
        }
        return instance;
    }

    public void add(ModelCategories model){
        list.add(model);
    }

    public void remove(ModelCategories model){
        list.remove(model);
    }

    public void remove(int index){
        list.remove(index);
    }

    public ArrayList<ModelCategories> getList(){
        return list;
    }

}
