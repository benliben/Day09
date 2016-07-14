package com.android.benben.a5;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*1.先查询row_contacts表的contact_id列 查询有多少联系人*/
        Uri uri = Uri.parse("content://com.android.benben.a5/row_contacts");
        Cursor cursor = getContentResolver().query(uri, new String[]{"contact_id"}, null, null, null);
        while (cursor.moveToNext()) {
            String contact_id = cursor.getString(0);
            Log.i("lyx", "onCreate: " + contact_id);

            /**/
        }

    }



}
