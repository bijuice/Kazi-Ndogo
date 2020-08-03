package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    Spinner spinner,spinner2;
    Button createjob;
    ImageButton categoryBackbtn;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_job);

        Intent intent = getIntent();
        final String pNumber=intent.getStringExtra(Sign_In.EXTRA_NUMBER);
        final String[] id = new String[1];

        jobtitle = findViewById(R.id.jobtitle);
        paid = findViewById(R.id.paid);
        jobdesc = findViewById(R.id.jobdesc);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        createjob = findViewById(R.id.createjob);
        categoryBackbtn = findViewById(R.id.categoryBackbtn);

        //Adapters linking to an XML resource file that populates the drop down menus for categories and locations

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.locations_array,android.R.layout.simple_spinner_item );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.categories_array,android.R.layout.simple_spinner_item );

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

        //THis code gets the idcount which finds the current job id and ennumerates it +1 so each job ID is unique
        //It is not working perfectly atm because it cannot save the idcount on the database

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

        //This is the button event listener for pushing values to the database

        createjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Capture data from edit texts and drop down menus

                final String title = jobtitle.getText().toString().trim();
                final String amount = paid.getText().toString().trim();
                final String description = jobdesc.getText().toString().trim();
                final String location = spinner.getSelectedItem().toString();
                final String category = spinner2.getSelectedItem().toString();

                //Pushes data to database with the aid of jobhelper class.

                databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(pNumber);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            jobhelper job = new jobhelper(title, location, amount,description,id[0],category);
                        databaseReference.child("jobs").child(id[0]).setValue(job);
                        Toast.makeText(Create_Job.this, "Job Created Successfully!", Toast.LENGTH_SHORT);
                        prevActivity();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        categoryBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevActivity();
            }
        });

    }

    void prevActivity(){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}