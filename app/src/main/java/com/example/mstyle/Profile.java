package com.example.mstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    EditText id, name_input, name_email, name_phone;
    Button add_Button;
    Button delete_Button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        id = findViewById(R.id.id);
        name_input = findViewById(R.id.name_input);
        name_email = findViewById(R.id.email_input);
        name_phone = findViewById(R.id.phone_input);
        add_Button = findViewById(R.id.add);
        delete_Button = findViewById(R.id.delete_1);



            delete_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatabaseHelper db = new DatabaseHelper(Profile.this);
                    db.DeleteData(Integer.valueOf(id.getText(). toString()));
                }
            });


        add_Button.setOnClickListener(new View.OnClickListener() {

            @Override
             public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(Profile.this);
                myDB.addProfile(Integer.valueOf(id.getText(). toString(). trim()),
                        name_input.getText(). toString(). trim(),
                        name_email.getText(). toString(). trim(),
                        Integer.valueOf(name_phone.getText().toString().trim()));


            }
        });



    }
}