package com.infinity.jerry.securitysupport.coal_security.ui.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZTitleBar;

import butterknife.BindView;

/**
 * Created by jerry on 2017/12/26.
 */

public class SynAllDataActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    ZTitleBar titleBar;
    @BindView(R.id.llPlanSyn)
    LinearLayout llPlanSyn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_syn_data;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        titleBar.setTitle("数据同步");
        llPlanSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
