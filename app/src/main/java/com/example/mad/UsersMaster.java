package com.example.mad;

import android.provider.BaseColumns;

public class UsersMaster {

    private UsersMaster () {}

    public static class Users implements BaseColumns{
        private static final String TABLE_NAME= "payment_detail";
        private static final String COLUMN_NIC = "nic";
        private static final String COLUMN_CARDNUM = "card_num";
        private static final String COLUMN_MM = "mm";
        private static final String COLUMN_YY = "yy";
        private static final String COLUMN_SECURITY = "security";
        private static final String COLUMN_CARDHOLDER = "cardholder";
    }

}
