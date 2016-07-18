package com.android.benben.a5;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        /*1.先查询row_contacts表的contact_id列 查询有多少联系人*/
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//
//        Uri uri1 = Uri.parse("content://com.android.contacts/contact_id");
//
//        Uri datauri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//
//        Log.i("lyx", "uri: " + ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
//        Cursor cursor = getContentResolver().query(uri, new String[]{"contact_id"}, null, null, null);
//        Log.i("lyx", "cursor: " + cursor);
//        while (cursor.moveToNext()) {
//            String contact_id = cursor.getString(0);
//            Log.i("lyx", "onCreate: " + contact_id);
//
////            Cursor dataCursor = getContentResolver().query(datauri, null, null, null, null);
////            String[] columnNames = dataCursor.getColumnNames();
////            for (String s :
////                    columnNames) {
////                Log.i("lyx", "s: " + s);
////            }
//            /*2.根据context_id去查询 data表和mimetype_id表*/
//            /**当我们在查询data表的时候 其实我们查询的view_data的视图*/
//            Cursor dataCursor = getContentResolver().query(datauri, new String[]{"data1", "mimetype"},"raw_contact_id=?", new String[]{contact_id}, null);
//            while (dataCursor.moveToNext()) {
//                String data1=dataCursor.getString(0);
//                String mimetype = dataCursor.getString(1);
//                Log.i("lyx", "data1: " + data1+"     mimetype_id"+mimetype);
//                /*3.根据mimetype区分data1列的数据类型*/
//                if ("vnd.android.cursor.item/name".equals(mimetype)) {
//                    Log.i("lyx", "姓名: " + data1);
//                } else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
//                    Log.i("lyx", "电话号码: " + data1);
//                }
//            }
//        }


        List<ContactModel> queryContacts = QueryContactsUtils.queryContacts(getApplicationContext());
        for (ContactModel contact : queryContacts) {
            Log.i("lyx", "onCreate: "+contact);
        }
    }
}
