package com.toolbar.model;

import org.springframework.stereotype.Component;

//商家xx
@Component

public class TissotInfo {
    private String sellerid;
    private int item;  // 商品数
    private int sku;    //sku数
    private int total_store;    //总门店
    private int open_store; //开启门店

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getTotal_store() {
        return total_store;
    }

    public void setTotal_store(int total_store) {
        this.total_store = total_store;
    }

    public int getOpen_store() {
        return open_store;
    }

    public void setOpen_store(int open_store) {
        this.open_store = open_store;
    }
}
