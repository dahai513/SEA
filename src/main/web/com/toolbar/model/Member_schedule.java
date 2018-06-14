package com.toolbar.model;

public class Member_schedule {
    private String sellerid;
    private int m_data;//确定cem数据处理方案
    private int m_applay;//isv(新零售) 品牌(商家品牌会员)
    private int m_api;//会员通开发
    private int m_receiving;//接口测试通过
    private int m_AppAndSpi_online;//自研(上线)-ivs(发布服务市场)-spi发布上线
    private int m_success_online;//测试平台通过后发布上线
    private String remark;//备注
    private int m_success;

    public int getM_success() {
        return m_success;
    }

    public void setM_success(int m_success) {
        this.m_success = m_success;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public int getM_data() {
        return m_data;
    }

    public void setM_data(int m_data) {
        this.m_data = m_data;
    }

    public int getM_applay() {
        return m_applay;
    }

    public void setM_applay(int m_applay) {
        this.m_applay = m_applay;
    }

    public int getM_api() {
        return m_api;
    }

    public void setM_api(int m_api) {
        this.m_api = m_api;
    }

    public int getM_receiving() {
        return m_receiving;
    }

    public void setM_receiving(int m_receiving) {
        this.m_receiving = m_receiving;
    }

    public int getM_AppAndSpi_online() {
        return m_AppAndSpi_online;
    }

    public void setM_AppAndSpi_online(int m_AppAndSpi_online) {
        this.m_AppAndSpi_online = m_AppAndSpi_online;
    }

    public int getM_success_online() {
        return m_success_online;
    }

    public void setM_success_online(int m_success_online) {
        this.m_success_online = m_success_online;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
