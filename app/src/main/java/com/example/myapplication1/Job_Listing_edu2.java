package com.example.myapplication1;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Job_Listing_edu2 extends AppCompatActivity {
    EditText jobnameView, descriptionView;
    TextView payView, textView6, jobIDview, locationView, jobcatView;
    ImageButton dialerBtn, categoryBackbtn;
     FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__listing);

        jobnameView=findViewById(R.id.jobnameView);
        descriptionView = findViewById(R.id.descriptionView);
        jobcatView = findViewById(R.id.jobcatViewList);
        payView = findViewById(R.id.payView);
        textView6 = findViewById(R.id.textView6);
        jobIDview = findViewById(R.id.jobIDview);
        locationView = findViewById(R.id.locationView);

        dialerBtn = findViewById(R.id.dialerBtn);
        categoryBackbtn = findViewById(R.id.categoryBackbtn);


        firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("Education").document("two");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                jobnameView.setText(value.getString("title"));
                jobIDview.setText(value.getString("id"));
                descriptionView.setText(value.getString("description"));
                locationView.setText(value.getString("location"));
                payView.setText(value.getString("payment") + "Ksh.");
                textView6.setText(value.getString("number"));

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