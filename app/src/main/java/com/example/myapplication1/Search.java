package com.example.myapplication1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {
    ListView searchListView;
    Button createJobBtn;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        createJobBtn = findViewById(R.id.createJobBtn);
        searchListView = findViewById(R.id.searchListView);
        searchView = findViewById(R.id.searchView);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.categories_array,android.R.layout.simple_list_item_1);


        searchListView.setAdapter(adapter2);
    }
}
