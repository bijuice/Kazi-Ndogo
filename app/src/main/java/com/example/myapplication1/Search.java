package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Search extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "com.example.TransferInfo.EXTRA_CATEGORY";
    public static final String EXTRA_NUMBER = "com.example.TransferInfo.EXTRA_NUMBER";


    ListView searchListView;
    Button createJobBtn;
    TextView searchView;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        createJobBtn = findViewById(R.id.createJobBtn);
        searchListView = findViewById(R.id.searchListView);
        searchView = findViewById(R.id.searchView);


        firebaseAuth=FirebaseAuth.getInstance();

        Intent intent = getIntent();
        final String number=intent.getStringExtra(Sign_In.EXTRA_NUMBER);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.categories_array,android.R.layout.simple_list_item_1);


        searchListView.setAdapter(adapter2);

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    Intent intent = new Intent(view.getContext(), JobCategories.class);
                    String cat = "education";
                    intent.putExtra(EXTRA_CATEGORY,cat);
                    startActivity(intent);
                }
                if (position==1) {
                    Intent intent = new Intent(view.getContext(), JobCategoriesThree.class);
                    String cat = "art";
                    intent.putExtra(EXTRA_CATEGORY,cat);
                    startActivity(intent);
                }
                if (position==2) {
                    Intent intent = new Intent(view.getContext(), JobCategoriesFour.class);
                    String cat = "housework";
                    intent.putExtra(EXTRA_CATEGORY,cat);
                    startActivity(intent);
                }
                if (position==3) {
                    Intent intent = new Intent(view.getContext(), JobCategoriesOne.class);
                    String cat = "carpentry";
                    intent.putExtra(EXTRA_CATEGORY,cat);
                    startActivity(intent);
                }
                if (position==4) {
                    Intent intent = new Intent(view.getContext(), JobCategoriesTwo.class);
                    String cat = "transport";
                    intent.putExtra(EXTRA_CATEGORY,cat);
                    startActivity(intent);
                }
            }
        });

        createJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(number);
            }
        });

    }

    void nextActivity(String number) {

        Intent intent = new Intent(this, Create_Job.class);
        intent.putExtra(EXTRA_NUMBER,number);
        startActivity(intent);
    }



}
