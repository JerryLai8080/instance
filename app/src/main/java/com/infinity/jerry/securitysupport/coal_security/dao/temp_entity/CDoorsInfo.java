package com.infinity.jerry.securitysupport.coal_security.dao.temp_entity;

/**
 * Created by jerry on 2018/1/9.
 */

public class CDoorsInfo {

    private int id;
    private String axis_x;
    private String axis_y;
    private String axis_z;
    private int company_code;
    private String door_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAxis_x() {
        return axis_x;
    }

    public void setAxis_x(String axis_x) {
        this.axis_x = axis_x;
    }

    public String getAxis_y() {
        return axis_y;
    }

    public void setAxis_y(String axis_y) {
        this.axis_y = axis_y;
    }

    public String getAxis_z() {
        return axis_z;
    }

    public void setAxis_z(String axis_z) {
        this.axis_z = axis_z;
    }

    public int getCompany_code() {
        return company_code;
    }

    public void setCompany_code(int company_code) {
        this.company_code = company_code;
    }

    public String getDoor_name() {
        return door_name;
    }

    public void setDoor_name(String door_name) {
        this.door_name = door_name;
    }
}
