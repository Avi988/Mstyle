package com.example.mstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Women extends AppCompatActivity {

    Button womenselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women);

        womenselect = findViewById(R.id.womenselect);

        womenselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Women.this,Womprice.class);
                startActivity(intent);

            }
        });
    }
}