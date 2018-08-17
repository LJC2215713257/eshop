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
<title>搜索-合众饰品专卖</title>
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
  $(".searchHistory dd:last a").click(function(){
	  var clear=confirm("确定清除搜索记录吗?");
	  if(clear==true){
		  $(this).parents(".searchHistory").find("dd").remove();
      }
  });

    //飞入动画，具体根据实际情况调整
    $(".addToCart").click(function(){
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
//点击按钮提交
$(function(){
    $("#sub").click(function(){
        $("#f").submit(); //普通提交

    })
})
</script>

</head>
<body>
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>搜索</h1>
</header>
<form id="f" action="goods/infobygoodsname" method="post">
<aside class="searchArea">
 <input type="text" name="gname" placeholder="查找玻璃品..."/>
 <input id="sub" type="button" value="&#63;" class="searchBtn"/>
</aside>
</form>

<!--Tab:productList-->
<dl class="tab_proList">
    <dd>
        <ul>
            <c:forEach items="${glist}" var="goods">
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
                            <a class="addToCart">&#126;</a>
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
    <a href="cart.jsp">0</a>
</div>

<dl class="searchHistory">
 <dt>历史搜索</dt>
 <dd>
  <ul>
    <c:forEach items="${slist}" var="s">
        <li><a href="goods/infobygoodsname?gname=${s.searchKey}">${s.searchKey}</a></li>
    </c:forEach>
  </ul>
 </dd>
 <dd>
  <a>清空历史记录</a>
 </dd>
</dl>
</body>
</html>
