package com.android.benben.a7;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*1.注册一个内容观察者*/
        Uri uri = Uri.parse("content://com.benben.provider/");
        /**
         * 第二个参数 如果是false 观察的uri 必须是一个确切的uri
         *            如果是true 观察的uri 只需要前缀准确了就行
         */
        getContentResolver().registerContentObserver(uri,true,new MyContentObserver(new Handler()));
    }


    /*定义一个观察者*/
    private class MyContentObserver extends ContentObserver {
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyContentObserver(Handler handler) {
            super(handler);
        }

        /*当被观察的uri发生改变的时候调用*/
        @Override
        public void onChange(boolean selfChange) {
            Log.i("lyx", "我是内容观察者  我发现有人被改变了");
            super.onChange(selfChange);
        }
    }
}
