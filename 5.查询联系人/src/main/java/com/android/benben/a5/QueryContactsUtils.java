package com.android.benben.a5;

import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiYuanxiong on 2016/7/15 14:46.
 * Desribe:查询联系人的工具类
 */
public class QueryContactsUtils {
    public static List<ContactModel> queryContacts(Context context) {
        /*0.创新一个集合*/
        List<ContactModel> contactList = new ArrayList<>();
        /*1.先查询row_contacts表的contact_id列 查询有多少联系人*/
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Uri datauri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(uri, new String[]{"contact_id"}
                , null, null, null);
        while (cursor.moveToNext()) {
            /*创建Java备案对象*/
            String contact_id = cursor.getString(0);
            if (contact_id != null) {
                ContactModel contact = new ContactModel();
                contact.setId(contact_id);
            /*2.根据context_id去查询 data表和mimetype_id表*/
                /**当我们在查询data表的时候 其实我们查询的view_data的视图*/
                Cursor dataCursor = context.getContentResolver().query(datauri,
                        new String[]{"data1", "mimetype"}, "raw_contact_id=?"
                        , new String[]{contact_id}, null);
                while (dataCursor.moveToNext()) {
                    String data1 = dataCursor.getString(0);
                    String mimetype = dataCursor.getString(1);
                /*3.根据mimetype区分data1列的数据类型*/
                    if ("vnd.android.cursor.item/name".equals(mimetype)) {
                        contact.setName(data1);
                    } else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                        contact.setPhone(data1);
                    } else if ("vnd.android.cursor.item/email_v2".equals(mimetype)) {
                        contact.setEmail(data1);
                    }}
                contactList.add(contact);
            }}
        return contactList;
    }


}
