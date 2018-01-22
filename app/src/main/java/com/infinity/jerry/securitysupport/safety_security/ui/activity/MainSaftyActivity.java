package com.infinity.jerry.securitysupport.safety_security.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.coal_security.ui.activity.SynAllDataActivity;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZViewPagerAdapter;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZTitleBar;
import com.infinity.jerry.securitysupport.safety_security.ui.fragment.SaftyLeftFragment;
import com.infinity.jerry.securitysupport.safety_security.ui.fragment.SaftyRightFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainSaftyActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    ZTitleBar titleBar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.imLeft)
    ImageView imLeft;
    @BindView(R.id.imRight)
    ImageView imRight;

    private SaftyLeftFragment leftFragment;
    private SaftyRightFragment rightFragment;

    private List<Fragment> fragList;

    private FragmentManager manager = getSupportFragmentManager();

    @Override
    public int getLayoutId() {
        return R.layout.activity_coal_main;
    }

    @Override
    public void initData() {
        leftFragment = new SaftyLeftFragment();
        rightFragment = new SaftyRightFragment();
        fragList = new ArrayList<>();
        fragList.add(leftFragment);
        fragList.add(rightFragment);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        titleBar.setTitleMode(ZTitleBar.Companion.getMODE_TEXT());
        titleBar.setTitle("安监执法");
        titleBar.noBack();
        titleBar.setTvPlusText("同步/上传");
        titleBar.setOnTextModeListener(new ZTitleBar.OnTextModeListener() {
            @Override
            public void onClickTextMode() {//TestActivity
                Intent intent = new Intent(MainSaftyActivity.this, SynAllDataActivity.class);
                startActivity(intent);
            }
        });

        viewPager.setAdapter(new ZViewPagerAdapter(manager, fragList));
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
