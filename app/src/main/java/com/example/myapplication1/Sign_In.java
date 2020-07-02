package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sign_In extends AppCompatActivity {

    Button LogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        LogIn=findViewById(R.id.LogIn);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMainMenu();
            }
        });
    }
    public void OpenMainMenu(){
        Intent intent= new Intent(this,Main_Menu.class);
        startActivity(intent);
    }
}
