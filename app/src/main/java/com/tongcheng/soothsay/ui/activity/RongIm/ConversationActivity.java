package com.tongcheng.soothsay.ui.activity.RongIm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;

import java.util.Locale;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

/**
 * 融云会话界面
 */
public class ConversationActivity extends BaseTitleActivity {

    private String mTargetId;
    private String mTargetIds;
    private String title;
    private Conversation.ConversationType mConversationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        initView();
    }

    @Override
    public void initView() {
         /* 从 intent 携带的数据里获取 targetId 和会话类型*/
        Intent intent = getIntent();
        mTargetId = intent.getData().getQueryParameter("targetId");
        mTargetIds = intent.getData().getQueryParameter("targetIds");
        title = intent.getData().getQueryParameter("title");
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));

        /* 新建 ConversationFragment 实例，通过 setUri() 设置相关属性*/
        ConversationFragment fragment = new ConversationFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId)
                .appendQueryParameter("targetIds", mTargetIds).build();

        fragment.setUri(uri);

        /* 加载 ConversationFragment */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.conversation, fragment);
        transaction.commit();

        getBaseHeadView().showTitle(title);
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void setActionBarTopPadding(View v) {

    }
}
