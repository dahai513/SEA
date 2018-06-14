package com.toolbar.mapper;

import com.toolbar.model.Member_schedule;
import com.toolbar.model.Pay_schedule;
import com.toolbar.model.Seller;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorkMapper {

    @Select( "select sellerid,store from seller;" )
    List<Seller> sellerInfoM( );

    @Select( "select * from pay_schedule where sellerid=#{sellerid}" )
    Pay_schedule payInfoM(@Param("sellerid") String sellerid);

    @Select( "select * from member_schedule where sellerid=#{sellerid}" )
    Member_schedule memberInfoM(@Param("sellerid") String sellerid);
}
