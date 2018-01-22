package com.infinity.jerry.securitysupport.safety_security.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by jerry on 2018/1/22.
 */

public class SaCheckItems extends DataSupport {

    private int id;
    private Object origin_id;
    private int type;
    private String name;
    private String requirement;
    private String check_method;
    private String illegal_act;
    private String punish_range;
    private int category_id;
    private String created;
    private String updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(Object origin_id) {
        this.origin_id = origin_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getCheck_method() {
        return check_method;
    }

    public void setCheck_method(String check_method) {
        this.check_method = check_method;
    }

    public String getIllegal_act() {
        return illegal_act;
    }

    public void setIllegal_act(String illegal_act) {
        this.illegal_act = illegal_act;
    }

    public String getPunish_range() {
        return punish_range;
    }

    public void setPunish_range(String punish_range) {
        this.punish_range = punish_range;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
