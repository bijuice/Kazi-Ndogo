package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sign_In extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.TransferInfo.EXTRA_NUMBER";

    TextView textView5;
    EditText email,password;
    Button LogIn;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);

        textView5=findViewById(R.id.textView5);
        LogIn=findViewById(R.id.LogIn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String unum = email.getText().toString().trim();
                final String upass = password.getText().toString().trim();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(unum);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        try {
                            String name = snapshot.child("number").getValue().toString();
                            String pass = snapshot.child("password").getValue().toString();
                            if (upass.equals(pass)){
                                OpenSearch();
                            } else {
                                Toast.makeText(Sign_In.this, "Invalid username or password!", Toast.LENGTH_SHORT);

                            }
                        } catch (NullPointerException e){
                            System.out.println(FirebaseError.ERROR_USER_NOT_FOUND);
                            Toast.makeText(Sign_In.this, "Invalid username or password!", Toast.LENGTH_SHORT);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUp();
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSearch();
            }
        });


    }

    public void OpenSignUp(){
        Intent intent=new Intent(this,Sign_Up.class);
        startActivity(intent);
    }

    public void OpenSearch(){
        String number = email.getText().toString();
        Intent intent=new Intent(this,Search.class);
        intent.putExtra(EXTRA_NUMBER,number);
        startActivity(intent);
    }

}
