package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity {
    Button  BtnRegister;
    EditText name,phoneNumber,email,password,password2;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

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


        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
//this is for sign up, the data gets stored in real time database and also the authentication database

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String uname = name.getText().toString().trim();
                final String uphoneNumber = phoneNumber.getText().toString().trim();
                final String uemail = email.getText().toString().trim();
                String upassword = password.getText().toString().trim();
                String upassword2 = password2.getText().toString().trim();

                if(TextUtils.isEmpty(uemail)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(uname)){
                    name.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(uphoneNumber)){
                    phoneNumber.setError("Phone number is required");
                    return;
                }
                if(TextUtils.isEmpty(upassword)){
                    password.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(upassword2)){
                    password2.setError("Repeat password is required");
                    return;
                }

                if(!upassword.equals(upassword2)){
                    Toast.makeText(Sign_Up.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();

                }
                else {

                    firebaseAuth.createUserWithEmailAndPassword(uemail, upassword2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(Sign_Up.this, "Account created", Toast.LENGTH_SHORT).show();
                                OpenGetStarted();
                            }
                            else {
                                Toast.makeText(Sign_Up.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    User user = new User(uname,uemail,uphoneNumber);
                    databaseReference.child("user").child(uphoneNumber).setValue(user);



                }



            }
        });

    }
    public void OpenGetStarted(){
        Intent intent=new Intent(this,Get_Started.class);
        startActivity(intent);
    }
}
