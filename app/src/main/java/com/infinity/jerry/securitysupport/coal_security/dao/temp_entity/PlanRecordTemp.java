package com.infinity.jerry.securitysupport.coal_security.dao.temp_entity;

import java.util.List;

/**
 * Created by jerry on 2017/12/27.
 */

public class PlanRecordTemp {

    /**
     * pageList : [{"id":9,"plan_name":"MIDDLE HEALTH","plan_type":1,"company_code":"3440","is_start":null,"is_finish":null,"excute_person_1":"JEERRRY2","start_time":"2017-12-15","end_time":"2017-12-16","plan_area":1,"company_name":"黄矸塘煤矿"},{"id":8,"plan_name":"ESAT ALL","plan_type":0,"company_code":"3354","is_start":null,"is_finish":null,"excute_person_1":"JERRY1","start_time":"2017-12-14","end_time":"2017-12-15","plan_area":0,"company_name":"武隆县茅坪煤矿"},{"id":6,"plan_name":"渝东全面安全价差","plan_type":0,"company_code":"3501","is_start":null,"is_finish":null,"excute_person_1":"Jerry","start_time":"2017-12-13","end_time":"2017-12-14","plan_area":1,"company_name":"重庆市湘渝矿业有限责任公司湘帆煤矿"},{"id":7,"plan_name":"渝中卫生职业检查","plan_type":1,"company_code":"4170","is_start":null,"is_finish":null,"excute_person_1":"Jerry2","start_time":"2017-12-01","end_time":"2017-12-02","plan_area":2,"company_name":"城口县锈水湾煤矿"}]
     * pageBean : {"curPage":1,"pageCount":1,"rowsCount":4,"pageSize":10}
     */

    private PageBeanBean pageBean;
    private List<PageListBean> pageList;

    public PageBeanBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBeanBean pageBean) {
        this.pageBean = pageBean;
    }

    public List<PageListBean> getPageList() {
        return pageList;
    }

    public void setPageList(List<PageListBean> pageList) {
        this.pageList = pageList;
    }

    public static class PageBeanBean {
        /**
         * curPage : 1
         * pageCount : 1
         * rowsCount : 4
         * pageSize : 10
         */

        private int curPage;
        private int pageCount;
        private int rowsCount;
        private int pageSize;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getRowsCount() {
            return rowsCount;
        }

        public void setRowsCount(int rowsCount) {
            this.rowsCount = rowsCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class PageListBean {

        private int id;
        private String plan_name;
        private int plan_type;
        private String company_code;
        private int is_start;
        private int is_finish;
        private String excute_person_1;
        private String start_time;
        private String end_time;
        private int plan_area;
        private String company_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlan_name() {
            return plan_name;
        }

        public void setPlan_name(String plan_name) {
            this.plan_name = plan_name;
        }

        public int getPlan_type() {
            return plan_type;
        }

        public void setPlan_type(int plan_type) {
            this.plan_type = plan_type;
        }

        public String getCompany_code() {
            return company_code;
        }

        public void setCompany_code(String company_code) {
            this.company_code = company_code;
        }

        public int getIs_start() {
            return is_start;
        }

        public void setIs_start(int is_start) {
            this.is_start = is_start;
        }

        public int getIs_finish() {
            return is_finish;
        }

        public void setIs_finish(int is_finish) {
            this.is_finish = is_finish;
        }

        public String getExcute_person_1() {
            return excute_person_1;
        }

        public void setExcute_person_1(String excute_person_1) {
            this.excute_person_1 = excute_person_1;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getPlan_area() {
            return plan_area;
        }

        public void setPlan_area(int plan_area) {
            this.plan_area = plan_area;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
    }
}
