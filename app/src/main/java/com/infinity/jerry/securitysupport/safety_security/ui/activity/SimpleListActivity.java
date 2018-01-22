package com.infinity.jerry.securitysupport.safety_security.ui.activity;

import android.widget.ListView;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZTitleBar;

import butterknife.BindView;

/**
 * Created by jerry on 2018/1/22.
 */

public class SimpleListActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    ZTitleBar titleBar;
    @BindView(R.id.listView)
    ListView listView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_list;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        titleBar.setTitle("");

    }

    private void initListView() {

    }


}
