package com.example.mad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Update extends AppCompatActivity {


    TextView nic,card_num,mm,yy,security,cardholder;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nic = findViewById(R.id.nic);
        card_num = findViewById(R.id.card_num);
        mm = findViewById(R.id.mm);
        yy = findViewById(R.id.yy);
        security= findViewById(R.id.security);
        cardholder = findViewById(R.id.cardholder);
        button = findViewById(R.id.button);
        

        UpdateDetails();

    }

    public void UpdateDetails(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(Update.this);
               db.updateData(nic.getText().toString(),card_num.getText().toString(),
                        mm.getText().toString(), yy.getText().toString(), security.getText().toString(), cardholder.getText().toString());
            }
        });
    }

}