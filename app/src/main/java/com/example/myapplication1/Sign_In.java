package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sign_In extends AppCompatActivity {

    TextView textView5;
    Button LogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        textView5=findViewById(R.id.textView5);
        LogIn=findViewById(R.id.LogIn);

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUp();
            }
        });

    }

    public void OpenSignUp(){
        Intent intent=new Intent(this,Sign_Up.class);
        startActivity(intent);
    }

}
