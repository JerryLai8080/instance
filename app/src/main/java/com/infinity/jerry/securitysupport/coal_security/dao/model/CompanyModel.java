package com.infinity.jerry.securitysupport.coal_security.dao.model;

import com.infinity.jerry.securitysupport.coal_security.dao.server.MeiJianServer;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CBaseInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CDoorsInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CProInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CSaftyInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CSixSysInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CheckItemTemp;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CompanyTemp;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CshaftInfo;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZCommonEntity;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZServiceFactory;

import java.util.List;

import rx.Observable;

/**
 * Created by jerry on 2018/1/9.
 */

public class CompanyModel {

    public Observable<ZCommonEntity<List<CompanyTemp>>> getAllCompanies() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.CompanyServer.class)
                .getAllCompany();
        return observable;
    }

    public Observable<ZCommonEntity<List<CBaseInfo>>> getBaseInfo() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.CompanyServer.class)
                .getBaseInfo();
        return observable;
    }

    public Observable<ZCommonEntity<List<CProInfo>>> getProInfo() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.CompanyServer.class)
                .getProInfo();
        return observable;
    }

    public Observable<ZCommonEntity<List<CSaftyInfo>>> getSaftyInfo() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.CompanyServer.class)
                .getSaftyInfo();
        return observable;
    }

    public Observable<ZCommonEntity<List<CDoorsInfo>>> getDoorInfo() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.CompanyServer.class)
                .getDoorsInfo();
        return observable;
    }

    public Observable<ZCommonEntity<List<CshaftInfo>>> getShaftInfo() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.CompanyServer.class)
                .getShaftInfo();
        return observable;
    }

    public Observable<ZCommonEntity<List<CSixSysInfo>>> getSixSysInfo() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.CompanyServer.class)
                .getSixSysInfo();
        return observable;
    }

    public Observable<ZCommonEntity<List<CheckItemTemp>>> getCheckItems() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.CompanyServer.class)
                .getCheckItems();
        return observable;
    }

}
