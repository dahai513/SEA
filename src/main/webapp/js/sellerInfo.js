
	/*  进度条控制
	@Param class_number     第几个class样式
 	@Param now 	            完成条数
	@Param total            目标条数
	*/
function shedule(obj,now,total){
	var shedule = obj.getElementsByClassName("shedule")[0];
	var percent = obj.getElementsByClassName("percent")[0];
	var num = Math.floor( (now/total)*10000)/100;
 	shedule.style.width = num+"px";
	percent.innerHTML = num+"%";
}


////进度条隐藏
//function shedule_hide(obj){
//  	obj.style.display="none";
//	obj.previousElementSibling.style.display="block";
//}


    //定义栏背景
function trbgc_show(obj){
    obj.style.backgroundColor="#93db70"
  }
function trbgc_hide(obj){
    obj.style.backgroundColor="white"
}


  //支付发送数据请求
  function sendPayAjax(obj,sellerid,store_s,alipay_s,payment_s) {
      $.ajax({
          url:"payInfo",
          data:{"sellerid":sellerid},
          type:"get",
          dataType:"json",
          success:function(data) {
            $("#pay_").show();//支付链路显示
            $("ul").hide();//页面跳转隐藏
            $(".pay_store").html(store_s);
            $(".pay_sellerid").html(sellerid);
            $(".pay_payment").html(payment_s);
            $(".connect").html(alipay_s);
            //进度
            var sum = data.pay.alipay+data.pay.data+data.pay.sign+data.pay.api+data.pay.receiving+data.pay.deploy;
            shedule(obj,sum,6);
            //判断是否已经对接完成
            if(data.pay.alipay == 1){
                 $(".alipay").attr("disabled",true);
                 $(".alipay").css("backgroundColor","gray")
            }
            if(data.pay.data == 1){
                 $(".data").attr("disabled",true);
                 $(".data").css("backgroundColor","gray")
            }
            if(data.pay.sign == 1){
                 $(".sign").attr("disabled",true);
                 $(".sign").css("backgroundColor","gray")
            }
            if(data.pay.api == 1){
                 $(".api").attr("disabled",true);
                 $(".api").css("backgroundColor","gray")
            }
            if(data.pay.receiving == 1){
                 $(".receiving").attr("disabled",true);
                 $(".receiving").css("backgroundColor","gray")
            }
            if(data.pay.deploy == 1){
                 $(".deploy").attr("disabled",true);
                 $(".deploy").css("backgroundColor","gray")
            }
            $(".pay_txt").val(data.pay.remark)
          },
          error: function() {
              hiAlert("error");
          }
      });
  }

    //进度条显示
function shedule_show(obj){
// 	   obj.style.display="none";
	   obj.nextElementSibling.style.display="block";
}
    //支付进度条显示
function shedule_style(obj,sellerid) {
     $.ajax({
          url:"payInfo",
          data:{"sellerid":sellerid},
          type:"get",
          dataType:"json",
          success:function(data) {
                 //进度
              var sum = data.pay.alipay+data.pay.data+data.pay.sign+data.pay.api+data.pay.receiving+data.pay.deploy;
              shedule(obj,sum,6);
          },
          error: function() {
             hiAlert("error");
          }
     });
}
    //会员进度条显示
function m_shedule_style(obj,sellerid) {
     $.ajax({
          url:"memberInfo",
          data:{"sellerid":sellerid},
          type:"get",
          dataType:"json",
          success:function(data) {
                 //进度
             var sum = data.member.m_data+data.member.m_applay+data.member.m_api+data.member.m_receiving+data.member.m_AppAndSpi_online+data.member.m_success_online;
             shedule(obj,sum,6);
          },
          error: function() {
             hiAlert("error");
          }
     });
}

//支付按下按钮改变状态
function but(obj,name){
  obj.disabled = true;
  obj.style.backgroundColor="gray";
    $.ajax({
          url:"payInfoUpdate",
          data:"sellerid="+$('.pay_sellerid').html()+"&"+name+"="+1,
          type:"get",
          success:function(data) {
            if(data.finish == 1){ hiAlert($(obj).html()+" 进度完成")}
          },
          error: function() {
             hiAlert("error");
          }
     });
}

