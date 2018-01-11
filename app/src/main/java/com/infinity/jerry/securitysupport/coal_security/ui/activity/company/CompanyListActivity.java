package com.infinity.jerry.securitysupport.coal_security.ui.activity.company;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.coal_security.constant.CoalConstant;
import com.infinity.jerry.securitysupport.coal_security.dao.i_view.IViewCompany;
import com.infinity.jerry.securitysupport.coal_security.dao.presenter.CompanyPresenter;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CompanyTemp;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.entity.CompanyCoalEntity;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZCommonAdapter;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZViewHolder;
import com.infinity.jerry.securitysupport.common.z_utils.z_tools.ZUtils;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.LoadingDialog;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZSearchBar;
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

public class CompanyListActivity extends BaseActivity implements IViewCompany {

    @BindView(R.id.titleBar)
    ZTitleBar titleBar;
    @BindView(R.id.searchBar)
    ZSearchBar searchBar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.tvEast)
    TextView tvEast;
    @BindView(R.id.tvMiddle)
    TextView tvMiddle;
    @BindView(R.id.tvSouth)
    TextView tvSouth;
    @BindView(R.id.tvOther)
    TextView tvOther;

    private ZCommonAdapter<CompanyCoalEntity> adapter;
    private List<CompanyCoalEntity> companyList;

    private boolean isChoose = false;

    private CompanyPresenter presenter;
    private Dialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_company_main;
    }

    @Override
    public void initData() {
        int sth = getIntent().getIntExtra("randomPlan", -256);
        if (sth != -256) {
            isChoose = true;
        }
        companyList = DataSupport.findAll(CompanyCoalEntity.class);
        dialog = LoadingDialog.loadingDialog(this, "数据加载中", false);
    }

    @Override
    public void initPresenter() {
        presenter = CompanyPresenter.getInstance(this);
    }

    private void synCompanyData() {
        dialog.show();
        presenter.getCompanies();
    }

    @Override
    public void initView() {
        titleBar.setTitle(getString(R.string.coal_company));
        titleBar.setTitleMode(ZTitleBar.Companion.getMODE_TEXT());
        titleBar.setTvPlusText("同步企业信息");
        titleBar.setOnTextModeListener(new ZTitleBar.OnTextModeListener() {
            @Override
            public void onClickTextMode() {
                synCompanyData();
            }
        });
        searchBar.setSearchHint(getString(R.string.enter_company_name));
        searchBar.setOnSearchListener(new ZSearchBar.ZOnSearchListener() {
            @Override
            public void zOnSearch(String string) {
                searchBar.setSearchHint("请输入公司名字");
                setTvTextColor(null);
                companyList.clear();
                companyList = DataSupport.where("companyName LIKE ?", "%" + string + "%").find(CompanyCoalEntity.class);
                initListView(companyList);
            }
        });

        if (companyList != null) {
            initListView(companyList);
        }
    }

    private void initListView(final List<CompanyCoalEntity> companyList) {
        adapter = new ZCommonAdapter<CompanyCoalEntity>(this, companyList, R.layout.item_single_text) {
            @Override
            public void convert(ZViewHolder holder, CompanyCoalEntity item, int position) {
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText(item.getCompanyName());
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CompanyCoalEntity com = companyList.get(position);
                if (isChoose) {
                    Intent intent = new Intent();
                    intent.putExtra("companyCode", com.getCompanyCode());
                    intent.putExtra("companyName", com.getCompanyName());
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    int companyCode = com.getCompanyCode();
                    Intent intent = new Intent(CompanyListActivity.this, CompanyDetialActivity.class);
                    intent.putExtra("companyCode", companyCode);
                    startActivity(intent);
                }

            }
        });
    }

    @OnClick({R.id.tvEast, R.id.tvMiddle, R.id.tvSouth, R.id.tvOther})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvEast:
                setTvTextColor(tvEast);
                searchArea(CoalConstant.AREA_EAST);
                break;
            case R.id.tvMiddle:
                setTvTextColor(tvMiddle);
                searchArea(CoalConstant.AREA_MIDDLE);
                break;
            case R.id.tvSouth:
                setTvTextColor(tvSouth);
                searchArea(CoalConstant.AREA_SOUTH);
                break;
            case R.id.tvOther:
                setTvTextColor(tvOther);
                searchArea(CoalConstant.AREA_QIANJIANG);
                break;
        }
    }

    private void searchArea(int type) {
        companyList.clear();
        searchBar.setSearchHint("筛选: " + ZUtils.getAreaByType(type));
        companyList = DataSupport.where("companyLocation = ?", ZUtils.getAreaByType(type)).find(CompanyCoalEntity.class);
        initListView(companyList);
    }

    private void setTvTextColor(TextView tvChoosed) {
        tvEast.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        tvMiddle.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        tvSouth.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        tvOther.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        if (tvChoosed != null) {
            tvChoosed.setTextColor(ContextCompat.getColor(this, R.color.appMainColor));
        }
    }

    @Override
    public void getCompanySucc(List<CompanyTemp> dataList) {
        List<CompanyCoalEntity> companyCoalEntities = new ArrayList<>();
        for (CompanyTemp item : dataList) {
            CompanyCoalEntity entity = new CompanyCoalEntity();
            entity.setCompanyCode(item.getCompany_code());
            entity.setCompanyName(item.getCompany_name());
            entity.setCompanyLocation(item.getCompany_location());
            entity.setCompanyArea(item.getCompany_area());
            entity.setCompanyState(item.getCompany_state());
            entity.setDirector(item.getDirector());
            entity.setLeaglPerson(item.getLeagl_person());
            entity.setCoalPhone(item.getCoal_phone());
            entity.setCoalCell(item.getCoal_cell());
            entity.setCoalUrl(item.getCoal_url());
            companyCoalEntities.add(entity);
        }
        DataSupport.deleteAll(CompanyCoalEntity.class);
        DataSupport.saveAll(companyCoalEntities);
        dialog.dismiss();
        companyList = DataSupport.findAll(CompanyCoalEntity.class);
        initListView(companyList);
    }

    @Override
    public void getCompanyError() {
        Toasty.error(this, "获取公司信息失败").show();
    }
}
