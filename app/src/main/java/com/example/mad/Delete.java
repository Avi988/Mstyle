package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Delete extends AppCompatActivity {

    TextView nic;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        nic = findViewById(R.id.nic);
        button = findViewById(R.id.delete);
        DeleteData();

    }

    public void DeleteData(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper db = new DatabaseHelper(Delete.this);
                db.DeleteData(nic.getText().toString());

            }
        });
    }
}