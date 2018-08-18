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
  <title>合众饰品专卖</title>
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
    $(document).ready(function(){
      var mySwiper = new Swiper('.slide',{
        autoplay:5000,
        visibilityFullFit : true,
        loop:true,
        pagination : '.pagination',
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
    });
  </script>
</head>
<body>
<!--header-->
<header>
  <a href="area/location" class="location"><c:if test="${location==null}">北京</c:if>
    <c:if test="${location!=null}">${location}</c:if></a>
  <h1>合众饰品专卖</h1>
  <a href="search" class="rt_searchIcon">&#37;</a>
</header>
<!--slide-->
<div class="slide">
  <div class="swiper-wrapper">
      <%--循环顶部图片--%>
      <c:forEach items="${advs}" var="adv">
    <div class="swiper-slide">
      <a href="${adv.advLinkUrl}">
        <img src="${adv.advPicUrl}"/>
      </a>
    </div>
      </c:forEach>

  </div>
  <div class="pagination"></div>
</div>
<!--Tab:productList-->
<dl class="tab_proList">
  <dd>
    <ul>
      <c:forEach items="${goodslist}" var="goods">
        <li>
          <div class="productArea">
            <a href="goods/info${goods.goodsId}" class="goodsPic">
              <img src="${goods.goodsImage}"/>
            </a>
            <div class="goodsInfor">
              <h2>
                <a href="goods/info${goods.goodsId}">${goods.goodsName} </a>
              </h2>
              <p>
                <del>${goods.goodsPrice}</del>
              </p>
              <p>
                <strong class="price">${goods.goodsSellPrice}</strong>
              </p>
              <a class="addToCart" name="${goods.goodsId}">&#126;</a>
            </div>
          </div>
          <aside>
            <a class="like_icon">${goods.thumbsUpNum}</a>
            <a class="comment_icon">${goods.commentNum}</a>
            <a class="deal_icon">${goods.salenumNum}</a>
          </aside>
        </li>
      </c:forEach>
    </ul>
  </dd>
</dl>
<!--floatCart-->
<div class="hoverCart">
  <a href="cart">
    <c:if test="${orderNum!=null}">
      ${orderNum}
    </c:if>
    <c:if test="${orderNum==null}">
      0
    </c:if>
  </a>
</div>
<!--fixedNav:footer-->
<div style="height:1.2rem;"></div>
<nav>
  <a href="index" class="homeIcon">首页</a>
  <a href="cate/category" class="categoryIcon">分类</a>
  <a href="cart" class="cartIcon">购物车</a>
  <a href="user" class="userIcon">我的</a>
</nav>
</body>
</html>

