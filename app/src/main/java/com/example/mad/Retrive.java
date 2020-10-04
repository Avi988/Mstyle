package com.example.mad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Retrive extends AppCompatActivity {

    TextView nic;
    Button retrive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive);

        nic = findViewById(R.id.nic_ret);
        retrive = findViewById(R.id.retrive);
        ViewData();
    }


    public void ViewData(){
        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper db = new DatabaseHelper(Retrive.this);
                Cursor res = db.getData();
                if (res.getCount() == 0){

                    showMassage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToFirst()){
                    buffer.append("NIC: "+ res.getString(0)+"\n");
                    buffer.append("Card Number: "+ res.getString(1)+"\n");
                    buffer.append("MM: "+ res.getString(2)+"\n");
                    buffer.append("YY: "+ res.getString(3)+"\n");
                    buffer.append("Security Code: "+ res.getString(4)+"\n");
                    buffer.append("Cardholder Name: "+ res.getString(5)+"\n\n");
                }

                showMassage("Details", buffer.toString());


            }
        });
    }

    public void showMassage(String title, String Massage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Massage);
        builder.show();
    }

}
