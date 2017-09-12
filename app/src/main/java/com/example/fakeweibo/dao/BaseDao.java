package com.example.fakeweibo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.fakeweibo.dao.BaseDbHelper.db_name;

/**
 * Created by 区枫华 on 2017/9/12.
 */

public class BaseDao {

    private BaseDbHelper helper;
    private Context context;

    public BaseDao(Context context){
        this.context = context;
        helper = new BaseDbHelper(context);
    }

    public boolean insert(String content){
        boolean flag = false;
        long id = -1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("content",content);
        contentValues.put("time",simpleDateFormat.format(System.currentTimeMillis()));
        try {
            id = database.insert(db_name,null,contentValues);
            flag = (id!=-1);
        }catch (Exception e){
            e.printStackTrace();
            Log.d("数据库操作","插入错误");
        }finally {
            database.close();
        }
        return flag;
    }

    public List<Map<String,String>> selectAll(){
        List<Map<String,String>> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        try{
            sqLiteDatabase = helper.getWritableDatabase();
            cursor = sqLiteDatabase.query(true,db_name,null,null,null,null,null,null,null);
            int cols_len = cursor.getColumnCount();
            while (cursor.moveToNext()){
                Map<String,String> map = new HashMap<>();
                for(int i = 0;i<cols_len;i++){
                    String cols_name = cursor.getColumnName(i);
                    String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                    if(cols_value==null){
                        cols_value="";
                    }
                    map.put(cols_name,cols_value);
                }
                list.add(map);
            }
        }catch (Exception e){

        }finally {
            sqLiteDatabase.close();
        }
        return list;
    }

}
