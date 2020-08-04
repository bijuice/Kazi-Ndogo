package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Get_Started extends AppCompatActivity {
    //Animation after a user clicks register

    private static int SPLASH_SCREEN=3000;

    Animation topAnim, bottomAnim, bottomAnim2;
    ImageView image;
    TextView text, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__started);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        bottomAnim2= AnimationUtils.loadAnimation(this,R.anim.bottom_animation2);

        image=findViewById(R.id.getstartedimg);
        text=findViewById(R.id.getstarted);
        text2=findViewById(R.id.getstarted2);

        image.setAnimation(topAnim);
        text.setAnimation(bottomAnim);
        text2.setAnimation(bottomAnim2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Get_Started.this,Sign_In.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}
