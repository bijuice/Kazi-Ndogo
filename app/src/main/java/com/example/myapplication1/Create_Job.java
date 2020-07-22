package com.example.myapplication1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class  Create_Job  extends AppCompatActivity {
    EditText jobtitle,paid,jobdesc;
    Spinner spinner;
    Button createjob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_job);

        jobtitle = findViewById(R.id.jobtitle);
        paid = findViewById(R.id.paid);
        jobdesc = findViewById(R.id.jobdesc);
        spinner = findViewById(R.id.spinner);
        createjob = findViewById(R.id.createjob);





    }
}