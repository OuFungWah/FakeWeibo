package com.example.fakeweibo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by 区枫华 on 2017/9/11.
 */

public class BaseDbHelper extends SQLiteOpenHelper {

    public static String db_name = "mydb";
    private static int db_version = 1;
    private static String sql = "CREATE TABLE "+db_name+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, content text, time date)";

    public BaseDbHelper(Context context) {
        super(context, db_name, null, db_version);
//        initSQL(className);
    }

    void initSQL(Class className){

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + db_name);
        onCreate(sqLiteDatabase);
    }
}
