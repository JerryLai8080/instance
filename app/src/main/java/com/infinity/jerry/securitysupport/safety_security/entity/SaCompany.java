package com.infinity.jerry.securitysupport.safety_security.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by jerry on 2018/1/22.
 */

public class SaCompany extends DataSupport {

    private int id;
    private int uid;
    private int category_id;
    private String name;
    private String gongshang_code;
    private String zuzhi_code;
    private String anzhuanzhengshu;
    private String faren;
    private int region_id;
    private String address;
    private String faren_zuoji;
    private String faren_mobile;
    private String anquanfuzeren;
    private String anquanfuzeren_zuoji;
    private String anquanfuzeren_mobile;
    private int up_mianji;
    private int down_mianji;
    private String down_mianji_usage;
    private String relation;
    private String zip_code;
    private int guomingjingji_type;
    private int total_renshu;
    private int safe_renshu;
    private int zhucezijin;
    private int gudingzichan;
    private String chengliriqi;
    private int deleted;
    private String level;
    private String created;
    private String updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGongshang_code() {
        return gongshang_code;
    }

    public void setGongshang_code(String gongshang_code) {
        this.gongshang_code = gongshang_code;
    }

    public String getZuzhi_code() {
        return zuzhi_code;
    }

    public void setZuzhi_code(String zuzhi_code) {
        this.zuzhi_code = zuzhi_code;
    }

    public String getAnzhuanzhengshu() {
        return anzhuanzhengshu;
    }

    public void setAnzhuanzhengshu(String anzhuanzhengshu) {
        this.anzhuanzhengshu = anzhuanzhengshu;
    }

    public String getFaren() {
        return faren;
    }

    public void setFaren(String faren) {
        this.faren = faren;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaren_zuoji() {
        return faren_zuoji;
    }

    public void setFaren_zuoji(String faren_zuoji) {
        this.faren_zuoji = faren_zuoji;
    }

    public String getFaren_mobile() {
        return faren_mobile;
    }

    public void setFaren_mobile(String faren_mobile) {
        this.faren_mobile = faren_mobile;
    }

    public String getAnquanfuzeren() {
        return anquanfuzeren;
    }

    public void setAnquanfuzeren(String anquanfuzeren) {
        this.anquanfuzeren = anquanfuzeren;
    }

    public String getAnquanfuzeren_zuoji() {
        return anquanfuzeren_zuoji;
    }

    public void setAnquanfuzeren_zuoji(String anquanfuzeren_zuoji) {
        this.anquanfuzeren_zuoji = anquanfuzeren_zuoji;
    }

    public String getAnquanfuzeren_mobile() {
        return anquanfuzeren_mobile;
    }

    public void setAnquanfuzeren_mobile(String anquanfuzeren_mobile) {
        this.anquanfuzeren_mobile = anquanfuzeren_mobile;
    }

    public int getUp_mianji() {
        return up_mianji;
    }

    public void setUp_mianji(int up_mianji) {
        this.up_mianji = up_mianji;
    }

    public int getDown_mianji() {
        return down_mianji;
    }

    public void setDown_mianji(int down_mianji) {
        this.down_mianji = down_mianji;
    }

    public String getDown_mianji_usage() {
        return down_mianji_usage;
    }

    public void setDown_mianji_usage(String down_mianji_usage) {
        this.down_mianji_usage = down_mianji_usage;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public int getGuomingjingji_type() {
        return guomingjingji_type;
    }

    public void setGuomingjingji_type(int guomingjingji_type) {
        this.guomingjingji_type = guomingjingji_type;
    }

    public int getTotal_renshu() {
        return total_renshu;
    }

    public void setTotal_renshu(int total_renshu) {
        this.total_renshu = total_renshu;
    }

    public int getSafe_renshu() {
        return safe_renshu;
    }

    public void setSafe_renshu(int safe_renshu) {
        this.safe_renshu = safe_renshu;
    }

    public int getZhucezijin() {
        return zhucezijin;
    }

    public void setZhucezijin(int zhucezijin) {
        this.zhucezijin = zhucezijin;
    }

    public int getGudingzichan() {
        return gudingzichan;
    }

    public void setGudingzichan(int gudingzichan) {
        this.gudingzichan = gudingzichan;
    }

    public String getChengliriqi() {
        return chengliriqi;
    }

    public void setChengliriqi(String chengliriqi) {
        this.chengliriqi = chengliriqi;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
