package com.android.benben.a2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lyx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*点击按钮增加第一个应用里面的数据*/
    public void click1(View view) {

    }    /*点击按钮删除第一个应用里面的数据*/

    public void click2(View view) {

    }    /*点击按钮修改第一个应用里面的数据*/

    public void click3(View view) {

    }
    /*点击按钮查询第一个应用里面的数据*/
    public void click4(View view) {
//        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.android.benben.a2/databases/Account.db", null, SQLiteDatabase.OPEN_READWRITE);
//        Cursor cursor = db.query("info", null, null, null, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                String name = cursor.getString(1);
//                String money = cursor.getString(cursor.getColumnIndex("money"));
//                Log.i(TAG, "name:"+name+"  ,money:"+money);
//            }
//        }

        /**
         * 第二种查询方式
         * 因为第一个应用里的私有数据库已经通过内容提供者所暴露 所以可以通过内容解析者去获取数据
         */
        Uri uri = Uri.parse("content://com.benben.provider/query");
        /*获取内容解析者*/
        Cursor cursor = getContentResolver().query(uri, new String[]{"name", "money"}
                , null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String money = cursor.getString(1);
            Log.i(TAG, "name:" + name + "  ,money:" + money);
        }
    }
}
