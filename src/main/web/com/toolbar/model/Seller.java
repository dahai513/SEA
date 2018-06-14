package com.toolbar.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*
Created in 2018/03/09 by sea
旗舰店信息
 */
@Component
public class Seller {
    private String sellerid;
    private String store;
    private static final String csa="溪行";
    private String csm;
    private String payment;
    private String pay;
    private String member;
    private String brand;
    private String koubei;
    private String createTime;
    private int success;
    //-----------------------------------------------------

     private TissotInfo tissotInfos;
//---------------------------------------------------


    public TissotInfo getTissotInfos() {
        return tissotInfos;
    }

    public void setTissotInfos(TissotInfo tissotInfos) {
        this.tissotInfos = tissotInfos;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCsa() {
        return csa;
    }

    public String getCsm() {
        return csm;
    }

    public void setCsm(String csm) {
        this.csm = csm;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getKoubei() {
        return koubei;
    }

    public void setKoubei(String koubei) {
        this.koubei = koubei;
    }
}
