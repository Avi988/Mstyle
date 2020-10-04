package com.example.buyanything.Database;

import android.provider.BaseColumns;

public final class DatabaseMaster {

    private DatabaseMaster() {}

    public static class Order implements BaseColumns{

        public static final String TABLE_NAME = "orders";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ITEM_NAME = "itemName";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CONTACT = "contact";
        public static final String COLUMN_NAME_PLACE = "place";
        public static final String COLUMN_NAME_QTY = "qty";
        public static final String COLUMN_NAME_SIZE = "size";
        public static final String COLUMN_NAME_PAYMENT_METHOD = "paymentMethod";
        public static final String COLUMN_NAME_PRICE = "price";
    }

}
