package com.tongcheng.soothsay.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tongcheng.soothsay.MyApplicationLike;
import com.tongcheng.soothsay.bean.dao.DaoMaster;
import com.tongcheng.soothsay.bean.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * 数据库帮助类
 * Created by Steven on 16/11/4.
 */

public class GreenDaoHelper {

    public static final String DB_NAME = "smds";

    private static GreenDaoHelper  mHelper;

    private DaoSession session;

    private GreenDaoHelper (){
        MyDBHelper helper = new MyDBHelper(MyApplicationLike.getInstance().getApplicationContext(),DB_NAME);
        Database db = helper.getWritableDb();
        session = new DaoMaster(db).newSession();
    }


    public static GreenDaoHelper getInstance(){
        if(mHelper == null){
            synchronized (GreenDaoHelper.class){
                if(mHelper == null){
                    mHelper = new GreenDaoHelper();
                }
            }
        }

        return mHelper;
    }

    public DaoSession getSeeion(){
        return session;
    }


    //数据库升级所用的dbHelper类 用来替换 DevOpenHelper
    public static class MyDBHelper extends DaoMaster.OpenHelper {

        public MyDBHelper(Context context, String name) {
            super(context, name);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onUpgrade(db, oldVersion, newVersion);



        }
    }



}
