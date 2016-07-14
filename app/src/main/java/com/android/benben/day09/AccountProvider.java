package com.android.benben.day09;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by LiYuanxiong on 2016/7/14 9:24.
 * Desribe:
 */
public class AccountProvider extends ContentProvider {
    private static final int QUERYSUCESS=0;

    /*1.定义一个uri路径匹配器*/
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    /*2.创建一个静态代码块 在这个里面添加uri*/
    static {
        /**
         * authority  注意和清单文件里面定义的一样
         *
         */
        sURIMatcher.addURI("com.benben.provider","query",QUERYSUCESS);
    }

    private MyOpenHelper myOpenHelper;
    /*当内容提供者初始化就会执行该方法*/
    @Override
    public boolean onCreate() {
        /*3.初始化MyOpenHelper对象  就可以获取到sqlitedatabases对象   我们就可以操作数据库*/
         myOpenHelper = new MyOpenHelper(getContext());
        return false;
    }

    /*这个方法对外暴露了*/
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int code = sURIMatcher.match(uri);
        if (code == QUERYSUCESS) {
            /*说明路径匹配成功*/
            SQLiteDatabase db = myOpenHelper.getReadableDatabase();
            /*调用query方法*/
            Cursor cursor = db.query("info", projection, selection, selectionArgs,
                    null, null, sortOrder);
            /*小细节这个cursor不能关*/
            return cursor;
        }else {
            /*路径不匹配*/
            throw new IllegalArgumentException("uri路径不匹配，请检查路径");

        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
