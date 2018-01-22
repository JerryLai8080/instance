package com.infinity.jerry.securitysupport.safety_security.dao.server;

import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZCommonEntity;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCheckCata;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCheckItems;
import com.infinity.jerry.securitysupport.safety_security.entity.SaComCatalog;
import com.infinity.jerry.securitysupport.safety_security.entity.SaCompany;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jerry on 2018/1/22.
 */

public class AnJianServer {

    public interface AnJianSynServer {
        @GET("/company/getSaftyComCatalog")
        Observable<ZCommonEntity<List<SaComCatalog>>> getComCatalog();

        @GET("/company/getSaftyCompany")
        Observable<ZCommonEntity<List<SaCompany>>> getCompanyies();

        @GET("/checkItem/getSaftyCatalog")
        Observable<ZCommonEntity<List<SaCheckCata>>> getCheckCata();

        @GET("checkItem/getSaftyItems")
        Observable<ZCommonEntity<List<SaCheckItems>>> getSaCheckItems();
    }
}
