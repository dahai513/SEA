<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">

</head>
<body>
<h3><周报></h3>
总体说明：1) <span style="color:rgb(20, 159, 236);">蓝色字体 – 重要进展；</span>2）<span style="background: yellow">黄色背景 - 重要风险预警；</span>3) <span style="background: rgb(146, 208, 80)">绿色背景 - 需进一步跟进</span><br>
<h3>1、重点项目</h3>
<p style="text-decoration:underline">天梭项目</p>

<span style="font-size: 40px">&nbsp;&nbsp;. </span>智慧门店商家接入情况： <br/>
<p><span style="font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;。</span>2312312	</p>
</body>
</html>

