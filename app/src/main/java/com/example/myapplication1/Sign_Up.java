package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sign_Up extends AppCompatActivity {
    Button  BtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        BtnRegister=findViewById(R.id.BtnRegister);

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGetStarted();
            }
        });

    }
    public void OpenGetStarted(){
        Intent intent=new Intent(this,Get_Started.class);
        startActivity(intent);
    }
}
