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
<title>产品详情-合众饰品专卖</title>
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
$(document).ready(function(){
  //效果测试，程序对接可将其删除
  $(".add_btn").click(function(){
	  $(".cart_icon em").html(parseInt($(".cart_icon em").html())+1);
	  });
  

});
function shopCart() {
    $.ajax({
        url: "cart/list",
        method:"post",
        dataType:"json",
        success: function(data){
            alert(data);
        }
    });
}
</script>
</head>
<body>
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>产品详情</h1>
</header>
<div class="pro_bigImg">
 <img src="../../upload/${goods.goodsImage}"/>
</div>
<!--base information-->
<section class="pro_baseInfor">
 <h2>${goods.goodsName} ${goods.goodsSubtitle}</h2>
 <p>
  <strong>${goods.goodsSellPrice}</strong>
  <del>${goods.goodsPrice}</del>
  <a class="add_btn">加入购物车</a>
 </p>
</section>
<!--product infor-->
<div class="pro_infor">
 <ul>
  <li>
   <span>创建时间</span>
   <span><time ><fmt:formatDate value="${goods.createTime}" pattern="yyyy-MM-dd"/></time></span>
  </li>
  <li>
   <span>设计者</span>
   <span>HZIT</span>
  </li>
  <li class="more_link" onClick="location.href='comment.html'">
   <span>评论</span>
   <span>共计<b>${goods.commentNum}</b>人点评</span>
  </li>
  <li>
   <span>成交</span>
   <span>共计<b>${goods.salenumNum}</b>笔</span>
  </li>
  <li>
   <span>点赞</span>
   <span>共计<b>${goods.thumbsUpNum}</b>人</span>
  </li>
 </ul>
</div>
<!--bottom nav-->
<div style="height:1rem;"></div>
<aside class="btmNav">
 <div class="cart_list">

 </div>
 <ul >
  <li onclick="shopCart()"><a class="cart_icon" ><em>0</em></a></li>
  <li><a>合计：￥0.00</a></li>
  <li><a href="order/list">立即下单</a></li>
 </ul>
</aside>
</body>
</html>
