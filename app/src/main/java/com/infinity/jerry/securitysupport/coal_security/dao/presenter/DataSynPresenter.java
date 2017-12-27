package com.infinity.jerry.securitysupport.coal_security.dao.presenter;

import com.infinity.jerry.securitysupport.coal_security.dao.i_view.IViewDataSyn;
import com.infinity.jerry.securitysupport.coal_security.dao.model.DataSynModel;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZResultSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jerry on 2017/12/27.
 */

public class DataSynPresenter {

    private IViewDataSyn iViewDataSyn;
    private DataSynModel model;

    private DataSynPresenter(IViewDataSyn iViewDataSyn) {
        this.iViewDataSyn = iViewDataSyn;
        model = new DataSynModel();
    }

    public static DataSynPresenter getInstance(IViewDataSyn iViewDataSyn) {
        return new DataSynPresenter(iViewDataSyn);
    }

    public void getMyPlans() {
        model.getAllPlan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<PlanRecordTemp>() {
                    @Override
                    public void onSuccessZ(PlanRecordTemp planRecordTemps) {
                        iViewDataSyn.getDataSucc(planRecordTemps);
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewDataSyn.getDataError();
                    }
                });
    }
}
