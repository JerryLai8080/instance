package com.infinity.jerry.securitysupport.coal_security.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.coal_security.dao.i_view.IViewDataSyn;
import com.infinity.jerry.securitysupport.coal_security.dao.presenter.DataSynPresenter;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.entity.PlanRecord;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZTitleBar;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * Created by jerry on 2017/12/26.
 */

public class SynAllDataActivity extends BaseActivity implements IViewDataSyn {

    @BindView(R.id.titleBar)
    ZTitleBar titleBar;
    @BindView(R.id.llPlanSyn)
    LinearLayout llPlanSyn;

    private DataSynPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_syn_data;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        presenter = DataSynPresenter.getInstance(this);
    }

    @Override
    public void initView() {
        titleBar.setTitle("数据同步");
        llPlanSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getMyPlans();
            }
        });
    }

    @Override
    public void getDataSucc(PlanRecordTemp data) {
        Log.e("TAG", "获取plan成功");
        List<PlanRecordTemp.PageListBean> pageList = data.getPageList();
        List<PlanRecord> planList = new ArrayList<>();
        if (pageList != null && pageList.size() > 0) {
            for (PlanRecordTemp.PageListBean item : pageList) {
                PlanRecord planRecord = new PlanRecord();
                planRecord.setCompanyName(item.getCompany_name());
                planRecord.setCompanyCode(Integer.valueOf(item.getCompany_code()));
                planRecord.setIsStart(item.getIs_start());
                planRecord.setIsFinish(item.getIs_finish());
                planRecord.setStartTime(item.getStart_time());
                planRecord.setEndTime(item.getEnd_time());
                planRecord.setExcutePerson1(item.getExcute_person_1());
                planRecord.setPlanName(item.getPlan_name());
                planRecord.setPlanType(item.getPlan_type());
                planList.add(planRecord);
            }
            DataSupport.saveAll(planList);
        }
    }

    @Override
    public void getDataError() {
        Toasty.error(this, "请求失败,网络异常").show();
    }
}
