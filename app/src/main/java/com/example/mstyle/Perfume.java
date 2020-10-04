package com.example.mstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Perfume extends AppCompatActivity {

    Button perfumeselect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfume);

        perfumeselect = findViewById(R.id.perfumeselect);

        perfumeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfume.this,perfumeprice.class);
                startActivity(intent);

            }
        });

    }
}