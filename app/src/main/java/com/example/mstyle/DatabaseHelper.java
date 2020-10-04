package com.example.mstyle;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Customer.db";
    private static final int DATABASE_VERSION =1;

    private static final String TABLE_NAME ="customer_table";
    private static final String COLUM_ID ="id";
    private static final String COLUM_NAME ="name";
    private static final String COLUM_EMAIL ="email";
    private static final String COLUM_PHONE ="phone";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUM_ID + "INTEGER PRIMARY KEY, "+
                        COLUM_NAME + " TEXT, "+
                        COLUM_EMAIL + " TEXT, "+
                        COLUM_PHONE + " INTERGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    }

    void addProfile(int id,String name, String email, int phone){
        SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUM_ID, id);
            cv.put(COLUM_NAME, name);
            cv.put(COLUM_EMAIL, email);
            cv.put(COLUM_PHONE, phone);
            long result = db.insert(TABLE_NAME, null,cv);
            if (result == -1){
                Toast.makeText(context,"failed", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"Add Successfully", Toast.LENGTH_SHORT).show();
            }
        }


}
