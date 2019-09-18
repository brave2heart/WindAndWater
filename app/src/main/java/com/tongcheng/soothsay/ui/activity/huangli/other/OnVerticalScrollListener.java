package com.tongcheng.soothsay.ui.activity.huangli.other;

import android.support.v7.widget.RecyclerView;

/**
 * Created by weihao on 2017/12/18.
 */

public abstract class OnVerticalScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop();
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom();
        } else if (dy < 0) {
            onScrolledUp();
        } else if (dy > 0) {
            onScrolledDown();
        }
    }

    private void onScrolledDown() {}

    private void onScrolledUp() {}

    private void onScrolledToBottom() {}

    private void onScrolledToTop() {


    }


}
