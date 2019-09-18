package com.living.service.view;


import com.living.service.entity.Book;

/**
 * Created by win764-1 on 2016/12/12.
 */

public interface BookView extends View {
    void onSuccess(Book mBook);
    void onError(String result);
}
