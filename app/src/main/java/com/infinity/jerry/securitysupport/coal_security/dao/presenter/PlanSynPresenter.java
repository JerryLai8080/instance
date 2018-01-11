package com.infinity.jerry.securitysupport.coal_security.dao.presenter;

import com.infinity.jerry.securitysupport.coal_security.dao.i_view.IViewPlanSyn;
import com.infinity.jerry.securitysupport.coal_security.dao.model.PlanSynModel;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;
import com.infinity.jerry.securitysupport.common.z_utils.z_callback.CallBack0;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZResultSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jerry on 2018/1/9.
 */

public class PlanSynPresenter {

    private IViewPlanSyn iViewPlanSyn;
    private PlanSynModel model;

    private PlanSynPresenter(IViewPlanSyn iViewPlanSyn) {

        this.iViewPlanSyn = iViewPlanSyn;
        this.model = new PlanSynModel();
    }

    public static PlanSynPresenter getInstance(IViewPlanSyn iViewPlanSyn) {
        return new PlanSynPresenter(iViewPlanSyn);
    }


    public void getMyPlans() {
        model.getAllPlan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<PlanRecordTemp>() {
                    @Override
                    public void onSuccessZ(PlanRecordTemp planRecordTemps) {
                        iViewPlanSyn.getDataSucc(planRecordTemps);
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewPlanSyn.getDataError();
                    }
                });
    }


}
