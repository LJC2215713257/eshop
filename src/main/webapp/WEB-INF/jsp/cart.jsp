<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <base href="<%=basePath %>"/>
<title>购物车-合众饰品专卖</title>
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
 <style>
  h1{
     text-align: center;
  }

 </style>
<script>
 var cart = new Array();
$(document).ready(function(){
  //show or hide:delBtn
  $(".edit").toggle(function(){
      $("span[name=showNum]").hide();
	  $(this).parent().siblings("dd").find(".delBtn").fadeIn();
	  $(this).html("完成");
	  $(".numberWidget").show();
	  $(".priceArea").hide();
	  },
      function(){
      $("span[name=showNum]").show();
      if(cart.length>0) {
          $.post("order/update", {cart:JSON.stringify(cart)}, function (data) {
              console.log(data);
          });
      }
	  $(this).parent().siblings("dd").find(".delBtn").fadeOut();
	  $(this).html("编辑");
	  $(".numberWidget").hide();
	  $(".priceArea").show();
  });
  //minus
  $(".minus").click(function(){
	  var currNum=$(this).siblings(".number");
	  $("#amount").html(parseFloat($("#amount").html())-parseFloat($(this).siblings("[type=hidden]").val()));
	  if(currNum.val()<=1){
		  $(this).parents("dd").remove();
		  $("#catenum").html(parseInt($("catenum").html())-1);
          nullTips();
      }else{
          $(this).parents("dd").find("span[name=showNum]").html(parseInt(currNum.val())-1);
          currNum.val(parseInt(currNum.val())-1);
      }
      var i;
      for(i=0;i<cart.length;i++){
          if(cart[i].goodsId==currNum.attr("name")){
              cart[i].num=parseInt(currNum.val())-1;
              break;
          }
      }
      if(i==cart.length){
          var obj = new Object();
          obj.goodsId = currNum.attr("name");
          obj.num=parseInt(currNum.val())-1;
          cart.push(obj);
      }

  });
  //plus
  $(".plus").click(function(){
	  var currNum=$(this).siblings(".number");
      $("#amount").html(parseFloat($("#amount").html())+parseFloat($(this).siblings("[type=hidden]").val()));
      var i;
	  for(i=0;i<cart.length;i++){
	      if(cart[i].goodsId==currNum.attr("name")){
	          cart[i].num=parseInt(currNum.val())+1;
	          break;
          }
      }
      if(i==cart.length){
          var obj = new Object();
          obj.goodsId = currNum.attr("name");
          obj.num=parseInt(currNum.val())+1;
          cart.push(obj);
      }
      $(this).parents("dd").find("span[name=showNum]").html(parseInt(currNum.val())+1);
	  currNum.val(parseInt(currNum.val())+1);

  });
  //delBtn
  $(".delBtn").click(function(){
      var goodsid = $(this).parent().find(".number").attr("name");
      var i;
      for(i=0;i<cart.length;i++){
          if(cart[i].goodsId==goodsid){
              cart[i].num=0;
              console.log();
              break;
          }
      }
      if(i==cart.length){
          var obj = new Object();
          obj.goodsId = goodsid;
          obj.num=0;
          cart.push(obj);
      }
      $("#amount").html(parseFloat($("#amount").html())
          +parseFloat($(this).siblings("[type=hidden]").val())*parseInt($(this).siblings(".number").val()));
      $(this).parent().remove();
      $("#catenum").html(parseInt($("catenum").html())-1);
      nullTips();
  });
  //isNull->tips
  function nullTips(){
	  if($(".cart dd").length==0){
		  var tipsCont="<mark style='display:block;background:none;text-align:center;color:grey;'>购物车为空！</mark>"
		  $(".cart").remove();
		  $("body").append(tipsCont);
      }
  }

  $("input[name=all]").click(function () {
      if($(this).attr("checked")) {
          $("input[name=row]").each(function (index, item) {
              $(item).attr("checked", true);
          });
      }else{
          $("input[name=row]").each(function (index, item) {
              $(item).attr("checked", false);
          });
      }
  });
});
</script>
</head>
<body>
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>购物车</h1>
</header>
<dl class="cart">

 <% if(session.getAttribute("user")!=null){%>
    <dt>
        <label><input type="checkbox" name="all"/>全选</label>
        <a class="edit">编辑</a>
    </dt>
 <c:forEach items="${cart.orderGoodsList}" var="d">
 <dd>
  <input type="checkbox" name="row"/>
  <a href="product${d.goodsId}" class="goodsPic"><img src="../../upload/${d.imageUrl}"/></a>
  <div class="goodsInfor">
   <h2>
    <a href="product${d.goodsId}">${d.goodsName}</a>
    <span name="showNum">${d.goodsNum}</span>
   </h2>
   <div class="priceArea">
    <strong>${d.goodsPayPrice}</strong>
    <del>${d.goodsPrice}</del>
   </div>
   <div class="numberWidget">
    <input type="button" value="-" class="minus"/>
    <input type="hidden" name="price" value="${d.goodsPayPrice}"/>
    <input type="text" name="${d.goodsId}" value="${d.goodsNum}" disabled  class="number"/>
    <input type="button" value="+"  class="plus"/>
   </div>
  </div>
  <a class="delBtn">删除</a>
 </dd>
 </c:forEach>
 <%} else{%>
    <h1>未登录！请先<a href="login">登录</a></h1>
 <%}%>
 <%--<dd>--%>
  <%--<input type="checkbox" name="row"/>--%>
  <%--<a href="product.jsp" class="goodsPic"><img src="../../upload/goods002.jpg"/></a>--%>
  <%--<div class="goodsInfor">--%>
   <%--<h2>--%>
    <%--<a href="product.jsp">烟灰缸 玻璃工艺品...</a>--%>
    <%--<span>1</span>--%>
   <%--</h2>--%>
   <%--<div class="priceArea">--%>
    <%--<strong>0.00</strong>--%>
    <%--<del>0.00</del>--%>
   <%--</div>--%>
   <%--<div class="numberWidget">--%>
    <%--<input type="button" value="-" class="minus"/>--%>
    <%--<input type="text" value="1" disabled class="number"/>--%>
    <%--<input type="button" value="+" class="plus"/>--%>
   <%--</div>--%>
  <%--</div>--%>
  <%--<a class="delBtn">删除</a>--%>
 <%--</dd>--%>
 <%--<dd>--%>
  <%--<input type="checkbox" name="row"/>--%>
  <%--<a href="product.jsp" class="goodsPic"><img src="../../upload/goods003.jpg"/></a>--%>
  <%--<div class="goodsInfor">--%>
   <%--<h2>--%>
    <%--<a href="product.jsp">迷你花杯 送底座</a>--%>
    <%--<span>1</span>--%>
   <%--</h2>--%>
   <%--<div class="priceArea">--%>
    <%--<strong>0.00</strong>--%>
    <%--<del>0.00</del>--%>
   <%--</div>--%>
   <%--<div class="numberWidget">--%>
    <%--<input type="button" value="-" class="minus"/>--%>
    <%--<input type="text" value="1" disabled  class="number"/>--%>
    <%--<input type="button" value="+" class="plus"/>--%>
   <%--</div>--%>
  <%--</div>--%>
  <%--<a class="delBtn">删除</a>--%>
 <%--</dd>--%>
</dl>
<!--bottom nav-->
<div style="height:1rem;"></div>
<aside class="btmNav">
 <ul>
  <li><a class="cart_icon"><em id="catenum">
      <c:if test="${orderNum!=null}">
          ${orderNum}
      </c:if>
      <c:if test="${orderNum==null}">
          0
      </c:if>
  </em></a></li>
  <li><a>合计：￥<span id="amount">
      <c:if test="${cart!=null}">
          ${cart.orderAmount}
      </c:if>
      <c:if test="${cart==null}">
          0
      </c:if>
  </span></a></li>
  <li><a href="order/savaCart">立即下单</a></li>
 </ul>
</aside>
</body>
</html>
