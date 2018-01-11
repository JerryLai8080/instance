package com.infinity.jerry.securitysupport.coal_security.ui.activity.plans;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.coal_security.dao.i_view.IViewPlanSyn;
import com.infinity.jerry.securitysupport.coal_security.dao.presenter.PlanSynPresenter;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;
import com.infinity.jerry.securitysupport.coal_security.ui.activity.check.CheckNewActivity;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.entity.PlanRecord;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZCommonAdapter;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZViewHolder;
import com.infinity.jerry.securitysupport.common.z_utils.z_callback.CallBack0;
import com.infinity.jerry.securitysupport.common.z_utils.z_tools.ZDialogUtils;
import com.infinity.jerry.securitysupport.common.z_utils.z_tools.ZUtils;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.LoadingDialog;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZTitleBar;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by jerry on 2017/11/13.
 */

public class PlanActivity extends BaseActivity implements IViewPlanSyn {

    @BindView(R.id.titleBar)
    ZTitleBar titleBar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.tvAddPlan)
    TextView tvAddPlan;
    @BindView(R.id.tvNotStart)
    TextView tvNotStart;
    @BindView(R.id.tvDoing)
    TextView tvDoing;
    @BindView(R.id.tvFinished)
    TextView tvFinished;

    private List<PlanRecord> planLists;

    private ZCommonAdapter<PlanRecord> adapter;

    private int viewState = -256;

    private PlanSynPresenter presenter;
    private Dialog dialog;

    public int getLayoutId() {
        return R.layout.activity_plan;
    }

    @Override
    public void initData() {
        dialog = LoadingDialog.loadingDialog(this, "数据正在加载中", false);
        planLists = new ArrayList<>();
        viewState = 0;

    }

    @Override
    public void initPresenter() {
        presenter = PlanSynPresenter.getInstance(this);
    }

    private void getPlanList() {
        dialog.show();
        presenter.getMyPlans();
    }

    @Override
    public void initView() {
        titleBar.setTitle(getString(R.string.my_plan));
        titleBar.setTitleMode(ZTitleBar.Companion.getMODE_TEXT());
        titleBar.setTvPlusText("同步计划");
        titleBar.setTextlistener(new ZTitleBar.OnTextModeListener() {
            @Override
            public void onClickTextMode() {
                getPlanList();
            }
        });
        adapter = new ZCommonAdapter<PlanRecord>(this, planLists, R.layout.item_plan_list) {
            @Override
            public void convert(ZViewHolder holder, PlanRecord item, int position) {
                TextView tvName = holder.getView(R.id.tvPlanName);
                tvName.setText(item.getPlanName() == null || item.getPlanName().equals("") ? getString(R.string.no_plan_name) : item.getPlanName() + "   " + ZUtils.getStrFromPlanType(item.getPlanType()));
                TextView tvCom = holder.getView(R.id.tvType);
                tvCom.setText(item.getCompanyName());
                TextView tvTime = holder.getView(R.id.tvTime);
                tvTime.setText(getString(R.string.start_time) + item.getStartTime() + "  " + getString(R.string.end_time) + (item.getEndTime() == null ? "" : item.getEndTime()));
                TextView tvIsStart = holder.getView(R.id.tvIsStart);
                tvIsStart.setText(item.getIsStart() == 1 && item.getIsFinish() == 1 ? getString(R.string.finished) : item.getIsStart() == 1 ? getString(R.string.doing) : getString(R.string.not_start));

            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final PlanRecord planRecord = planLists.get(position);
                if (planRecord.getIsStart() == 0) {
                    ZDialogUtils.showDialog(PlanActivity.this, getString(R.string.ensure_start_plan), new CallBack0() {
                        @Override
                        public void callBack() {
                            planRecord.setIsStart(1);
                            planRecord.update(planRecord.getId());
                            adapter.notifyDataSetChanged();
                            setTVColor(tvDoing);
                        }
                    });
                } else if (planRecord.getIsStart() != 0 && planRecord.getIsFinish() == 0) {
                    ZDialogUtils.showDialog(PlanActivity.this, "您确定要继续该计划吗?", new CallBack0() {
                        @Override
                        public void callBack() {
                            Intent intent = new Intent(PlanActivity.this, CheckNewActivity.class);
                            PlanRecord item = planLists.get(position);
                            intent.putExtra("planId", item.getId());
                            intent.putExtra("startType", item.getPlanType());//很重要
                            startActivity(intent);
                        }
                    });
                } else if (planRecord.getIsFinish() == 1) {
                    Toasty.success(PlanActivity.this, "获取您完成的计划").show();
                }
                getPlanLists(viewState);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final PlanRecord planRecord = planLists.get(position);
                ZDialogUtils.showDialog(PlanActivity.this, "确定要删除该计划?", new CallBack0() {
                    @Override
                    public void callBack() {
                        planRecord.delete();
                        setTVColor(tvNotStart);
                        getPlanLists(viewState);
                    }
                });
                return true;
            }
        });
        setTVColor(tvNotStart);
        getPlanLists(0);
    }

    private void getPlanLists(int state) {
        String sql = null;
        switch (state) {
            case 0://未开始
                sql = "isStart = 0";
                break;
            case 1://进行中
                sql = "isStart = 1 AND isFinish = 0";
                break;
            case 2://已经完成
                sql = "isFinish = 1";
                break;
        }
        viewState = state;
        List<PlanRecord> planRecords = DataSupport.where(sql).find(PlanRecord.class);
        planLists.clear();
        planLists.addAll(planRecords);
        adapter.notifyDataSetChanged();
    }

    private final int REQUEST_FOR_PLAN = 10;

    @OnClick({R.id.tvNotStart, R.id.tvDoing, R.id.tvFinished, R.id.tvAddPlan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvNotStart:
                setTVColor(tvNotStart);
                getPlanLists(0);
                break;
            case R.id.tvDoing:
                setTVColor(tvDoing);
                getPlanLists(1);
                break;
            case R.id.tvFinished:
                setTVColor(tvFinished);
                getPlanLists(2);
                break;
            case R.id.tvAddPlan:
                Intent intent = new Intent(this, PlanAddActivity.class);
                startActivityForResult(intent, REQUEST_FOR_PLAN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_FOR_PLAN && resultCode == RESULT_OK) {
            getPlanLists(0);
            setTVColor(tvNotStart);
        }
    }

    private void setTVColor(TextView textView) {
        tvNotStart.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        tvDoing.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        tvFinished.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        textView.setTextColor(ContextCompat.getColor(this, R.color.color_main));

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
        getPlanLists(0);
        dialog.dismiss();
    }

    @Override
    public void getDataError() {

    }
}
