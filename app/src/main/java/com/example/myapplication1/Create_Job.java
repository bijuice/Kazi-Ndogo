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
        final String[] id = new String[1];

        jobtitle = findViewById(R.id.jobtitle);
        paid = findViewById(R.id.paid);
        jobdesc = findViewById(R.id.jobdesc);
        spinner = findViewById(R.id.spinner);
        createjob = findViewById(R.id.createjob);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.locations_array,android.R.layout.simple_spinner_item );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("idcount");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id[0] = snapshot.getValue().toString();
                Integer jobID = Integer.parseInt(id[0])+1;
                id[0] = jobID.toString();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });







        createjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = jobtitle.getText().toString().trim();
                final String amount = paid.getText().toString().trim();
                final String description = paid.getText().toString().trim();
                final String location = spinner.getSelectedItem().toString();


                databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(pNumber);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            jobhelper job = new jobhelper(title, location, amount,description,id[0]);
                        databaseReference.child("jobs").child(id[0]).setValue(job);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });







    }
}