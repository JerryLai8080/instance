package com.infinity.jerry.securitysupport.coal_security.dao.server;

import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZCommonEntity;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jerry on 2017/12/27.
 */

public class MeiJianServer {

    public interface PlanServer {
        @POST("plan/listPlan")
        Observable<ZCommonEntity<PlanRecordTemp>> getAllPlans();
    }
}
