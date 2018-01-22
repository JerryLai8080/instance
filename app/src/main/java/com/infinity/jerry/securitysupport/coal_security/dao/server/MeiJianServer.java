package com.infinity.jerry.securitysupport.coal_security.dao.server;

import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CBaseInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CDoorsInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CProInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CSaftyInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CSixSysInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CheckItemTemp;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CompanyTemp;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CshaftInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZCommonEntity;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by jerry on 2017/12/27.
 */

public class MeiJianServer {

    public interface PlanServer {
        @POST("plan/listPlan")
        Observable<ZCommonEntity<PlanRecordTemp>> getAllPlans();

        @FormUrlEncoded
        @POST("plan/synCheck")
        Observable<ZCommonEntity<Integer>> updatePlan(@Field("map") String map);

        @POST("plan/synDocs")
        @Multipart
        Observable<ZCommonEntity<Object>> synDocs(@Part List<MultipartBody.Part> files, @Part("planId") int planId);

        @POST("/plan/updateItems")
        @FormUrlEncoded
        Observable<ZCommonEntity<Object>> synItems(@Field("map") String json);
    }

    public interface CompanyServer {
        @POST("company/listCompany")
        Observable<ZCommonEntity<List<CompanyTemp>>> getAllCompany();

        @GET("company/getOtherInfo2?state=0")
        Observable<ZCommonEntity<List<CBaseInfo>>> getBaseInfo();

        @GET("company/getOtherInfo2?state=1")
        Observable<ZCommonEntity<List<CProInfo>>> getProInfo();

        @GET("company/getOtherInfo2?state=2")
        Observable<ZCommonEntity<List<CSaftyInfo>>> getSaftyInfo();

        @GET("company/getOtherInfo2?state=3")
        Observable<ZCommonEntity<List<CDoorsInfo>>> getDoorsInfo();

        @GET("company/getOtherInfo2?state=4")
        Observable<ZCommonEntity<List<CshaftInfo>>> getShaftInfo();

        @GET("company/getOtherInfo2?state=5")
        Observable<ZCommonEntity<List<CSixSysInfo>>> getSixSysInfo();

        @GET("checkItem/getCoalCheckItems")
        Observable<ZCommonEntity<List<CheckItemTemp>>> getCheckItems();
    }
}
