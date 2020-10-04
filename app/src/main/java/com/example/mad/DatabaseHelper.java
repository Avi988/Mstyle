package com.example.mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Payment.db";
    private static final int DATABASE_VERSION = 1;

    private static String TABLE_NAME= "payment_detail";
    private static String COLUMN_NIC = "nic";
    private static String COLUMN_CARDNUM = "card_num";
    private static String COLUMN_MM = "mm";
    private static String COLUMN_YY = "yy";
    private static String COLUMN_SECURITY = "security";
    private static String COLUMN_CARDHOLDER = "cardholder";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_NIC + " TEXT PRIMARY KEY , " +
                COLUMN_CARDNUM + " INTEGER, " +
                COLUMN_MM + " INTEGER, " +
                COLUMN_YY + " INTEGER, " +
                COLUMN_SECURITY + " INTEGER, " +
                COLUMN_CARDHOLDER + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void insert(String nic, int card_num, int mm, int yy, int security, String cardholder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NIC, nic);
        cv.put(COLUMN_CARDNUM, card_num);
        cv.put(COLUMN_MM, mm);
        cv.put(COLUMN_YY, yy);
        cv.put(COLUMN_SECURITY, security);
        cv.put(COLUMN_CARDHOLDER, cardholder);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context,"faild", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }


    }

    void updateData(String nic,String card_num, String mm, String yy, String security, String cardholder){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NIC, nic);
        cv.put(COLUMN_CARDNUM, card_num);
        cv.put(COLUMN_MM, mm);
        cv.put(COLUMN_YY, yy);
        cv.put(COLUMN_SECURITY, security);
        cv.put(COLUMN_CARDHOLDER, cardholder);

        long result = db.update(TABLE_NAME, cv, "nic = ?",new String[] {nic});


        if(result == -1){
            Toast.makeText(context,"faild", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void DeleteData (String nic){


        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "nic = ?", new String[] {nic});

        if(result == -1){
            Toast.makeText(context,"faild", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getData() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;


    }
}
