package com.toolbar.service;

import com.toolbar.mapper.SellerMapper;
import com.toolbar.model.Member_schedule;
import com.toolbar.model.Pay_schedule;
import com.toolbar.model.Seller;
import com.toolbar.model.TissotInfo;
import com.toolbar.util.TimeUtil;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerMapper sellerMapper;

    /*
    总条数
    */
    public int countS() {
        return sellerMapper.countM();
    }

    /*
    商家信息
     */
    @Transactional
    public List<Seller> sellerInfoS(int currentCount, int currentPageCount) {
        List<Seller> seller = sellerMapper.sellerInfoM( currentCount, currentPageCount );

        for (Seller se :seller){
            TissotInfo tissotInfos = sellerMapper.tissotInfoM( se.getSellerid() );
            se.setTissotInfos( tissotInfos );
        }
        return seller;
    }

    /*
    添加商家信息
     */
    @Transactional
    public int sellerAddS(String sellerid, String store, String csa, String csm, String payment, String pay, String member, String brand, String koubei) {
        int p = sellerMapper.payInsertM( sellerid,null );
        int m = sellerMapper.memberInsertM( sellerid,null );
        int t = sellerMapper.tissotM( sellerid );
        int s = sellerMapper.sellerAddM( sellerid, store, csa, csm, payment, pay, member, brand, koubei, TimeUtil.getTime() );
        return p+m+t+s;
    }

    /*
    修改商家信息
     */
    public int sellerUpdateS(String sellerid, String csm, String payment, String pay, String member, String brand, String koubei) {
        return sellerMapper.sellerUpdateM( sellerid, csm, payment, pay, member, brand, koubei );
    }

    /*
    天梭B+间改直
     */

    public Pay_schedule payInfoS(String sellerid) {
        return sellerMapper.payInfoM( sellerid );
    }


    /*
    ajax更改支付链路进度  按钮按下 状态改为1为完成
     */
    public int payInfoUpdateS(String sellerid, int alipay, int data, int sign, int api, int receiving, int deploy, String remark) {
        return sellerMapper.payInfoUpdateM( sellerid, alipay, data, sign, api, receiving, deploy, remark );
    }

    /*
    会员链路
     */

    public Member_schedule memberInfoS(String sellerid) {
        return sellerMapper.memberInfoM( sellerid );
    }


    /*
    ajax更改会员链路进度  按钮按下 状态改为1为完成
     */
    public int memberInfoUpdateS(String sellerid,int m_data,int m_applay,int m_api,int m_receiving,int m_AppAndSpi_online,int m_success_online,String remark) {
        return sellerMapper.memberInfoUpdateM( sellerid, m_data, m_applay, m_api, m_receiving, m_AppAndSpi_online, m_success_online, remark );
    }
    ////////////////////////////
        /*
    获取商家门店商品情况
     */
     public TissotInfo tissotInfoS(String sellerid){
         return sellerMapper.tissotInfoM( sellerid );
     }

     public int tissotUpdateS(String sellerid,int item,int sku,int total_store,int open_store){
         return sellerMapper.tissotUpdateM( sellerid,item,sku,total_store,open_store );
     }

    /*
   Macan
    */

}
