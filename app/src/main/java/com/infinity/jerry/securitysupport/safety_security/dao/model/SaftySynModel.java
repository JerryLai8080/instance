package com.infinity.jerry.securitysupport.safety_security.dao.model;

import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZCommonEntity;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZServiceFactory;
import com.infinity.jerry.securitysupport.safety_security.dao.server.AnJianServer;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCheckCata;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCheckItems;
import com.infinity.jerry.securitysupport.safety_security.entity.SaComCatalog;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCompany;

import java.util.List;

import rx.Observable;

/**
 * Created by jerry on 2018/1/22.
 */

public class SaftySynModel {

    public SaftySynModel() {

    }

    public Observable<ZCommonEntity<List<SaComCatalog>>> getSaComCatalogs() {
        return ZServiceFactory.getInstance().createService(AnJianServer.AnJianSynServer.class).getComCatalog();
    }


    public Observable<ZCommonEntity<List<SaCompany>>> getSaCompanyies() {
        return ZServiceFactory.getInstance().createService(AnJianServer.AnJianSynServer.class).getCompanyies();

    }

    public Observable<ZCommonEntity<List<SaCheckCata>>> getSaCheckCata() {
        return ZServiceFactory.getInstance().createService(AnJianServer.AnJianSynServer.class).getCheckCata();

    }

    public Observable<ZCommonEntity<List<SaCheckItems>>> getSaCheckItems() {
        return ZServiceFactory.getInstance().createService(AnJianServer.AnJianSynServer.class).getSaCheckItems();
    }
}
