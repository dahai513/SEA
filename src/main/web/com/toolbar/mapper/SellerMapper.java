package com.toolbar.mapper;

import com.toolbar.model.Member_schedule;
import com.toolbar.model.Pay_schedule;
import com.toolbar.model.Seller;
import com.toolbar.model.TissotInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SellerMapper {

    /*
    总条数
     */
    @Select("select count(sellerid) from seller;")
    int countM();

    /*
    商家信息
    @Param currentCount     每页起始条数
    @Param currentPageCount 每页展示条数
     */
    @Select( "SELECT * from seller s,pay_schedule p,member_schedule m\n" +
            "WHERE\n" +
            "\ts.sellerid=p.sellerid AND p.sellerid=m.sellerid\n" +
//            "ORDER BY\n" +
//            "\t(p.alipay+p.api+p.`data`+p.deploy+p.receiving+p.sign+m.m_data+m.m_applay+m.m_api+m.m_receiving+m.m_AppAndSpi_online+m.m_success_online)
            "limit #{currentCount},#{currentPageCount};" )
    List<Seller> sellerInfoM(@Param( "currentCount" ) int currentCount, @Param( "currentPageCount" ) int currentPageCount );



    /*
    添加商家信息
     */
    @Insert( "insert into seller values(#{sellerid},#{store},#{csa},#{csm},#{payment},#{pay},#{member},#{brand},#{koubei},#{createTime});")
    int sellerAddM(@Param("sellerid") String sellerid, @Param("store") String store,@Param("csa") String csa,@Param("csm") String csm,@Param("payment")String payment,@Param("pay")String pay,@Param("member")String member,@Param("brand") String brand,@Param("koubei")String koubei,@Param( "createTime" )String createTime );

    @Insert("insert into tissotInfo values(#{sellerid},0,0,0,0)")
    int tissotM(@Param("sellerid") String sellerid);
    /*
    修改商家信息
     */
    @Update( "update seller set csm=#{csm}, payment=#{payment},pay=#{pay},member=#{member},brand=#{brand},koubei=#{koubei} where sellerid=#{sellerid}" )
    int sellerUpdateM(@Param("sellerid") String sellerid,@Param("csm") String csm,@Param("payment")String payment,@Param("pay")String pay,@Param("member")String member,@Param("brand") String brand,@Param("koubei")String koubei);

    /*
    天梭B+间改直
     */
    @Insert( "insert into pay_schedule values(#{sellerid},0,0,0,0,0,0,#{remark})" )
    int payInsertM(@Param("sellerid") String sellerid,@Param("remark") String remark);

    /*
    ajax搜索支付链路完成进度
     */
    @Select( "select * from pay_schedule where sellerid=#{sellerid}" )
    Pay_schedule payInfoM(@Param("sellerid") String sellerid);

    /*
    ajax更改支付链路进度  按钮按下 状态改为1为完成
     */
    @Update( "update pay_schedule set alipay=#{alipay},data=#{data},sign=#{sign},api=#{api},receiving=#{receiving},deploy=#{deploy},remark=#{remark} where sellerid=#{sellerid}" )
    int payInfoUpdateM(@Param("sellerid")String sellerid,@Param("alipay")int alipay,@Param("data")int data,@Param("sign")int sign,@Param("api")int api,@Param("receiving")int receiving,@Param("deploy")int deploy,@Param("remark")String remark);

    /*
    会员链路
     */
    @Insert( "insert into member_schedule values(#{sellerid},0,0,0,0,0,0,#{remark})" )
    int memberInsertM(@Param("sellerid") String sellerid,@Param("remark") String remark);

    /*
    ajax搜索会员链路完成进度
     */
    @Select( "select * from member_schedule where sellerid=#{sellerid}" )
    Member_schedule memberInfoM(@Param("sellerid") String sellerid);

    /*
    ajax更改会员链路进度  按钮按下 状态改为1为完成
     */
    @Update( "update member_schedule set m_data=#{m_data},m_applay=#{m_applay},m_api=#{m_api},m_receiving=#{m_receiving},m_AppAndSpi_online=#{m_AppAndSpi_online},m_success_online=#{m_success_online},remark=#{remark} where sellerid=#{sellerid}" )
    int memberInfoUpdateM(@Param("sellerid")String sellerid,@Param("m_data")int m_data,@Param("m_applay")int m_applay,@Param("m_api")int m_api,@Param("m_receiving")int m_receiving,@Param("m_AppAndSpi_online")int m_AppAndSpi_online,@Param("m_success_online")int m_success_online,@Param("remark")String remark);

    /*
    获取商家门店商品情况
     */
    @Select( "select * from tissotInfo where sellerid=#{sellerid}" )
    TissotInfo tissotInfoM(@Param( "sellerid" ) String sellerid);

    @Update( "update set tissotInfo item=#{item},sku=#{sku},total_store=#{total_store},open_store=#{open_store} where sellerid=#{sellerid}" )
    int tissotUpdateM(@Param( "sellerid" ) String sellerid,@Param( "item" )int item,@Param( "sku" )int sku,@Param( "total_store" )int total_store,@Param( "open_store" )int open_store);

    /*
        Macan
         */
    @Insert( "insert into tissotinfo values(#{sellerid},0,0,0,0)" )
    int tissotInsertM(@Param( "sellerid" ) String sellerid);
}
