package com.example.myapplication1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class  Create_Job  extends AppCompatActivity {
    EditText jobtitle,paid,jobdesc;
    Spinner spinner;
    Button createjob;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_job);

        final String pNumber = "0712345678";

        jobtitle = findViewById(R.id.jobtitle);
        paid = findViewById(R.id.paid);
        jobdesc = findViewById(R.id.jobdesc);
        spinner = findViewById(R.id.spinner);
        createjob = findViewById(R.id.createjob);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.locations_array,android.R.layout.simple_spinner_item );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        createjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = jobtitle.getText().toString().trim();
                final String amount = paid.getText().toString().trim();
                final String description = paid.getText().toString().trim();
                final String location = spinner.getSelectedItem().toString();
                final String id = "003";

                databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(pNumber);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            jobhelper job = new jobhelper(title, location, amount,description,id );
                        databaseReference.child("user").child(pNumber).child("jobs").child(id).setValue(job);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });







    }
}