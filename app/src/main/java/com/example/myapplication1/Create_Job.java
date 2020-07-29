package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class  Create_Job  extends AppCompatActivity {

    private Button createjob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_job);

        createjob = findViewById(R.id.createjob);

        createjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWaitScreen();
            }
        });
    }
    public void OpenWaitScreen(){
        Intent intent=new Intent(this,Wait_Screen.class);
        startActivity(intent);
    }
}