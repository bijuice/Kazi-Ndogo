package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity {
    Button  BtnRegister;
    EditText name,phoneNumber,email,password,password2;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        BtnRegister=findViewById(R.id.BtnRegister);
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = name.getText().toString().trim();
                String uphoneNumber = phoneNumber.getText().toString().trim();
                String uemail = email.getText().toString().trim();
                String upassword = password.getText().toString().trim();
                String upassword2 = password2.getText().toString().trim();

                if(!upassword.equals(upassword2)){
                    Toast.makeText(Sign_Up.this, "Passwords do not match!", Toast.LENGTH_SHORT);

                }
                else {
                    User user = new User(uname,uemail,upassword,uphoneNumber);
                    databaseReference.child("user").child(uphoneNumber).setValue(user);
                    Toast.makeText(Sign_Up.this, "Account created", Toast.LENGTH_SHORT);
                    OpenGetStarted();
                }

            }
        });

    }
    public void OpenGetStarted(){
        Intent intent=new Intent(this,Get_Started.class);
        startActivity(intent);
    }
}
