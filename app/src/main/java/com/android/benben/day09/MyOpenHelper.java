package com.android.benben.day09;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LiYuanxiong on 2016/7/13 16:41.
 * Desribe:
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        /**
         * context
         * name 数据库名称
         * factory 游标工厂
         * version 版本
         */
        super(context, "Account.db", null, 1);
    }

    /*当数据库第一次创建的时候调用， 这个方法非常适合做表就够的初始化*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),money varchar(20))");
        db.execSQL("insert into info(name,money) values(?,?)",new String[]{"张三","50000"});
        db.execSQL("insert into info(name,money) values(?,?)",new String[]{"李四","30000"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
