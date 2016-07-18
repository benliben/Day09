package com.android.benben.a6;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.et_name)
    EditText mName;
    @InjectView(R.id.et_phone)
    EditText mPhone;
    @InjectView(R.id.et_emil)
    EditText mEmil;


    private String name,phone,emil;

    private int data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    public void insert(View view) {
        /*获取输入框的内容*/
        initData();
        /*把内容插入到数据中*/
        insertData();
    }

    private void initData() {
        name = mName.getText().toString().trim();
        phone = mPhone.getText().toString().trim();
        emil = mEmil.getText().toString().intern();
    }





    private void insertData() {
        /*1.先获取在添加之前数据库中有多少条数据*/
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        int count = cursor.getCount();
        int contact_id = count + 1;
        Log.i("lyx", "数量为: "+contact_id+"name:"+name+"phone"+phone+"email"+emil);

        /*2.先往row_contacts表中插入数据联系人的id contact_id*/
        ContentValues values = new ContentValues();
        values.put("contact_id",contact_id);
        getContentResolver().insert(uri, values);

        /*3.把name数据往data表中插入 data1表*/
        ContentValues nameValues = new ContentValues();
        nameValues.put("data1", name);
        /**插入数据的时候要告诉数据库属于第几个联系人和数据类型*/
        nameValues.put("raw_contact_id", contact_id);
        nameValues.put("mimetype", "vnd.android.cursor.item/name");
        getContentResolver().insert(dataUri, nameValues);

        /*4.把phone数据往data表中插入 data1表*/
        ContentValues phoneValues = new ContentValues();
        phoneValues.put("data1", phone);
        phoneValues.put("raw_contact_id", contact_id);
        phoneValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
        getContentResolver().insert(dataUri, phoneValues);

        /*5.把email数据往data表中插入 data1表*/
        ContentValues emailValues = new ContentValues();
        emailValues.put("data1", emil);
        emailValues.put("raw_contact_id", contact_id);
        emailValues.put("mimetype", "vnd.android.cursor.item/email_v2");
        getContentResolver().insert(dataUri, emailValues);

        Log.i("lyx", "插入完成: ");
    }
}
