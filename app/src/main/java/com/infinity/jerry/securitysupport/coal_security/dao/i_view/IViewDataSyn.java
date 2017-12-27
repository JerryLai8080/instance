package com.infinity.jerry.securitysupport.coal_security.dao.i_view;

import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.PlanRecordTemp;

/**
 * Created by jerry on 2017/12/27.
 */

public interface IViewDataSyn {

    void getDataSucc(PlanRecordTemp data);
    void getDataError();

}
