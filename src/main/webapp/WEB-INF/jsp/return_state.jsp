<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8"/>
 <base href="<%=basePath %>"/>
<title>订单状态返回-合众饰品专卖</title>
<meta name="keywords"  content="KEYWORDS..." />
<meta name="description" content="DESCRIPTION..." />
<meta name="author" content="HZIT" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name='apple-touch-fullscreen' content='yes'>
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<link rel="icon" href="../../images/icon/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon-precomposed" sizes="57x57" href="../../images/icon/apple-touch-icon-57x57-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="120x120" href="../../images/icon/apple-touch-icon-120x120-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="196x196" href="../../images/icon/apple-touch-icon-196x196-precomposed.png">
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="../../css/style.css" />
</head>
<body>
<!--header-->
<header>
 <a href="index" class="iconfont backIcon">&#60;</a>
 <h1>订单状态</h1>
</header>
<section class="return_state">
 <!--订单状态图标：0为成功；1为失败-->
 <h2 class="state_0">订单提交成功！</h2>
 <p>订单编号：${os.orderSn}</p>
 <p>订单金额：<strong>${os.orderAmount}</strong></p>
 <p>支付时间：<time><fmt:formatDate value="${os.paymentTime}" pattern="yyyy-MM-dd-mm-ss"/></time></p>
 <p>
  <a href="order_list">查看订单</a>
  <a href="index">返回首页</a>
 </p>
</section>
</body>
</html>
