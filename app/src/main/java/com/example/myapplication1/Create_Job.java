package com.example.myapplication1;

import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
>>>>>>> parent of e29a1f8... Merge pull request #19 from bijuice/abdul-mainmenu

import androidx.appcompat.app.AppCompatActivity;

public class  Create_Job  extends AppCompatActivity {
<<<<<<< HEAD
=======
    EditText jobtitle,paid,jobdesc;
    Spinner spinner,spinner2;
    Button createjob;
    DatabaseReference databaseReference;

>>>>>>> parent of e29a1f8... Merge pull request #19 from bijuice/abdul-mainmenu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_job);
<<<<<<< HEAD
=======

        final String pNumber = "0712345678";
        final String[] id = new String[1];

        jobtitle = findViewById(R.id.jobtitle);
        paid = findViewById(R.id.paid);
        jobdesc = findViewById(R.id.jobdesc);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        createjob = findViewById(R.id.createjob);

        //Adapters linking to an XML resource file that populates the drop down menus for categories and locations

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.locations_array,android.R.layout.simple_spinner_item );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.categories_array,android.R.layout.simple_spinner_item );

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

        //THis code gets the idcount which finds the current job id and ennumerates it +1 so each job ID is unique
        //It is not working perfectly atm because it cannot save the idcount on the database

        databaseReference = FirebaseDatabase.getInstance().getReference().child("idcount");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id[0] = snapshot.getValue().toString();
                Integer jobID = Integer.parseInt(id[0])+1;
                id[0] = jobID.toString();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        //This is the button event listener for pushing values to the database

        createjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Capture data from edit texts and drop down menus

                final String title = jobtitle.getText().toString().trim();
                final String amount = paid.getText().toString().trim();
                final String description = jobdesc.getText().toString().trim();
                final String location = spinner.getSelectedItem().toString();
                final String category = spinner2.getSelectedItem().toString();

                //Pushes data to database with the aid of jobhelper class.

                databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(pNumber);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            jobhelper job = new jobhelper(title, location, amount,description,id[0],category);
                        databaseReference.child("jobs").child(id[0]).setValue(job);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });







>>>>>>> parent of e29a1f8... Merge pull request #19 from bijuice/abdul-mainmenu
    }
}