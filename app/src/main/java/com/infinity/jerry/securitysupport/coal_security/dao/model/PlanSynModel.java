package com.infinity.jerry.securitysupport.coal_security.dao.model;

import com.infinity.jerry.securitysupport.coal_security.dao.server.MeiJianServer;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZCommonEntity;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZServiceFactory;

import rx.Observable;

/**
 * Created by jerry on 2018/1/9.
 */

public class PlanSynModel {

    public PlanSynModel() {

    }
    public Observable<ZCommonEntity<PlanRecordTemp>> getAllPlan() {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.PlanServer.class)
                .getAllPlans();
        return observable;
    }


}
