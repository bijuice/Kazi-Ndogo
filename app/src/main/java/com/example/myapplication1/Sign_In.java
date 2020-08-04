package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Sign_In extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.TransferInfo.EXTRA_NUMBER";

    TextView textView5;
    EditText email,password, phone;
    Button LogIn;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        textView5=findViewById(R.id.textView5);
        LogIn=findViewById(R.id.LogIn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone=findViewById(R.id.phone);

        firebaseAuth=FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

//when user clicks login, it uses inbuilt authentication of firebase to create a user with a username and password
        //we have added another mechanism for authentication using phone number and so we have implemented two-factor authentication
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String unum = email.getText().toString().trim();
                final String upass = password.getText().toString().trim();
                final String uphone=phone.getText().toString().trim();

                if (TextUtils.isEmpty(unum)) {
                    email.setError("Username is required");
                    return;
                }
                if (TextUtils.isEmpty(upass)) {
                    password.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(uphone)){
                    phone.setError("Phone number is required");
                }
//user is created here
                firebaseAuth.signInWithEmailAndPassword(unum, upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Sign_In.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            OpenSearch();
                        }
                        else{
                            Toast.makeText(Sign_In.this, "Invalid Details!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
                public void OpenSearch() {
                    String number = phone.getText().toString();
                    Intent intent = new Intent(Sign_In.this, Search.class);
                    intent.putExtra(EXTRA_NUMBER, number);
                    startActivity(intent);
                }
            });


              textView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenSignUp();
                }
            });
    }



            public void OpenSignUp() {
                Intent intent = new Intent(Sign_In.this, Sign_Up.class);
                startActivity(intent);
            }


        }



