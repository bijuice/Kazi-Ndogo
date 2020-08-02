package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class JobCategoriesTwo extends AppCompatActivity {

    TextView jobcatView;
    ListView jobcatView_list;
    ImageButton categoryBackbtn;
    private DatabaseReference databaseReference;

    public static final String EXTRA_JOBID = "com.example.myapplication1.EXTRA_JOBID";

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


//this is for Transport
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_categories);
        jobcatView=findViewById(R.id.jobcatView);
        jobcatView_list=findViewById(R.id.jobcatViewList);
        categoryBackbtn=findViewById(R.id.categoryBackbtn);

        final Intent intent = getIntent();
        String job_name_category=intent.getStringExtra(Search.EXTRA_CATEGORY);
        jobcatView.setText(job_name_category);


        databaseReference=FirebaseDatabase.getInstance().getReference("Transport");
        arrayAdapter=new ArrayAdapter<>(JobCategoriesTwo.this, android.R.layout.simple_list_item_1, arrayList);
        jobcatView_list.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value= snapshot.getValue(String.class);
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //when a job is clicked, it should take the user to the job listing page
        jobcatView_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent1= new Intent(view.getContext(), Job_ListingTwo.class);
                    startActivity(intent1);


                }
                if(position==1){
                    Intent intent1= new Intent(view.getContext(), Job_ListingTwo.class);
                    // intent1.putExtra(EXTRA_JOBID, jobcatView_list.getItemIdAtPosition(position));
                    startActivity(intent1);
                }
                if(position==2){
                    Intent intent1= new Intent(view.getContext(), Job_ListingTwo.class);
                    // intent1.putExtra(EXTRA_JOBID, jobcatView_list.getItemIdAtPosition(position));
                    startActivity(intent1);
                }

            }
        });

        categoryBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }
    public void goBack(){
        Intent intent2= new Intent(this, Search.class);
        startActivity(intent2);
    }

}
