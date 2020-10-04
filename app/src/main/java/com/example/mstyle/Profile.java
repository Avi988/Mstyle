package com.example.mstyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {

    EditText name_input, email_input, phone_input,id;
    Button add_Buton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        id = findViewById(R.id.id);
        name_input = findViewById(R.id.name_input);
        email_input = findViewById(R.id.email_input);
        phone_input = findViewById(R.id.phone_input);
        add_Buton = findViewById(R.id.add_Button);

        add_Buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new  DatabaseHelper(Profile.this);
                myDB.addProfile(Integer.valueOf(id.getText().toString().trim()),
                        name_input.getText().toString().trim(),
                    email_input.getText(). toString().trim(),
                    Integer.valueOf(phone_input.getText().toString().trim()));
            }
        });


    }
}