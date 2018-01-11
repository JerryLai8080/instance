package com.infinity.jerry.securitysupport.coal_security.dao.i_view;

import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CompanyTemp;

import java.util.List;

/**
 * Created by jerry on 2018/1/9.
 */

public interface IViewCompany {

    void getCompanySucc(List<CompanyTemp> dataList);

    void getCompanyError();
}
