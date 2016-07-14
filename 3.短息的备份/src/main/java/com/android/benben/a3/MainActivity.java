package com.android.benben.a3;

import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /*点击按钮查询短信内容 然后把短信内容进行备份*/
    public void click(View view) {
        try {
            /*一.获取XmlSerializer的实例*/
            XmlSerializer serializer = Xml.newSerializer();
            /*二。设置序列化器参数*/
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "smsbackup.xml");
            FileOutputStream fos = new FileOutputStream(file);
            serializer.setOutput(fos, "utf-8");
            /*三.写xml文档开头*/
            serializer.startDocument("utf-8", true);
            /*四.写xml的根节点*/
            serializer.startTag(null, "smss");

            /*五.构造uri*/
            Uri uri = Uri.parse("content://sms/");
            /*六.由于短息的数据库已经由内容提供者暴露出来，所以我们直接通过内容解析者查询即可*/
            Cursor cursor = getContentResolver().query(uri, new String[]{"address", "date", "body"}
                    , null, null, null);
            while (cursor.moveToNext()) {
                String address = cursor.getString(0);
                String date = cursor.getString(1);
                String body = cursor.getString(2);

                /*七.写sms节点*/
                serializer.startTag(null, "sms");
                /*八.写address节点*/
                serializer.startTag(null, "address");
                serializer.text(address);
                serializer.endTag(null, "address");
                /*九.写date节点*/
                serializer.startTag(null, "date");
                serializer.text(date);
                serializer.endTag(null, "date");
                /*九.写body节点*/
                serializer.startTag(null, "body");
                serializer.text(body);
                serializer.endTag(null, "body");
                serializer.endTag(null, "sms");
            }



            serializer.endTag(null, "smss");
            serializer.endDocument();
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
