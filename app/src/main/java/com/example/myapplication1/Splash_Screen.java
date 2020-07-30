package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Splash_Screen extends AppCompatActivity {

    private Button SignIn,SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        SignIn=findViewById(R.id.SignIn);
        SignUp=findViewById(R.id.SignUp);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignIn();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUp();
            }
        });
    }

    public void OpenSignIn(){
        Intent intent = new Intent(this,Sign_In.class);
        startActivity(intent);
    }

    public void OpenSignUp(){
        Intent intent = new Intent(this,Sign_Up.class);
        startActivity(intent);
    }
}
