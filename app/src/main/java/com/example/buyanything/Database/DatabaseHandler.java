package com.example.buyanything.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "buyAnything";

    private static final String CREATE_TABLE_ORDERS =
            "CREATE TABLE `"+ DatabaseMaster.Order.TABLE_NAME +"` " +
            "( `"+ DatabaseMaster.Order.COLUMN_NAME_ID +"` INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL  , " +
            " `"+ DatabaseMaster.Order.COLUMN_NAME_ITEM_NAME +"` VARCHAR(100) , " +
            " `"+ DatabaseMaster.Order.COLUMN_NAME_NAME +"` VARCHAR(100) , " +
            " `"+ DatabaseMaster.Order.COLUMN_NAME_CONTACT +"` VARCHAR(40) , " +
            " `"+ DatabaseMaster.Order.COLUMN_NAME_PLACE +"` VARCHAR(1000) , " +
            " `"+ DatabaseMaster.Order.COLUMN_NAME_QTY +"` INTEGER , " +
            " `"+ DatabaseMaster.Order.COLUMN_NAME_SIZE +"` INTEGER , " +
            " `"+ DatabaseMaster.Order.COLUMN_NAME_PAYMENT_METHOD +"` VARCHAR(250) , " +
            " `"+ DatabaseMaster.Order.COLUMN_NAME_PRICE +"` REAL " +
            "   ) ";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseMaster.Order.TABLE_NAME);

        onCreate(db);
    }
}
