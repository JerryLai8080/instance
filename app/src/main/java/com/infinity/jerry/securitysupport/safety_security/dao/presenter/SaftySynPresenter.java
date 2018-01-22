package com.infinity.jerry.securitysupport.safety_security.dao.presenter;

import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZResultSubscriber;
import com.infinity.jerry.securitysupport.safety_security.dao.i_view.IViewSaftySyn;
import com.infinity.jerry.securitysupport.safety_security.dao.model.SaftySynModel;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCheckCata;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCheckItems;
import com.infinity.jerry.securitysupport.safety_security.entity.SaComCatalog;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCompany;

import org.litepal.crud.DataSupport;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jerry on 2018/1/22.
 */

public class SaftySynPresenter {

    private IViewSaftySyn iViewSaftySyn;
    private SaftySynModel model;


    private SaftySynPresenter(IViewSaftySyn iViewSaftySyn) {
        this.iViewSaftySyn = iViewSaftySyn;
        this.model = new SaftySynModel();
    }

    public static SaftySynPresenter getInstance(IViewSaftySyn iViewSaftySyn) {
        return new SaftySynPresenter(iViewSaftySyn);
    }

    public void getComCatas() {
        model.getSaComCatalogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<SaComCatalog>>() {
                    @Override
                    public void onSuccessZ(List<SaComCatalog> catalogTemps) {
                        DataSupport.deleteAll(SaComCatalog.class);
                        DataSupport.saveAll(catalogTemps);
                        getCompanies();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewSaftySyn.saftySynError();

                    }
                });

    }

    private void getCompanies() {
        model.getSaCompanyies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<SaCompany>>() {
                    @Override
                    public void onSuccessZ(List<SaCompany> saCompanies) {
                        DataSupport.deleteAll(SaCompany.class);
                        DataSupport.saveAll(saCompanies);
                        getCheckCata();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewSaftySyn.saftySynError();
                    }
                });
    }

    private void getCheckCata() {
        model.getSaCheckCata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<SaCheckCata>>() {
                    @Override
                    public void onSuccessZ(List<SaCheckCata> saCheckCatas) {
                        DataSupport.deleteAll(SaCheckCata.class);
                        DataSupport.saveAll(saCheckCatas);
                        getCheckItems();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewSaftySyn.saftySynError();
                    }
                });
    }

    private void getCheckItems() {
        model.getSaCheckItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<SaCheckItems>>() {
                    @Override
                    public void onSuccessZ(List<SaCheckItems> saCheckItems) {
                        DataSupport.deleteAll(SaCheckItems.class);
                        DataSupport.saveAll(saCheckItems);
                        iViewSaftySyn.saftySynSucc();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewSaftySyn.saftySynError();
                    }
                });
    }
}