//会员按下按钮改变状态
function m_but(obj,name){
   obj.disabled = true;
  obj.style.backgroundColor="gray";
    $.ajax({
          url:"memberInfoUpdate",
          data:"sellerid="+$('.m_sellerid').html()+"&"+name+"="+1,
          type:"get",
          success:function(data) {
            if(data.m_finish == 1){ hiAlert($(obj).html()+" 进度完成")}
          },
          error: function() {
             hiAlert("error");
          }
     });
}
  //改变文档状态
	function txt(obj){
           $.ajax({
              url:"payInfoUpdate",
              data:"sellerid="+$('.pay_sellerid').html()+"&remark="+$(obj).val(),
              type:"post",
              success:function(data) {
                 if(data.finish == 1){ hiAlert("文档信息保存完成")}
              },
              error: function() {
                 hiAlert("error");
              }
        });
	}
 //改变文档状态
	function m_txt(obj){
            $.ajax({
              url:"memberInfoUpdate",
              data:"sellerid="+$('.m_sellerid').html()+"&remark="+$(obj).val(),
              type:"post",
              success:function(data) {
                 if(data.m_finish == 1){ hiAlert("文档信息保存完成")};
              },
              error: function() {
                 hiAlert("error");
              }
        });
	}

	//关闭链路弹出框
function pay_hide(){ $("#pay_").hide(); $("ul").show();}
	//关闭链路弹出框
function m_hide(){ $("#member_").hide(); $("ul").show();}


//------------------会员
  //会员发送数据请求
  function sendMemberAjax(obj,sellerid,store_s,member_s) {
      $.ajax({
          url:"memberInfo",
          data:{"sellerid":sellerid},
          type:"get",
          dataType:"json",
          success:function(data) {
            $("#member_").show();//支付链路显示
            $("ul").hide();//页面跳转隐藏
            $(".m_store").html(store_s);
            $(".m_sellerid").html(sellerid);
            $(".m_member").html(member_s);
             //进度
            var sum = data.member.m_data+data.member.m_applay+data.member.m_api+data.member.m_receiving+data.member.m_AppAndSpi_online+data.member.m_success_online;
             shedule(obj,sum,6);
            //判断是否已经对接完成
            if(data.member.m_data == 1){
                 $(".m_data").attr("disabled",true);
                 $(".m_data").css("backgroundColor","gray")
            }
            if(data.member.m_applay == 1){
                 $(".m_applay").attr("disabled",true);
                 $(".m_applay").css("backgroundColor","gray")
            }
            if(data.member.m_api == 1){
                 $(".m_api").attr("disabled",true);
                 $(".m_api").css("backgroundColor","gray")
            }
            if(data.member.m_receiving == 1){
                 $(".m_receiving").attr("disabled",true);
                 $(".m_receiving").css("backgroundColor","gray")
            }
            if(data.member.m_AppAndSpi_online == 1){
                 $(".m_AppAndSpi_online").attr("disabled",true);
                 $(".m_AppAndSpi_online").css("backgroundColor","gray")
            }
            if(data.member.m_success_online == 1){
                 $(".m_success_online").attr("disabled",true);
                 $(".m_success_online").css("backgroundColor","gray")
            }
            $(".m_txt").val(data.member.remark)
          },
          error: function() {
              hiAlert("error");
          }
      });
  }

//////////////////////////////////////////////////
function king_load(obj){
    $("#pass").show();
    $(obj).hide();
}
function king(obj){
     if($(obj).val() == "dahai"){
         $("#onload").hide();
         $(".update_hide").show();
    }
}

///////////////////
//获取商家门店和商品上传信息
function se_on(obj){
     var parent = obj.parentNode;
     $.ajax({
          url:"tissotInfo",
          data:{"sellerid":$(obj).html()},
          type:"get",
          dataType:"json",
          success:function(data) {
            parent.find(".total_store").html(data.tissot.total_store);
            parent.find(".open_store").html(data.tissot.open_store);
            parent.find(".iten").html(data.tissot.item);
            parent.find(".sku").html(data.tissot.sku);
          },
          error: function() {
               hiAlert("error");
           }
     });
}
//商家门店商品进度显示
function son_show(obj){
    $(obj).find(".seller_online").show()
}