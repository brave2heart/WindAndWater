package com.living.service.manager;

import android.content.Context;


import com.living.service.RetrofitHelper;
import com.living.service.RetrofitService;
import com.living.service.entity.Book;

import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public  Observable<Book> getSearchBooks(String name, String type, String key){
        return mRetrofitService.getSearchBooks(name,type,key);
    }
}
