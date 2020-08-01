package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Job_Listing extends AppCompatActivity {
    EditText jobnameView, descriptionView;
    TextView payView, textView6, jobIDview, locationView, jobcatView;
    ImageButton dialerBtn, categoryBackbtn;
    ImageView locationImg;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__listing);

        jobcatView=findViewById(R.id.jobcatViewList);
        descriptionView=findViewById(R.id.descriptionView);
        payView=findViewById(R.id.payView);
        textView6=findViewById(R.id.textView6);
        jobIDview=findViewById(R.id.jobIDview);
        locationView=findViewById(R.id.locationView);
        jobcatView=findViewById(R.id.jobcatViewList);
        dialerBtn=findViewById(R.id.dialerBtn);
        categoryBackbtn=findViewById(R.id.categoryBackbtn);
        locationImg=findViewById(R.id.locationImg);

        Intent intent=getIntent();
        String id=intent.getStringExtra(JobCategories.EXTRA_JOBID);

        databaseReference= FirebaseDatabase.getInstance().getReference("jobs").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String title=snapshot.child("title").getValue().toString();
                String uid=snapshot.child("id").getValue().toString();
                String description=snapshot.child("description").getValue().toString();
                String location=snapshot.child("location").getValue().toString();
                String pay=snapshot.child("payment").getValue().toString();
                String contact=snapshot.child("number").getValue().toString();

                jobnameView.setText(title);
                jobIDview.setText(uid);
                descriptionView.setText(description);
                locationView.setText(location);
                payView.setText(pay);
                textView6.setText(contact);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //back button
        categoryBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goprevious();
            }
        });

        //dialer button
        dialerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialer();


            }
        });
    }
    public void goprevious(){
        Intent intent= new Intent(this, JobCategories.class);
        startActivity(intent);
    }
    public void opendialer(){
        String number=textView6.getText().toString().trim();
        Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)));
        startActivity(intent);
    }



}