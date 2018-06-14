package com.toolbar.model;

public class Pay_schedule {
    private String sellerid;
    private int alipay;//支付宝帐号申请情况
    private int data;//主档数据初始化
    private int sign;//签约
    private int api;//接口开发
    private int receiving;//case验收
    private int deploy;//门店部署
    private String remark;//备注

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public int getAlipay() {
        return alipay;
    }

    public void setAlipay(int alipay) {
        this.alipay = alipay;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    public int getReceiving() {
        return receiving;
    }

    public void setReceiving(int receiving) {
        this.receiving = receiving;
    }

    public int getDeploy() {
        return deploy;
    }

    public void setDeploy(int deploy) {
        this.deploy = deploy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
