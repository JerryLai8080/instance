package com.infinity.jerry.securitysupport.coal_security.dao.temp_entity;

/**
 * Created by jerry on 2018/1/10.
 */

public class PlanDocTemp {

    private int planId;
    private String doc_type;
    private String doc_name;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_type() {

        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }
}
