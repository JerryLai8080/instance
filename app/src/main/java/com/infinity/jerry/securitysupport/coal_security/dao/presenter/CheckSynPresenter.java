package com.infinity.jerry.securitysupport.coal_security.dao.presenter;

import com.infinity.jerry.securitysupport.coal_security.dao.i_view.IViewCheckSyn;
import com.infinity.jerry.securitysupport.coal_security.dao.model.CheckSynModel;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZResultSubscriber;

import java.io.File;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jerry on 2018/1/9.
 */

public class CheckSynPresenter {

    private IViewCheckSyn iViewCheckSyn;
    private CheckSynModel model;


    private CheckSynPresenter(IViewCheckSyn iViewCheckSyn) {
        this.iViewCheckSyn = iViewCheckSyn;
        model = new CheckSynModel();
    }

    public static CheckSynPresenter getInstance(IViewCheckSyn iViewCheckSyn) {
        return new CheckSynPresenter(iViewCheckSyn);
    }

    public void updatePlan(String map) {
        model.updatePlan(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<Integer>() {
                    @Override
                    public void onSuccessZ(Integer o) {
                        iViewCheckSyn.updateSucc(o);
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewCheckSyn.updateError();
                    }
                });

    }

    public void updateDoc(int planId, List<File> fileList) {
        model.updateDoc(planId, fileList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<Object>() {
                    @Override
                    public void onSuccessZ(Object o) {
                        iViewCheckSyn.updateDocSucc();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewCheckSyn.updateError();
                    }
                });
    }

    public void updateCheckItems(String json) {
        model.updateItems(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<Object>() {
                    @Override
                    public void onSuccessZ(Object o) {
                        iViewCheckSyn.updateItemsSucc();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewCheckSyn.updateError();
                    }
                });

    }
}
