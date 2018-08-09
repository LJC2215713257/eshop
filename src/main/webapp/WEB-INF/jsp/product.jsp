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

    <style>
        .btmNav{
            -webkit-transition: height 0.5s;
            -moz-transition: height 0.5s;
            -ms-transition: height 0.5s;
            -o-transition: height 0.5s;
            transition: height 0.5s;
            box-shadow: 0 0 5px #BC8F8F;
        }

        .cart_list{
            display: none;
            height: 170px;
            overflow: hidden;
            background-color:#fff;
        }
        .cart_list ul{
            margin: 0px auto;
            border:0px;
            padding: 0px auto;
        }

         .cart_list>ul>li{
             -webkit-box-flex: unset;
             box-flex:unset;
             -moz-box-flex: unset;
            list-style: none;
             display:-moz-box; /* Firefox */
             display:-webkit-box; /* Safari and Chrome */
            width: 100%;
            height: 42px;
             line-height: 42px;
            border-bottom: 1px solid #aaaaaa;
         }

        .cart_list>ul>li>span{
            display: block;
            float:left;
        }

         .cart_list>ul>li>span:nth-child(1){

             -webkit-box-flex: 1;
             box-flex:1;
             -moz-box-flex: 1;
             font-size: 20px;
         }

        .cart_list>ul>li>span:nth-child(2):before{
            content: "￥  ";
        }

        .cart_list>ul>li>span:nth-child(2){
            text-align: right;
            -webkit-box-flex: 1;
            box-flex:1;
            -moz-box-flex: 1;
             font-size: 16px;
        }
        .cart_list>ul>li>span:nth-child(3):before{
            content: "×";
        }
        .cart_list>ul>li>span:nth-child(3){
            text-align: right;
            -webkit-box-flex: 1;
            box-flex:1;
            -moz-box-flex: 1;
             font-size: 14px;
        }
    </style>
<script>

$(document).ready(function(){
  //效果测试，程序对接可将其删除
  $(".add_btn").click(function(){
      $.ajax({
          url: "order/addGoods",
          data:{goodsId:$(this).attr("name")},
          method:"post",
          dataType:"json",
          success: $(".cart_icon em").html(parseInt($(".cart_icon em").html())+1)
      });
  });
  

});
function shopCart() {
    $.ajax({
        url: "cart/list",
        method:"post",
        dataType:"json",
        success: flushCart
    });
}

function flushCart(data) {
    console.log(data.entity.orderGoodsList);
    var gds = data.entity.orderGoodsList;
    var btmNav =  $(".btmNav");
    var cart = $(".cart_list");
    if(data.title=="1"){
        var ulstr = "<ul>";
        for(var i=0;i<gds.length;i++){
            ulstr+="<li><span>"+gds[i].goodsName+"</span><span>"
                +gds[i].goodsPayPrice+"</span><span>"+gds[i].goodsNum+"</span></li>"
        }
        ulstr+="</ul>";
        $(cart).html(ulstr);
        console.log(btmNav);
        $(cart).css({
        "display":"block",
        "height":"220"
        })
        $(btmNav).css("height","270");
    }else{
        $(cart).css({
            "display":"block",
            "width":"100%",
            "background-color":"#fff",
            "height":"100"
        });
        $(cart).html("<h1>空空如也</h1>")
        $(btmNav).css("height","150");
    }
}

function turnCart() {
    var btmNav =  $(".btmNav");
    var cart = $(".cart_list");
    if($(cart).css("display")=="block"){
        $(btmNav).css("height","50");
        $(cart).css("display","none");
    }else{
        shopCart();
    }
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
  <li onclick="turnCart()"><a class="cart_icon" ><em  name="gNum">0</em></a></li>
  <li><a name="amout">合计：￥0.00</a></li>
  <li><a href="order/list">立即下单</a></li>
 </ul>
</aside>
</body>
</html>
