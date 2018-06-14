package com.toolbar.controller;

import com.toolbar.model.Member_schedule;
import com.toolbar.model.Pay_schedule;
import com.toolbar.model.Seller;
import com.toolbar.model.TissotInfo;
import com.toolbar.service.SellerService;
import com.toolbar.util.Json;
import com.toolbar.util.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SellerController {
    @Autowired
    private static final Logger log = Logger.getLogger( SellerController.class );
    @Autowired
    private SellerService sellerService;

    @RequestMapping("/test")
 //   @ResponseBody
    public String test(String mobile, HttpServletRequest req){
        String mo = req.getAttribute( "mobile" )+"";
        System.out.println(mobile+"   test"+mo);
        return "test"+mobile;
    }

    @RequestMapping("/sellerInfo")
    public String sellerInfo(Map<String, Object> result, String n) {
        int currentPage;//本页
        int currentCount;//每页起始数
        int currentPageCount = 10;//单页显示条数
        if ("".equals( n ) || n == null || n.equals( "0" )) {
            currentPage = currentCount = 0;
        } else {
            currentCount = Integer.parseInt( n ) * currentPageCount;
            currentPage = Integer.parseInt( n );
        }
        int totalCount = sellerService.countS();
        int totalPageCount = (int) Math.ceil( (double) totalCount / currentPageCount );//总页数
        int currentPage_1 = currentPage + 1;//页面实际页码

        result.put( "totalPageCount", totalPageCount );//总页数
        result.put( "currentPage", currentPage );//当前页
        result.put( "currentPage_1", currentPage_1 );//第currentPage+1页
        log.info( "sellerInfo ###总页数：" + totalPageCount + " ####### 当前第" + currentPage_1 + "页#### 本页条数：" + currentPageCount );
         result.put( "sellers", sellerService.sellerInfoS( currentCount, currentPageCount ) );
        return "seller/sellerInfo";
    }

    @RequestMapping("/sellerAdd")
    public String sellerAdd(Map<String, Object> result, Seller seller, String currentPage) {
        log.info( "sellerAdd ###sellerId：" + seller.getSellerid() + " #####旗舰店：" + seller.getStore() + "#### CSA：" + seller.getCsa() + "#### CSM：" + seller.getCsm() + " ####支付链路：" + seller.getPayment() + " ####支付宝改造：" + seller.getPay() + "####会员链路：" + seller.getMember() + " ####品牌帐号：" + seller.getBrand() + "####口碑：" + seller.getKoubei() );
        int add = sellerService.sellerAddS( seller.getSellerid(), seller.getStore(), seller.getCsa(), seller.getCsm(), seller.getPayment(), seller.getPay(), seller.getMember(), seller.getBrand(), seller.getKoubei() );

        result.put( "addResult", add );
        result.put( "currentPage", currentPage );
        return "seller/agency";
    }


    @RequestMapping("/sellerUpdate")
    public String sellerUpdate(Map<String, Object> result, Seller seller, String currentPage) {
        log.info( "sellerUpdate ###sellerId：" + seller.getSellerid() + " ####支付链路：" + seller.getPayment() + " ####支付宝改造：" + seller.getPay() + "####会员链路：" + seller.getMember() + " ####品牌帐号：" + seller.getBrand() + "####口碑：" + seller.getKoubei() );
        int add = sellerService.sellerUpdateS( seller.getSellerid(), seller.getCsm(), seller.getPayment(),seller.getPay(), seller.getMember(),  seller.getBrand(), seller.getKoubei() );
        result.put( "addResult", add );
        result.put( "currentPage", currentPage );
        return "seller/agency";
    }

    //---------ajax搜索支付链路完成进度
    @RequestMapping("/payInfo")
    @ResponseBody
    public Map<String, Object> payInfo(String sellerid) {
        Map<String, Object> map = new HashMap<String, Object>();
        log.info( "payInfo ###sellerId：" + sellerid );
        map.put( "pay", sellerService.payInfoS( sellerid ));
        return map;
    }

    //---------ajax更改支付链路进度  按钮按下 状态改为1为完成
    @RequestMapping("/payInfoUpdate")
    @ResponseBody
    public Map<String, Integer> payInfoUpdate(Pay_schedule pay) {

        Map<String, Integer> map = new HashMap<String, Integer>();
        log.info( "payInfoUpdate ###sellerId：" + pay.getSellerid()+"###alipay：" + pay.getAlipay()+"###data：" + pay.getData()+"###sign：" + pay.getApi()+"###receiving：" + pay.getReceiving()+"###deploy：" + pay.getDeploy()+"###remark：" + pay.getRemark() );

        Pay_schedule result =  sellerService.payInfoS( pay.getSellerid() );

        //判断数据库是否已经修改  如果为1说明进程已经完成
        if ( result.getAlipay() == 1){ pay.setAlipay(result.getAlipay()); }
        if ( result.getData() == 1){ pay.setData(result.getData()); }
        if ( result.getSign() == 1){ pay.setSign(result.getSign()); }
        if ( result.getApi() == 1){ pay.setApi(result.getApi()); }
        if ( result.getReceiving() == 1){ pay.setReceiving(result.getReceiving( )); }
        if ( result.getDeploy() == 1){ pay.setDeploy(result.getDeploy()); }
        if ( pay.getRemark() != null && pay.getRemark() != ""){ pay.setRemark(pay.getRemark()); } else { pay.setRemark( result.getRemark() ); }
        log.info( "payInfoUpdate_1:"+ TimeUtil.getTime()+" ###sellerId：" + pay.getSellerid()+"###alipay：" + pay.getAlipay()+"###data：" + pay.getData()+"###sign：" + pay.getApi()+"###receiving：" + pay.getReceiving()+"###deploy：" + pay.getDeploy()+"###remark：" + pay.getRemark() );

        map.put( "finish", sellerService.payInfoUpdateS(  pay.getSellerid(), pay.getAlipay(),pay.getData(),pay.getSign() ,pay.getApi(), pay.getReceiving(),pay.getDeploy(),pay.getRemark() ));
        return map;
    }
////////////////---------------------会员------------------
    //---------ajax搜索会员链路完成进度
    @RequestMapping("/memberInfo")
    @ResponseBody
    public Map<String, Object> memberInfo(String sellerid) {
        Map<String, Object> map = new HashMap<String, Object>();
        log.info( "memberInfo() ###sellerId：" + sellerid );
        map.put( "member", sellerService.memberInfoS( sellerid ));
        return map;
    }

    //---------ajax更改会员链路进度  按钮按下 状态改为1为完成
    @RequestMapping("/memberInfoUpdate")
    @ResponseBody
    public Map<String, Integer> memberInfoUpdate(Member_schedule member) {
        System.out.println(member.getRemark()+"-----------");

        Map<String, Integer> map = new HashMap<String, Integer>();
        log.info( "memberInfoUpdate() ###sellerId：" + member.getSellerid()+"###data：" + member.getM_data()+"###app：" + member.getM_applay()+"###api：" + member.getM_api()+"###receiving：" + member.getM_receiving()+"###M_AppAndSpi_online：" + member.getM_AppAndSpi_online()+"###getM_success_online: "+member.getM_success_online()+"###remark：" + member.getRemark() );

        Member_schedule result =  sellerService.memberInfoS( member.getSellerid() );

        //判断数据库是否已经修改  如果为1说明进程已经完成
        if ( result.getM_data() == 1){ member.setM_data(result.getM_data()); }
        if ( result.getM_applay() == 1){ member.setM_applay(result.getM_applay()); }
        if ( result.getM_api() == 1){ member.setM_api(result.getM_api()); }
        if ( result.getM_receiving() == 1){ member.setM_receiving(result.getM_receiving()); }
        if ( result.getM_AppAndSpi_online() == 1){ member.setM_AppAndSpi_online(result.getM_AppAndSpi_online( )); }
        if ( result.getM_success_online() == 1){ member.setM_success_online(result.getM_success_online()); }
        if ( member.getRemark() != null && member.getRemark() != ""){ member.setRemark(member.getRemark()); } else { member.setRemark( result.getRemark() ); }
     //   log.info( "payInfoUpdate_1:"+ TimeUtil.getTime()+" ###sellerId：" + pay.getSellerid()+"###alipay：" + pay.getAlipay()+"###data：" + pay.getData()+"###sign：" + pay.getApi()+"###receiving：" + pay.getReceiving()+"###deploy：" + pay.getDeploy()+"###remark：" + pay.getRemark() );

        map.put( "m_finish", sellerService.memberInfoUpdateS(  member.getSellerid(),member.getM_data(),member.getM_applay(),member.getM_api() ,member.getM_receiving(), member.getM_AppAndSpi_online(),member.getM_success_online(),member.getRemark() ));
        return map;
    }

    //获取进展==
    @RequestMapping("/tissotInfo")
    @ResponseBody
    public Map<String, Object> tissotInfo(Map<String,Object> map_tissot,String sellerid){
        TissotInfo tissotInfo = null;
        try {
              tissotInfo = Json.info( sellerid );
           int tissotUp =sellerService.tissotUpdateS(sellerid,tissotInfo.getItem(),tissotInfo.getSku(),tissotInfo.getTotal_store(  ),tissotInfo.getOpen_store() );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map_tissot.put( "tissot",tissotInfo );
        return map_tissot;
    }
}
