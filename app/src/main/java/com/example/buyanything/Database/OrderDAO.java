package com.example.buyanything.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.buyanything.models.Order;
import java.util.ArrayList;

public class OrderDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler dbHelper;

    public OrderDAO(Context context) {
        dbHelper = new DatabaseHandler(context);

        try{
            Open();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void Open() throws SQLException {
        mDatabase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void add(Order order){
        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.Order.COLUMN_NAME_ITEM_NAME, order.getItemName());
        values.put(DatabaseMaster.Order.COLUMN_NAME_NAME, order.getName());
        values.put(DatabaseMaster.Order.COLUMN_NAME_CONTACT, order.getContact());
        values.put(DatabaseMaster.Order.COLUMN_NAME_PLACE, order.getPlace());
        values.put(DatabaseMaster.Order.COLUMN_NAME_QTY, order.getQty());
        values.put(DatabaseMaster.Order.COLUMN_NAME_SIZE, order.getSize());
        values.put(DatabaseMaster.Order.COLUMN_NAME_PAYMENT_METHOD, order.getPaymentMethod());
        values.put(DatabaseMaster.Order.COLUMN_NAME_PRICE, order.getPrice());

        try{
            long newRowId = mDatabase.insert(DatabaseMaster.Order.TABLE_NAME, null, values);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<Order> GetAll(){
        ArrayList<Order> drugList = new ArrayList<>();

        String[] projection ={
            DatabaseMaster.Order.COLUMN_NAME_ID,
            DatabaseMaster.Order.COLUMN_NAME_ITEM_NAME,
            DatabaseMaster.Order.COLUMN_NAME_NAME,
            DatabaseMaster.Order.COLUMN_NAME_CONTACT,
            DatabaseMaster.Order.COLUMN_NAME_PLACE,
            DatabaseMaster.Order.COLUMN_NAME_QTY,
            DatabaseMaster.Order.COLUMN_NAME_SIZE,
            DatabaseMaster.Order.COLUMN_NAME_PAYMENT_METHOD,
            DatabaseMaster.Order.COLUMN_NAME_PRICE
        };

        String sortOrder = DatabaseMaster.Order.COLUMN_NAME_ID + " DESC";

        Cursor cursor = mDatabase.query(
            DatabaseMaster.Order.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        );

        while (cursor.moveToNext()){
            Order tempOrder = new Order();

            tempOrder.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_ID)));
            tempOrder.setItemName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_ITEM_NAME)));
            tempOrder.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_NAME)));
            tempOrder.setContact(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_CONTACT)));
            tempOrder.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PLACE)));
            tempOrder.setQty(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_QTY)));
            tempOrder.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_SIZE)));
            tempOrder.setPaymentMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PAYMENT_METHOD)));
            tempOrder.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PRICE)));

            drugList.add(tempOrder);
        }
        cursor.close();
        return drugList;
    }

    public boolean deleteByID(int id){
        try {
            mDatabase.delete(DatabaseMaster.Order.TABLE_NAME, DatabaseMaster.Order.COLUMN_NAME_ID +"="+ id, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public ArrayList<Order> searchByItemName(String name){
        ArrayList<Order> orderList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.Order.TABLE_NAME +" WHERE "+
                DatabaseMaster.Order.COLUMN_NAME_ITEM_NAME +" LIKE '% "+ name +" %'";

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{
                    Order tempOrder = new Order();

                    tempOrder.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_ID)));
                    tempOrder.setItemName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_ITEM_NAME)));
                    tempOrder.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_NAME)));
                    tempOrder.setContact(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_CONTACT)));
                    tempOrder.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PLACE)));
                    tempOrder.setQty(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_QTY)));
                    tempOrder.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_SIZE)));
                    tempOrder.setPaymentMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PAYMENT_METHOD)));
                    tempOrder.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PRICE)));

                    orderList.add(tempOrder);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return orderList;
    }

    public Order findById(int id){
        Order retOrder = new Order();

        String sqlQuery = "SELECT * FROM "+ DatabaseMaster.Order.TABLE_NAME +" WHERE "+
                DatabaseMaster.Order.COLUMN_NAME_ID +" = "+ id;

        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst() && cursor.getCount() == 1){
            retOrder.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_ID)));
            retOrder.setItemName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_ITEM_NAME)));
            retOrder.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_NAME)));
            retOrder.setContact(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_CONTACT)));
            retOrder.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PLACE)));
            retOrder.setQty(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_QTY)));
            retOrder.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_SIZE)));
            retOrder.setPaymentMethod(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PAYMENT_METHOD)));
            retOrder.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseMaster.Order.COLUMN_NAME_PRICE)));
        }
        cursor.close();
        return retOrder;
    }

    public boolean updateOrder(Order order){
        String sqlQuery = DatabaseMaster.Order.COLUMN_NAME_ID +" = ?";
        String id = String.valueOf(order.getId());
        String[] selectionArgs = {id};

        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.Order.COLUMN_NAME_ITEM_NAME, order.getItemName());
        values.put(DatabaseMaster.Order.COLUMN_NAME_NAME, order.getName());
        values.put(DatabaseMaster.Order.COLUMN_NAME_CONTACT, order.getContact());
        values.put(DatabaseMaster.Order.COLUMN_NAME_PLACE, order.getPlace());
        values.put(DatabaseMaster.Order.COLUMN_NAME_QTY, order.getQty());
        values.put(DatabaseMaster.Order.COLUMN_NAME_SIZE, order.getSize());
        values.put(DatabaseMaster.Order.COLUMN_NAME_PAYMENT_METHOD, order.getPaymentMethod());
        values.put(DatabaseMaster.Order.COLUMN_NAME_PRICE, order.getPrice());

        int count = mDatabase.update(
                DatabaseMaster.Order.TABLE_NAME,
                values,
                sqlQuery,
                selectionArgs
        );

        return count != 0;
    }
}
