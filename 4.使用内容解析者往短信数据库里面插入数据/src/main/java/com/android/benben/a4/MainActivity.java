package com.android.benben.a4;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*点击按钮 往短信数据库里面插入一条数据*/
    public void click(View view) {
        /*1.由于短信的数据库已经通过内容提供者暴露了出来 所以可以直接通过内容的解析者操作*/
        Uri uri = Uri.parse("content://sms/");
        /*2.创建ContentValues*/
        ContentValues values = new ContentValues();
        values.put("address", "95555");
        values.put("body", "你的银行卡余额为100000000元");
        values.put("date", System.currentTimeMillis());
         getContentResolver().insert(uri, values);
    }
}
