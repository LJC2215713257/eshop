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
<title>登录-合众饰品专卖</title>
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
  .wx_sty{
   background-image: url("../../images/icon/wxlogo.png");
   background-repeat: no-repeat;
   background-size:auto 100%;
   display: inline-block;
   height: 33px;
   width:60px;
   margin-left: 30px;
  }
 </style>
<script>
$(document).ready(function(){
   $(".formarea li:last input[type='button']").click(function(){
       if($("input[name=tel]").val()==""||$("input[name=psw]").val()==""){
           $("#tip").html("账号密码不能为空！");
       }else {
           $.post("user/login",
               {tel:$("input[name=tel]").val(), psw: $("input[name=psw]").val()}
               , function (data) {
                   if (data.title == "1") {
                       location.href="/index";
                   } else if (data.title == "0") {
                       $("#tip").html("账号密码错误！");
                   }
               });
       }
   });
   $(".wx_sty").click(function () {
       location.href="wxlogin";
   });
});
</script>
</head>
<body>
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>登录</h1>
</header>
<ul class="formarea">
 <li>
  <label class="lit">账号：</label>
  <input name="tel" type="text" placeholder="手机号码" class="textbox"/>
 </li>
 <li>
  <label class="lit">密码：</label>
  <input name="psw" type="password" placeholder="登陆密码" class="textbox"/>
 </li>
 <li class="liLink">
  <a href="register" class="fl">新用户注册</a>
  <span class="wx_sty"></span>
  <a href="find_pwd" class="fr">忘记密码?</a>
 </li>
 <li style="color:#FF6347;font-size: 16px; background-color:transparent;margin-bottom: 30px;">
  <label id="tip"></label>
 </li>
 <li>
  <input type="button" value="立即登录"/>
 </li>
</ul>
<!--fixedNav:footer-->
<div style="height:1.2rem;"></div>
<nav>
 <a href="index" class="homeIcon">首页</a>
 <a href="cate/category" class="categoryIcon">分类</a>
 <a href="cart" class="cartIcon">购物车</a>
 <a href="javascript:void(0);" class="userIcon">我的</a>
</nav>
</body>
</html>
