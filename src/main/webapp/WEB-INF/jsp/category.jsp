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
<title>分类列表-合众饰品专卖</title>
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
<script src="../../js/swiper.min.js"></script>
<script>
 var page_n=1;
 var cateid = "goods/next_page";
 var sort = "asc";
$(document).ready(function(){
  $(".des_icon").click(function(){
	  $(this).toggleClass("asc_icon");
	  });
  $(".drop_icon").click(function(){
	  $(".drop_list").toggle();
	  $(".drop_list li a").click(function(){
		  $(this).parents(".drop_list").hide();
      });
  });
  $(".more_btn").click(function () {
      console.log(cateid);
      console.log("more info");
      page_n+=1;

      $.post(cateid,{sort:sort,page:page_n},function (data) {
          console.log(data);
          var gl = $("ul[name=goods]");
          var str = new Array();
          for(var i=0;i<data.length;i++){
              str.push("<li>\n" +
                  "      <a href=\"goods/info"+data[i].goodsId+"\" class=\"goodsPic\">\n" +
                  "       <img src=\""+data[i].goodsImage+"\"/>\n" +
                  "      </a>\n" +
                  "      <div class=\"goodsInfor\">\n" +
                  "       <h2>\n" +
                  "        <a href=\"goods/info"+data[i].goodsId+"\">"+data[i].goodsName+" "+data[i].gcName+"</a>\n" +
                  "       </h2>\n" +
                  "       <p>\n" +
                  "        <del>"+data[i].goodsPrice+"</del>\n" +
                  "       </p>\n" +
                  "       <p>\n" +
                  "        <strong class=\"price\">"+data[i].goodsSellPrice+"</strong>\n" +
                  "       </p>\n" +
                  "       <a class=\"addToCart\">&#126;</a>\n" +
                  "      </div>\n" +
                  "     </li>");
          }
          if(data.length<10){
              $(".more_btn").hide();
          }
          $(gl).html($(gl).html()+str.join());
      });
  });
  $("ul[name=category]>li>a").click(function () {
      cateid = $(this).attr("name");
      page_n = 1;
      $.post($(this).attr("name"),{},function (data) {
          var gl = $("ul[name=goods]");
          var str = new Array();

          for(var i=0;i<data.length;i++){
              str.push("<li>\n" +
                  "      <a href=\"goods/info"+data[i].goodsId+"\" class=\"goodsPic\">\n" +
                  "       <img src=\""+data[i].goodsImage+"\"/>\n" +
                  "      </a>\n" +
                  "      <div class=\"goodsInfor\">\n" +
                  "       <h2>\n" +
                  "        <a href=\"goods/info"+data[i].goodsId+"\">"+data[i].goodsName+" "+data[i].gcName+"</a>\n" +
                  "       </h2>\n" +
                  "       <p>\n" +
                  "        <del>"+data[i].goodsPrice+"</del>\n" +
                  "       </p>\n" +
                  "       <p>\n" +
                  "        <strong class=\"price\">"+data[i].goodsSellPrice+"</strong>\n" +
                  "       </p>\n" +
                  "       <a class=\"addToCart\">&#126;</a>\n" +
                  "      </div>\n" +
                  "     </li>");
          }
          if(data.length<10){
              $(".more_btn").hide();
          }
          $(gl).html(str.join());
      });
  });
   //飞入动画，具体根据实际情况调整
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
   $("a[name=sort_price]").click(function () {

   });
	var mySwiper = new Swiper('.swiper-container',{
      slidesPerView :5,
      slidesPerGroup :5,
	})
});
</script>
</head>
<body style="background:white;">
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>分类列表</h1>
</header>
<!-- category Swiper -->
<div class="swiper-container category_list">
    <ul class="swiper-wrapper" name="category">
     <c:forEach items="${cates}" var="c">
       <li class="swiper-slide"><a href="javascript:void(0)" name="cate/findcate${c.catId}">${c.catName}</a></li>
     </c:forEach>
        <%--<li class="swiper-slide"><a href="category.jsp">分类</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp">横向滚动</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp" class="curr_link">当前分类</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp">分类</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp">玻璃</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp">创意</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp">设计</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp">设计稿</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp">测试</a></li>--%>
        <%--<li class="swiper-slide"><a href="category.jsp">分类</a></li>--%>
    </ul>
    <!-- Add Pagination -->
    <div class="swiper-pagination"></div>
</div>
 <!--asc->1[升序asc_icon];des->0[降序des_icon]-->
 <ul class="sift_nav">
  <li><a name="goodsSellPrice" class="des_icon">价格</a></li>
  <li><a name="sort_sale" class="des_icon">销量优先</a></li>
  <li>
   <a class="nav_li drop_icon">筛选项目</a>
   <ul class="drop_list">
    <li><a name="sort_comment">评论</a></li>
    <li><a name="sort_thu">点赞</a></li>
    <%--<li><a> </a></li>--%>
    <%--<li><a>自定义</a></li>--%>
   </ul>
  </li>
 </ul>
<!--productList-->
<section class="productList">
  <ul name="goods">
   <c:forEach items="${goods}" var="g">
     <li>
      <a href="goods/info${g.goodsId}" class="goodsPic">
       <img src="${g.goodsImage}"/>
      </a>
      <div class="goodsInfor">
       <h2>
        <a href="goods/info${g.goodsId}">${g.goodsName} ${g.gcName}</a>
       </h2>
       <p>
        <del>${g.goodsPrice}</del>
       </p>
       <p>
        <strong class="price">${g.goodsSellPrice}</strong>
       </p>
       <a class="addToCart">&#126;</a>
      </div>
     </li>
   </c:forEach>
   </ul>
   <%--<li>--%>
    <%--<a href="product.jsp" class="goodsPic">--%>
     <%--<img src="../../upload/goods001.jpg"/>--%>
    <%--</a>--%>
    <%--<div class="goodsInfor">--%>
     <%--<h2>--%>
      <%--<a href="product.jsp">水晶骷髅头 工艺品</a>--%>
     <%--</h2>--%>
     <%--<p>--%>
      <%--<del>5.90</del>--%>
     <%--</p>--%>
     <%--<p>--%>
      <%--<strong class="price">3.90</strong>--%>
     <%--</p>--%>
     <%--<a class="addToCart">&#126;</a>--%>
    <%--</div>--%>
   <%--</li>--%>
   <%--<li>--%>
    <%--<a href="product.jsp" class="goodsPic">--%>
     <%--<img src="../../upload/goods002.jpg"/>--%>
    <%--</a>--%>
    <%--<div class="goodsInfor">--%>
     <%--<h2>--%>
      <%--<a href="product.jsp">时尚烟灰缸 玻璃制品</a>--%>
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
      <%--<a href="product.jsp">花杯 带底座</a>--%>
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
      <%--<a href="product.jsp">新婚天鹅 玻璃工艺品</a>--%>
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
      <%--<a href="product.jsp">招财貔貅</a>--%>
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
  <%--</ul>--%>
  <a class="more_btn">加载更多</a>
</section>
<!--floatCart-->
<div class="hoverCart">
 <a href="cart">0</a>
</div>
</body>
</html>
