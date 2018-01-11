package com.infinity.jerry.securitysupport.coal_security.ui.activity.syn_check;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.coal_security.dao.i_view.IViewCheckSyn;
import com.infinity.jerry.securitysupport.coal_security.dao.presenter.CheckSynPresenter;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanDocTemp;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.entity.PlanRecord;
import com.infinity.jerry.securitysupport.common.entity.PlanToDocEntity;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZCommonAdapter;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZViewHolder;
import com.infinity.jerry.securitysupport.common.z_utils.z_tools.ZGsonUtils;
import com.infinity.jerry.securitysupport.common.z_utils.z_tools.ZUtils;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.LoadingDialog;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZTitleBar;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * Created by jerry on 2017/12/12.
 */

public class CheckSynActivity extends BaseActivity implements IViewCheckSyn {

    @BindView(R.id.titleBar)
    ZTitleBar titleBar;
    @BindView(R.id.listView)
    ListView listView;

    private List<PlanRecord> recordList;
    private ZCommonAdapter<PlanRecord> adapter;

    private Dialog dialog;

    private CheckSynPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_syn_check_1;
    }

    @Override
    public void initData() {
        dialog = LoadingDialog.loadingDialog(this, "数据上传中", false);
        recordList = DataSupport.where("isSyn = 0 AND isFinish = 1").order("id DESC").find(PlanRecord.class);
    }

    @Override
    public void initPresenter() {
        presenter = CheckSynPresenter.getInstance(this);
    }

    @Override
    public void initView() {
        titleBar.setTitle("检查同步");
        titleBar.setTitleMode(ZTitleBar.Companion.getMODE_TEXT());
//        titleBar.setTvPlusText("同步记录");
        titleBar.setOnTextModeListener(new ZTitleBar.OnTextModeListener() {
            @Override
            public void onClickTextMode() {
                Intent intent = new Intent(CheckSynActivity.this, CheckSynHistoryActvitiy.class);
                startActivity(intent);
            }
        });
        adapter = new ZCommonAdapter<PlanRecord>(this, recordList, R.layout.item_syn_check) {
            @Override
            public void convert(ZViewHolder holder, final PlanRecord item, int position) {
                TextView tvCompany = holder.getView(R.id.tvCompany);
                tvCompany.setText(item.getCompanyName());
                TextView tvType = holder.getView(R.id.tvType);
                tvType.setText(ZUtils.getStrFromPlanType(item.getPlanType()));
                TextView tvPlanName = holder.getView(R.id.tvPlanName);
                tvPlanName.setText(item.getPlanName());
                TextView tvTime = holder.getView(R.id.tvTime);
                String endTime = (item.getEndTime() == null ? "" : item.getEndTime());
                tvTime.setText(item.getStartTime() + " 至 " + endTime);
                TextView tvUpdate = holder.getView(R.id.tvUpdate);
                tvUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doUpdateData(item);
                    }
                });
            }
        };
        listView.setAdapter(adapter);

    }

    private int currentId = -256;
    private PlanRecord currentItem = null;

    private void doUpdateData(PlanRecord item) {
        currentItem = item;
        currentId = item.getId();
        PlanRecordTemp.PageListBean entity = new PlanRecordTemp.PageListBean();
        entity.setPlan_name(item.getPlanName());
        entity.setPlan_type(item.getPlanType());
        entity.setCompany_code(String.valueOf(item.getCompanyCode()));
        entity.setIs_start(item.getIsStart());
        entity.setIs_finish(item.getIsFinish());
        entity.setExcute_person_1(item.getExcutePerson1());
        entity.setStart_time(item.getStartTime());
        entity.setEnd_time(item.getEndTime());
        entity.setPlan_area(item.getPlanArea());
        entity.setCompany_name(item.getCompanyName());
        presenter.updatePlan(ZGsonUtils.getInstance().getJsonString(entity));
        dialog.show();

    }

    @Override
    public void updateSucc(Integer planId) {
        List<File> files = new ArrayList<>();
        if (currentId != -256) {
            List<PlanToDocEntity> docList = DataSupport.where("planId = ?", String.valueOf(currentId)).find(PlanToDocEntity.class);
            for (PlanToDocEntity item : docList) {
                File file = new File(item.getDocPath());
                files.add(file);
            }
            PlanDocTemp temp = new PlanDocTemp();
            temp.setPlanId(planId);
            presenter.updateDoc(planId, files);
        }
    }

    @Override
    public void updateDocSucc() {
        Toasty.success(this, "同步成功").show();
        recordList.remove(currentItem);
        currentItem.setIsFinish(1);
        currentItem.setIsSyn(1);
        currentItem.update(currentItem.getId());
        currentId = -256;
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    @Override
    public void updateError() {
        Toasty.error(this, "同步失败").show();
        dialog.dismiss();
    }


}
