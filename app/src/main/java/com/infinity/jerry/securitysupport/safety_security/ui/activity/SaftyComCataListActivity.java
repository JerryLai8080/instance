package com.infinity.jerry.securitysupport.safety_security.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZCommonAdapter;
import com.infinity.jerry.securitysupport.common.z_utils.z_adapter.ZViewHolder;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.LoadingDialog;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZSearchBar;
import com.infinity.jerry.securitysupport.common.z_utils.z_widget.ZTitleBar;
import com.infinity.jerry.securitysupport.safety_security.dao.i_view.IViewSaftySyn;
import com.infinity.jerry.securitysupport.safety_security.dao.presenter.SaftySynPresenter;
import com.infinity.jerry.securitysupport.safety_security.entity.SaComCatalog;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

/**
 * Created by jerry on 2017/11/13.
 */

public class SaftyComCataListActivity extends BaseActivity implements IViewSaftySyn {

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

    private ZCommonAdapter<SaComCatalog> adapter;
    private List<SaComCatalog> companyList;

    private boolean isChoose = false;

    private SaftySynPresenter presenter;
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
        companyList = DataSupport.where("parent_id ISNULL").find(SaComCatalog.class);
        dialog = LoadingDialog.loadingDialog(this, "数据加载中", false);
    }

    @Override
    public void initPresenter() {
        presenter = SaftySynPresenter.getInstance(this);
    }

    private void synCompanyData() {
        dialog.show();
        presenter.getComCatas();
    }

    @Override
    public void initView() {
        titleBar.setTitle("企业分类");
        titleBar.setTitleMode(ZTitleBar.Companion.getMODE_TEXT());
        titleBar.setTvPlusText("同步企业信息");
        titleBar.setOnTextModeListener(new ZTitleBar.OnTextModeListener() {
            @Override
            public void onClickTextMode() {
                synCompanyData();
            }
        });
        searchBar.setSearchHint("请输入关键字");
        searchBar.setOnSearchListener(new ZSearchBar.ZOnSearchListener() {
            @Override
            public void zOnSearch(String string) {
                searchBar.setSearchHint("请输入分类名字");
                companyList.clear();
                companyList = DataSupport.where("name LIKE ?", "%" + string + "%").find(SaComCatalog.class);
                initListView(companyList);
            }
        });

        if (companyList != null) {
            initListView(companyList);
        }
        tvEast.setVisibility(View.GONE);
        tvMiddle.setVisibility(View.GONE);
        tvSouth.setVisibility(View.GONE);
        tvOther.setVisibility(View.GONE);
    }

    private void initListView(final List<SaComCatalog> companyList) {
        adapter = new ZCommonAdapter<SaComCatalog>(this, companyList, R.layout.item_single_text) {
            @Override
            public void convert(ZViewHolder holder, SaComCatalog item, int position) {
                TextView tvName = holder.getView(R.id.tvName);
                tvName.setText(item.getName());
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SaftyComCataListActivity.this, SimpleListActivity.class);
                intent.putExtra("cata_id", companyList.get(position).getId());
                startActivity(intent);
            }
        });
    }


    @Override
    public void saftySynSucc() {
        dialog.dismiss();
    }

    @Override
    public void saftySynError() {
        dialog.dismiss();
    }
}
