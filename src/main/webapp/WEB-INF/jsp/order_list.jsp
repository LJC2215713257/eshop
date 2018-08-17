<%@ page import="org.springframework.web.servlet.support.RequestContext" %>
<%@ page import="cn.edu.jxufe.entity.Memberinfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 Memberinfo mem = (Memberinfo) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8"/>
 <base href="<%=basePath %>"/>
<title>订单列表-合众饰品专卖</title>
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
<script src="../../js/jquery.js"></script>
 <script>
     var orderid;
  $(document).ready(function () {


  });

  function  updateState(state,obj) {
      orderid = $(obj).attr("name");
      console.log($(obj));
      if(state==1){
          $.post("order/payfororder",{orderid:$(obj).attr("name")},function (data) {
              if(data.title=="1"){
                  location.href="order/statepage";
              }else{
                  alert("付款失败！");
              }
          });
      }

      if(state==0){
          $.post("order/deletefororder",{orderid:$(obj).attr("name")},function (data) {
              if(data.title=="1"){
                  $("li [name="+orderid+"]").remove();
              }else{
                  alert("删除失败！");
              }
          });
      }
  }
 </script>
</head>
<body>
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>订单列表</h1>
</header>
<!--异步处理，此处不做TAB形式,注意当前状态样式currStyle-->
<aside class="orderSift">
 <a class="currStyle">待付款</a>
 <a>待发货</a>
 <a>已完成</a>
</aside>

<% if (mem!=null){%>
<!--订单列表-->
<ul class="orderList">
 <!--订单循环li-->
 <c:forEach items="${orders}" var="order">
  <li name="${order.orderId}">
   <dl>
    <dt>
     <span>订单：${order.orderSn}</span><c:choose>
      <c:when test="${order.orderState==0}">
     <span>已取消</span>
      </c:when>
      <c:when test="${order.orderState==20}">
        <span>已付款</span>
      </c:when>
      <c:when test="${order.orderState==30}">
        <span>已发货</span>
      </c:when>
      <c:when test="${order.orderState==40}">
        <span>已收货</span>
      </c:when>
      <c:otherwise> <span>未付款</span></c:otherwise>
     </c:choose>
    </dt>
    <!--订单产品循环dd-->
    <c:forEach items="${order.orderGoodsList}" var="detail">
    <dd>
     <h2>${detail.goodsName}</h2>
     <strong>
      <em>${detail.goodsPrice}</em>
      <span>${detail.goodsNum}</span>
     </strong>
    </dd>
    </c:forEach>
    <dd>
       <span>商品数量：<b>${fn:length(order.orderGoodsList)}</b></span>
       <span>实付：<b>${order.orderAmount}</b></span>
    </dd>
    <dd>
     <c:if test="${order.orderState==10}">
     <a class="order_delBtn" name="${order.orderId}" onclick="updateState(0,this)">删除订单</a>
     <a class="order_payBtn" name="${order.orderId}" onclick="updateState(1,this)">付款</a>
     </c:if>
    </dd>
   </dl>
  </li>
 </c:forEach>
 <%--<li>--%>
  <%--<dl>--%>
   <%--<dt>--%>
    <%--<span>订单：201512130108</span>--%>
    <%--<span>待付款</span>--%>
   <%--</dt>--%>
   <%--<!--订单产品循环dd-->--%>
   <%--<dd>--%>
    <%--<h2>优质牛肉5kg散装</h2>--%>
    <%--<strong>--%>
     <%--<em>0.00</em>--%>
     <%--<span>1</span>--%>
    <%--</strong>--%>
   <%--</dd>--%>
   <%--<dd>--%>
    <%--<h2>新疆葡萄干散装</h2>--%>
    <%--<strong>--%>
     <%--<em>0.00</em>--%>
     <%--<span>1</span>--%>
    <%--</strong>--%>
   <%--</dd>--%>
   <%--<dd>--%>
    <%--<span>商品数量：<b>2</b></span>--%>
    <%--<span>实付：<b>0.00</b></span>--%>
   <%--</dd>--%>
   <%--<dd>--%>
    <%--<a class="order_delBtn">删除订单</a>--%>
    <%--<a class="order_payBtn">付款</a>--%>
   <%--</dd>--%>
  <%--</dl>--%>
 <%--</li>--%>
 <%--<!--订单循环li-->--%>
 <%--<li>--%>
  <%--<dl>--%>
   <%--<dt>--%>
    <%--<span>订单：201512130108</span>--%>
    <%--<span>待发货</span>--%>
   <%--</dt>--%>
   <%--<!--订单产品循环dd-->--%>
   <%--<dd>--%>
    <%--<h2>优质牛肉5kg散装</h2>--%>
    <%--<strong>--%>
     <%--<em>0.00</em>--%>
     <%--<span>1</span>--%>
    <%--</strong>--%>
   <%--</dd>--%>
   <%--<dd>--%>
    <%--<span>商品数量：<b>1</b></span>--%>
    <%--<span>实付：<b>0.00</b></span>--%>
   <%--</dd>--%>
   <%--<dd>--%>
    <%--<a class="order_payBtn">待发货</a>--%>
   <%--</dd>--%>
  <%--</dl>--%>
 <%--</li>--%>
 <%--<!--订单循环li-->--%>
 <%--<li>--%>
  <%--<dl>--%>
   <%--<dt>--%>
    <%--<span>订单：201512130108</span>--%>
    <%--<span>已完成</span>--%>
   <%--</dt>--%>
   <%--<!--订单产品循环dd-->--%>
   <%--<dd>--%>
    <%--<h2>优质牛肉5kg散装</h2>--%>
    <%--<strong>--%>
     <%--<em>0.00</em>--%>
     <%--<span>1</span>--%>
    <%--</strong>--%>
   <%--</dd>--%>
   <%--<dd>--%>
    <%--<span>商品数量：<b>1</b></span>--%>
    <%--<span>实付：<b>0.00</b></span>--%>
   <%--</dd>--%>
   <%--<dd>--%>
    <%--<a class="order_delBtn">已完成</a>--%>
   <%--</dd>--%>
  <%--</dl>--%>
 <%--</li>--%>
</ul>
<%}else{%>
<h1 style="margin-top: 30px;font-size: 24px;color:#BC8F8F; text-align: center;">用户未登录！<a href="login">登录</a></h1>
<%}%>
</body>
</html>
