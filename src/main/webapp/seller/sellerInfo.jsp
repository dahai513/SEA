<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<!--清空缓存-->
<METAHTTP-EQUIV="Pragma"CONTENT="no-cache">
<METAHTTP-EQUIV="Cache-Control"CONTENT="no-cache">
<METAHTTP-EQUIV="Expires"CONTENT="0">
<meta charset="UTF-8">
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/css.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/alert.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/sellerInfo.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src='js/jquery-ui.min.js'></script>
<script type="text/javascript" src="js/jquery.alert.js"></script>
<script type="text/javascript" src="js/sellerInfo.js"></script>
<script type="text/javascript">
  //改变当前页数的函数
function changePage(obj){
  //获取连接标签中的内容，当前页数，总页数
  var text = $(obj).html();//链接中的内容
    var currentPageCount = ${currentPage };//当前页数-1
  var totalPageCount = ${totalPageCount };//总页数
  var url= "/sellerInfo?n=";
  //判断是哪一种改变页数的方式
  if("首页" == text){
      window.location.href = url + 0;
  }
  if("末页" == text){
    totalPageCount = totalPageCount-1;
    window.location.href = url + totalPageCount;
  }else if("上一页" == text){
    //判断改变后的页 数是否小于等于0
    if(currentPageCount <= 0){
      //如果小于等于0，提示已经是首页了
      hiAlert("已经是首页了");
    }else{
      //否则跳转到卡片信息管理控制层
      currentPageCount = currentPageCount - 1;
      window.location.href = url + currentPageCount;
    }
  }else if("下一页" == text){
      currentPageCount = currentPageCount+1;
    //判断改变后页数是否大于总页数
    if(currentPageCount >= totalPageCount){
      //如果大于总页数，则提示已经是末页了
      hiAlert("已经是末页了");
    }else{
      //否则跳转到卡片信息管理控制层
      window.location.href = url + currentPageCount;
    }
  }else if("跳转" == text){
    //如果是跳转获取跳转的页数
    var toPage = $("#toPageId").val()-1;
    //判断跳转的页数时候大于等于1并且小于等于总页数
    if( toPage >= 0 && toPage < totalPageCount ){
      //满足跳到卡片信息管理控制层
      window.location.href = url + toPage;
    }else{
      //如果不满足则提示请输入正确的页数,
      hiAlert("请输入正确的页数");
    }
  }
}

function add(){
  $("b:first").html("商家信息录入");
  $("form:first").attr("action", "/sellerAdd?currentPage=${currentPage}");
  $("#f").show().animate({height:220},2000)
  $("#f").css('left',($(window).width()-$("#f").outerWidth())/2)
  $("#f").css('top',($(window).height()-$("#f").outerHeight)/2+$(window).scrollTop())
}
function x(){ $("#f").hide(); }

function update(sellerid,store,csm,payment,pay,member,brand,koubei){
  $("b:first").html("商家信息更新");
  $("form:first").attr("action", "/sellerUpdate?currentPage=${currentPage}");
  $("*[name='sellerid']").val(sellerid);
  $("*[name='store']").val(store);
  $("*[name='sellerid']").attr("readonly",true);
  $("*[name='store']").attr("readonly",true);
  $("*[name='csm']").val(csm);
  $("*[name='payment']").val(payment);
  $("*[name='pay']").val(pay);
  $("*[name='member']").val(member);
  $("*[name='brand']").val(brand);
  $("*[name='koubei']").val(koubei);
  $("#f").show().animate({height:200},2000);
  $("#f").css('left',($(window).width()-$("#f").outerWidth())/2);
  $("#f").css('top',($(window).height()-$("#f").outerHeight)/2+$(window).scrollTop());
  }
