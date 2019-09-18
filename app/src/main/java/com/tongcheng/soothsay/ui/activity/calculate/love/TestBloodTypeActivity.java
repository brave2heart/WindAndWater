package com.tongcheng.soothsay.ui.activity.calculate.love;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.base.BaseTitleActivity;
import com.tongcheng.soothsay.bean.calculation.BloodType;
import com.tongcheng.soothsay.utils.ClickUtil;

import butterknife.BindView;

/**
 * Created by ALing on 2016/12/1 0001.
 * 血型配对结果页
 */

public class TestBloodTypeActivity extends BaseTitleActivity {

    @BindView(R.id.tv_match)
    TextView tvMatch;
    @BindView(R.id.tv_match_desc)
    TextView tvMatchDesc;
    @BindView(R.id.ll_match)
    LinearLayout llMatch;
    @BindView(R.id.tv_pds_index)
    TextView tvPdsIndex;
    @BindView(R.id.rl_pds)
    RelativeLayout rlPds;
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_match_detial)
    TextView tvMatchDetial;
    @BindView(R.id.tv_character)
    TextView tvCharacter;
    @BindView(R.id.tv_instructionTitle)
    TextView tvInstructionTitle;
    @BindView(R.id.tv_instructionContent)
    TextView tvInstructionContent;
    @BindView(R.id.tv_remindTitle)
    TextView tvRemindTitle;
    @BindView(R.id.tv_remindeContent)
    TextView tvRemindeContent;
    private BloodType mbloodType;
    private String matchIndex;
    private String titleContent;
    private String instructionContent;
    private String remindeContent;
    private String boyIndex;
    private String girlIndex;
    private String[] bloodTypeArr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.test_blood_type);
        initListener();
        initData();
        setData();
    }

    @Override
    public void initListener() {
        super.initListener();
        getBaseHeadView().showTitle(getResources().getString(R.string.title_blood_group_matching));
        getBaseHeadView().showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void setData() {
        tvMatchDesc.setText(bloodTypeArr[Integer.valueOf(boyIndex)]+"型男 -- "+bloodTypeArr[Integer.valueOf(girlIndex)]+"型女");
        tvPdsIndex.setText(matchIndex+"");
        tvMatchDetial.setText(matchIndex+"");
        tvCharacter.setText(titleContent);
        tvInstructionContent.setText(instructionContent);
        tvRemindeContent.setText(remindeContent);

    }

    @Override
    public void initData() {
        super.initData();
        bloodTypeArr = getResources().getStringArray(R.array.bloodType_arr);
        mbloodType = (BloodType) getIntent().getSerializableExtra("bean");
        Log.e("tag", "initData: mbloodType==??" + mbloodType);
        boyIndex = mbloodType.getBoyIndex();
        girlIndex = mbloodType.getGirlIndex();
        matchIndex = mbloodType.getIndex();
        remindeContent = mbloodType.getRemindeContent();
        titleContent = mbloodType.getTitleContent();
        instructionContent = mbloodType.getInstructionContent();
    }
}
