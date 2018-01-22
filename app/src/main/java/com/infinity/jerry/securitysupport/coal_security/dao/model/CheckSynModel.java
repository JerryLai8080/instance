package com.infinity.jerry.securitysupport.coal_security.dao.model;

import com.infinity.jerry.securitysupport.coal_security.dao.server.MeiJianServer;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZCommonEntity;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZServiceFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by jerry on 2018/1/9.
 */

public class CheckSynModel {

    public CheckSynModel() {

    }

    public Observable<ZCommonEntity<Integer>> updatePlan(String map) {
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.PlanServer.class)
                .updatePlan(map);
        return observable;
    }

    public Observable<ZCommonEntity<Object>> updateDoc(int planId, List<File> fileList) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (File file : fileList) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("doc/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        Observable observable = ZServiceFactory.getInstance()
                .createService(MeiJianServer.PlanServer.class)
                .synDocs(parts, planId);
        return observable;
    }

    public Observable<ZCommonEntity<Object>> updateItems(String json){
        return ZServiceFactory.getInstance().createService(MeiJianServer.PlanServer.class).synItems(json);
    }
}