</script>
</head> 
<body>

    <!--  支付链路进展 -->
    <fieldset id="pay_" class="fl">
          <legend><b><span class="pay_store"></span>(<span class="pay_sellerid"></span>)--<span class="pay_payment"></span>(<span class="connect"></span>)</b></legend><br><br>
          <img src="images/close.png" onclick="pay_hide()">
          <button onclick="but(this,'alipay')" class="alipay" name="alipay">申请支付宝帐号</button>==>
          <button onclick="but(this,'data')" class="data" name="data">主档数据初始化</button>==>
          <button onclick="but(this,'sign')" class="sign" name="sign">智慧门店签约</button>==>
          <button onclick="but(this,'api')" class="api" name="api">接口开发中</button>==>
          <button onclick="but(this,'receiving')" class="receiving" name="receiving">case验收管理</button>==>
          <button onclick="but(this,'deploy')" class="deploy" name="deploy">门店部署</button>
          <div class="txt">
            备注信息：
            <textarea name="content" cols="80" rows="10" onchange="txt(this)" class="pay_txt"></textarea>
          </div>
    </fieldset>
    <!--  会员链路进展 -->
    <fieldset id="member_" class="fr">
        <legend><b><span class="m_store"></span>(<span class="m_sellerid"></span>)--<span class="m_member"></span></b></legend><br><br>
        <img src="images/close.png" onclick="m_hide()" style="position:relative; left:610px;top:-50px; width:20px;height:20px">
        <button onclick="m_but(this,'m_data')" class="m_data">确定cem数据处理方案</button>==>
        <button onclick="m_but(this,'m_applay')" class="m_applay">isv(“新零售”应用)  品牌商(“商家品牌会员”应用)</button>==>
        <button onclick="m_but(this,'m_api')" class="m_api">会员通开发</button>==>
        <button onclick="m_but(this,'m_receiving')" class="m_receiving">接口测试通过</button>==>
        <button onclick="m_but(this,'m_AppAndSpi_online')" class="m_AppAndSpi_online">自研(上线)-ISV(发布服务市场)-SPI发布上线</button>==>
        <button onclick="m_but(this,'m_success_online')" class="m_success_online">测试平台通过后发布上线</button>
        <div class="m_txt">
          备注信息：
          <textarea name="m_content" cols="80" rows="10"  onchange="m_txt(this)"></textarea>
         </div>
    </fieldset>

	<div id="pageAll">
		<div class="page">
			<!-- banner页面样式 -->
			<div class="banner">
				<form  method="get"  >
                <!-- 商家信息表单的表单-->
                	<fieldset id="f" style="display:none">
                		<legend><b>商家录入</b></legend>
                		<div id="x" onclick="x()">×</div><br>
                		SeID：<input name="sellerid">
                		旗舰店：<input name="store"><br/>
                 		CSM：<input name="csm">
                		品牌号：<input name="brand"><br/>
                		支付：
                            <select name="payment" class="payment">
                                 <option value =""></option>
                                 <option value ="天梭B">天梭B</option>
                                 <option value="云POS">云POS</option>
                            </select>
                            <select name="pay" >
                                 <option value =""></option>
                                 <option value ="间连">间连</option>
                                 <option value="间改直">间改直</option>
                                 <option value="直连">直连</option>
                            </select>
                		会员：
                		    <select name="member">
                                 <option value =""></option>
                                 <option value ="ISTORE">ISTORE</option>
                                 <option value="线下通">线下通</option>
                                 <option value="全打通">全打通</option>
                           </select>

                		口碑：
               		    <select name="koubei">
               		              <option value =" "> </option>
                                 <option value ="是">是</option>
                                 <option value ="否">否</option>
                           </select>
                		<input type="submit" style="padding:5px; background:white">
                	</fieldset>
                </form>
                <div class="add">
 					<a class="addA" onclick="add()">新增</a>
 				</div>
 				<div id="onload">
 				    <div onclick="king_load(this)" id="king">登录</div>
 				    <input type="password" id="pass" onchange="king(this)"/>
 				</div>
				<!-- 商家 表格 显示 -->
				<div class="banShow">
					<table border="1" cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td class="tdColor">SellerId</td>
							<td class="tdColor">旗舰店</td>
							<td class="tdColor">CSM</td>
							<td class="tdColor">支付方案</td>
							<td class="tdColor">连接方式</td>
							<td class="tdColor">会员方案</td>
							<td class="tdColor">品牌号</td>
							<td class="tdColor">口碑</td>
							<td class="tdColor update_hide">操作</td>
						</tr>
						<c:forEach items="${sellers}" var="seller">
							<tr class="tr" onmouseover="trbgc_show(this)" onmouseout="trbgc_hide(this)">
								<td class="seon"  onmouseover="son_show(this)">
								    <span onclick="se_on(this)">${seller.sellerid}</span><!--获取商家门店，商品情况--><p/>
                                    <div class="seller_online" ><!--获取商家门店，商品情况-->
                                          <p><span class="total_store">${seller.tissotInfos.total_store}</span>
                                         - <span class="open_store">${seller.tissotInfos.open_store}</span>
                                         - <span class="item">${seller.tissotInfos.item}</span>
                                         - <span class="sku">${seller.tissotInfos.sku}</p>
                                     </div>
                                </td>
								<td><a href="http://auction-dev.taobao.org:9999/seller/search?sellerId=${seller.sellerid}" target="_blank" style="color:black">${seller.store}</a></td>
								<td>${seller.csm}</td>
								<!-------------支付链路------------------>
								<td style="cursor:pointer;">
								<span onmouseover="shedule_show(this)">${seller.payment}</span>
									<!-- 进度条 -->
                                	<div class="shedule_"onmouseout="shedule_hide(this)" onmouseover="shedule_style(this,'${seller.sellerid}')"  onclick="sendPayAjax(this,'${seller.sellerid}','${seller.store}','${seller.pay}','${seller.payment}')">
                                		<p class="bottom"></p>
                                		<p class="shedule"><span class="percent"></span></p>
                                	</div>
								</td>
								<td>${seller.pay}</td>
								<!-------------会员通------------------>
								<td style="cursor:pointer;">
								  <span  onmouseover="shedule_show(this)" >${seller.member}</span>
									<!-- 进度条 -->
                                	<div class="shedule_" onmouseout="shedule_hide(this)" onmouseover="m_shedule_style(this,'${seller.sellerid}')" onclick="sendMemberAjax(this,'${seller.sellerid}','${seller.store}','${seller.member}')">
                                		<p class="bottom"></p>
                                		<p class="shedule"><span class="percent"></span></p>
                                	</div>
                                </td>
								<td>${seller.brand}</td>
								<td>${seller.koubei}</td>
								<td class="update_hide">
									<a style="cursor:pointer" onclick="update('${seller.sellerid}','${seller.store}','${seller.csm}','${seller.payment}','${seller.pay}','${seller.member}','${seller.brand}','${seller.koubei}')">修改</a>
 								</td>
							</tr>
						</c:forEach>
					</table>
					<!-- 页数选择 -->
					<ul>
						<li><a onclick="changePage(this)">首页</a></li>
						<li><a onclick="changePage(this)">末页</a></li>
						<li><a onclick="changePage(this)">上一页</a></li>
						<li><a onclick="changePage(this)">下一页</a></li>
						<li style="float:left; margin: 10px;">当前第<span>${currentPage_1}</span>页</li>
						<li style="float:left; margin: 10px;">共<span>${totalPageCount}</span>页</li>
						<li style="float:left; margin: 10px;"><input style="width:30px;" id="toPageId"/></li>
						<li><a onclick="changePage(this)">跳转</a></li>
					</ul><!-- 页数选择 分页-->
				</div>
			</div>
		</div>
	</div>    
</body>
</html>
