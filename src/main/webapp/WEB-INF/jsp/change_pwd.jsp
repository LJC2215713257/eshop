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
<title>修改密码-合众饰品专卖</title>
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
  //测试返回页面，程序对接删除即可
  $(".userForm input[type='button']").click(function(){
      $.post("user/up_pwd",{old_p:$("#old_p").val(),new_p:$("#new_p").val(),check_p:$("#check_p").val()},function (data) {
          console.log(data);
          switch (data.title){
              case "-1":{
                  console.log($(".d_tip[name=new_p]"));
                  $(".d_tip[name=new_p]").html(data.entity);
                  $(".d_tip[name=check_p]").html(data.entity);
                  break;}
              case "0":{
                  console.log($(".d_tip[name=new_p]"));
                  $(".d_tip[name=old_p]").html(data.entity);
                  setTimeout(function () {
                      location.href="login";
                  },2000);
                  break;}
              case "1":{
                  $(".d_tip[name=old_p]").html(data.entity);
                  location.href="user/user_set";
                  break;}
              case "2":{
                  $(".d_tip[name=old_p]").html(data.entity);
                  break;}
          }
      });
  });
});
</script>
</head>
<body>
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>修改密码</h1>
</header>
<ul class="userForm">
 <li>
 <label class="formName">旧密码：<span class="d_tip" name="old_p"></span></label>
 <input id="old_p" type="password" required placeholder="旧密码..."/>
 </li>
 <li>
 <label class="formName">新密码：<span class="d_tip" name="new_p"></span></label>
 <input id="new_p" type="password" required placeholder="新密码..."/>
 </li>
 <li>
  <label class="formName">确认新密码：<span class="d_tip" name="check_p"></span></label>
 <input id="check_p" type="password" required placeholder="确认新密码..."/>
 </li>
 <li><input type="button" value="确认修改密码" class="formLastBtn"/></li>
</ul>
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
