package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Insert extends AppCompatActivity {

    EditText card_num,mm,yy,security,cardholder,nic;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        nic = findViewById(R.id.nic);
        card_num = findViewById(R.id.card_num);
        mm = findViewById(R.id.mm);
        yy = findViewById(R.id.yy);
        security = findViewById(R.id.security);
        cardholder = findViewById(R.id.cardholder);
        add_button = findViewById(R.id.button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(nic.getText().toString())){
                    nic.setError("NIC is Required");
                    nic.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(card_num.getText().toString())){
                    card_num.setError("Card Number is Required");
                    card_num.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mm.getText().toString())){
                    mm.setError("Month is Required");
                    mm.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(yy.getText().toString())){
                    yy.setError("Year is Require");
                    yy.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(security.getText().toString())){
                    security.setError("Security Code is Require");
                    security.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(cardholder.getText().toString())){
                    cardholder.setError("Card Holder Name is Require");
                    cardholder.requestFocus();
                    return;
                }

                DatabaseHelper db = new DatabaseHelper(Insert.this);
                db.insert(nic.getText().toString().trim(),
                        Integer.valueOf(card_num.getText().toString().trim()),
                        Integer.valueOf(mm.getText().toString().trim()),
                        Integer.valueOf(yy.getText().toString().trim()),
                        Integer.valueOf(security.getText().toString().trim()),
                        cardholder.getText().toString().trim());
            }
        });

    }
}