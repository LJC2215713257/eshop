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
<title>我喜欢-合众饰品专卖</title>
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
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="../../css/style.css" />
<script src="../../js/jquery.js"></script>
<script>
$(document).ready(function(){
     //飞入动画，具体根据实际情况调整
        var p=2;
        if($("#good li").length<10){
            $(".more_btn").hide();
        }
        $(".addToCart").click(function(){
            $.post("order/addGoods",
                {goodsId:$(this).attr("name")}
                ,function (data) {
                    console.log(data);
                }
            );
	        $(".hoverCart a").html(parseInt($(".hoverCart a").html())+1);/*测试+1*/
            var shopOffset = $(".hoverCart").offset();
            var cloneDiv = $(this).parent().siblings(".goodsPic").clone();
            var proOffset = $(this).parent().siblings(".goodsPic").offset();
            cloneDiv.css({ "position": "absolute", "top": proOffset.top, "left": proOffset.left });
            $(this).parent().siblings(".goodsPic").parent().append(cloneDiv);
            cloneDiv.animate({
				width:0,
				height:0,
                left: shopOffset.left,
                top: shopOffset.top,
				opacity:1
            },"slow");

	   });
        $(".more_btn").click(function () {
            $.post("col/list",{page:2},function (data) {
                console.log(data);
                var i=0;
                var str = new Array();
                for(i=0;i<data.length;i++){
                    str.push("   <li>\n" +
                        "    <a href=\"goods/info"+data[i].goodsId+"\" class=\"goodsPic\">\n" +
                        "     <img src=\"../../upload/"+data[i].goodsImage+"\"/>\n" +
                        "    </a>\n" +
                        "    <div class=\"goodsInfor\">\n" +
                        "     <h2>\n" +
                        "      <a href=\"goods/info"+data[i].goodsId+"\">"+data[i].goodsName+"</a>\n" +
                        "     </h2>\n" +
                        "     <p>\n" +
                        "      <del>"+data[i].goodsPrice+"</del>\n" +
                        "     </p>\n" +
                        "     <p>\n" +
                        "      <strong class=\"price\">"+data[i].goodsSellPrice+"</strong>\n" +
                        "     </p>\n" +
                        "     <a class=\"addToCart\">&#126;</a>\n" +
                        "    </div>\n" +
                        "   </li>");
                }
                $("#goods").html($("#goods").html()+str.join());
            });
        });
});
</script>
</head>
<body style="background:white;">
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>我喜欢</h1>
</header>
<section class="productList">
  <ul id="goods">
   <c:forEach items="${goodsls}" var="goods">
   <li>
    <a href="goods/info${goods.goodsId}" class="goodsPic">
     <img src="../../upload/${goods.goodsImage}"/>
    </a>
    <div class="goodsInfor">
     <h2>
      <a href="goods/info${goods.goodsId}">${goods.goodsName}</a>
     </h2>
     <p>
      <del>${goods.goodsPrice}</del>
     </p>
     <p>
      <strong class="price">${goods.goodsSellPrice}</strong>
     </p>
     <a class="addToCart">&#126;</a>
    </div>
   </li>
   </c:forEach>
   <%--<li>--%>
    <%--<a href="product.jsp" class="goodsPic">--%>
     <%--<img src="../../upload/goods002.jpg"/>--%>
    <%--</a>--%>
    <%--<div class="goodsInfor">--%>
     <%--<h2>--%>
      <%--<a href="product.jsp">红萝卜3斤装</a>--%>
     <%--</h2>--%>
     <%--<p>--%>
      <%--<del>12.90</del>--%>
     <%--</p>--%>
     <%--<p>--%>
      <%--<strong class="price">8.90</strong>--%>
     <%--</p>--%>
     <%--<a class="addToCart">&#126;</a>--%>
    <%--</div>--%>
   <%--</li>--%>
   <%--<li>--%>
    <%--<a href="product.jsp" class="goodsPic">--%>
     <%--<img src="../../upload/goods003.jpg"/>--%>
    <%--</a>--%>
    <%--<div class="goodsInfor">--%>
     <%--<h2>--%>
      <%--<a href="product.jsp">西红柿5斤装</a>--%>
     <%--</h2>--%>
     <%--<p>--%>
      <%--<del>9.90</del>--%>
     <%--</p>--%>
     <%--<p>--%>
      <%--<strong class="price">6.90</strong>--%>
     <%--</p>--%>
     <%--<a class="addToCart">&#126;</a>--%>
    <%--</div>--%>
   <%--</li>--%>
   <%--<li>--%>
    <%--<a href="product.jsp" class="goodsPic">--%>
     <%--<img src="../../upload/goods005.jpg"/>--%>
    <%--</a>--%>
    <%--<div class="goodsInfor">--%>
     <%--<h2>--%>
      <%--<a href="product.jsp">西红柿5斤装</a>--%>
     <%--</h2>--%>
     <%--<p>--%>
      <%--<del>9.90</del>--%>
     <%--</p>--%>
     <%--<p>--%>
      <%--<strong class="price">6.90</strong>--%>
     <%--</p>--%>
     <%--<a class="addToCart">&#126;</a>--%>
    <%--</div>--%>
   <%--</li>--%>
   <%--<li>--%>
    <%--<a href="product.jsp" class="goodsPic">--%>
     <%--<img src="../../upload/goods004.jpg"/>--%>
    <%--</a>--%>
    <%--<div class="goodsInfor">--%>
     <%--<h2>--%>
      <%--<a href="product.jsp">西红柿5斤装</a>--%>
     <%--</h2>--%>
     <%--<p>--%>
      <%--<del>9.90</del>--%>
     <%--</p>--%>
     <%--<p>--%>
      <%--<strong class="price">6.90</strong>--%>
     <%--</p>--%>
     <%--<a class="addToCart">&#126;</a>--%>
    <%--</div>--%>
   <%--</li>--%>
  </ul>
  <a class="more_btn">加载更多</a>

</section>
<!--floatCart-->
<div class="hoverCart">
 <a href="cart.jsp">0</a>
</div>
</body>
</html>
