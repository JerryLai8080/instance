package com.infinity.jerry.securitysupport.common.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.infinity.jerry.securitysupport.R;
import com.infinity.jerry.securitysupport.coal_security.ui.activity.MainActivity;
import com.infinity.jerry.securitysupport.common.app.CommonApplication;
import com.infinity.jerry.securitysupport.common.base.BaseActivity;
import com.infinity.jerry.securitysupport.common.entity.User;
import com.infinity.jerry.securitysupport.common.test.UserHelper;
import com.infinity.jerry.securitysupport.common.z_utils.z_tools.ZShrPrefencs;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;


/**
 * Created by jerry on 2017/11/13.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.ll_imei)
    LinearLayout llImei;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.rd_anjian)
    RadioButton rdAnjian;
    @BindView(R.id.rd_meijian)
    RadioButton rdMeijian;
    @BindView(R.id.rgbtn)
    RadioGroup rgbtn;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    private TelephonyManager tm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        String[] nameAndPwd = ZShrPrefencs.getInstance().getNameAndPwd();
        if (nameAndPwd.length == 3) {
            edName.setText(nameAndPwd[0]);
            edPwd.setText(nameAndPwd[1]);
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        rgbtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_anjian:
                        CommonApplication.isMeijian = false;
                        break;
                    case R.id.rd_meijian:
                        CommonApplication.isMeijian = true;
                        break;
                }
            }
        });
    }

    @SuppressLint("HardwareIds")
    @OnClick({R.id.ll_imei, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_imei:
                tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                Toasty.info(this, "获取IMEI 号码").show();
                break;
            case R.id.tv_login:
                doLogin();
                break;
        }
    }

    private void doLogin() {
        String userName = edName.getText().toString();
        String pwd = edPwd.getText().toString();

        if(userName.equals("")){
            Toasty.info(this, "用户名不能为空").show();
            return;
        }

        if (pwd.equals("")) {
            Toasty.info(this, "密码不能为空").show();
            return;
        }

        String realName = "";
        List<User> userList = UserHelper.getUserList();
        boolean isLogin = false;
        for (User user : userList) {
            if (userName.equals(user.getUsername()) && pwd.equals(user.getPassword())) {
                isLogin = true;
                realName = user.getName();
                break;
            }
        }
        if (isLogin) {
            ZShrPrefencs.getInstance().setNameAndPwd(userName, pwd, realName);
            Intent intent = new Intent(this, MainActivity.class);
            Toasty.success(this, "欢迎使用煤监执法系统,尊敬的" + realName + "同志").show();
            startActivity(intent);
        } else {
            Toasty.error(this, "用户名或密码错误").show();
        }
    }
}
