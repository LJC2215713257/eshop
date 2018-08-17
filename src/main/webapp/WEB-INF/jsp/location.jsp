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
<title>位置-合众饰品专卖</title>
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
 <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=aGOi6ccM9oGUUENpGqXAZvfqC9obVzhC"></script>
 <script src="../../js/jquery.js"></script>
 <script>
     var zimu="";
  var geolocation = new BMap.Geolocation();
  geolocation.getCurrentPosition(function (r) {
      if(this.getStatus() == BMAP_STATUS_SUCCESS){
          $(".location_auto>span").html(r.address.city);
          $.post("area/updateLocation",{location:r.address.city});
      }
  });
  $(document).ready(function () {
      $(".cityLi[name=zimu]>dd>a").click(function () {
          zimu=$(this).html();
          $.post("area/splist",{zimu:$(this).html()},function (data) {
              var container = $("#citylist");
              var ht = "<dt>"+zimu+"</dt>";
              var strs = new Array();
              for(var i=0;i<data.length;i++){
                  strs.push("<dd><a>"+data[i]+"</a></dd>");
              }
              $(container).html(ht+strs.join(""));
          });
      });

      $(".cityLi[name=list] dd a").click(function () {
          $.post("area/updateLocation",{location:$(this).html()},function (data) {
              location.href="index";
          });
      });
  });
 </script>
</head>
<body>
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>我的位置</h1>
</header>
<div class="location_auto">定位城市：<span>自动定位...</span></div>
<dl class="cityLi" name="list">
 <dt>热门城市</dt>
    <c:forEach items="${hots}" var="h">
        <dd><a>${h}</a></dd>
    </c:forEach>
 <%--<dd><a>西安</a></dd>--%>
 <%--<dd><a>杭州</a></dd>--%>
 <%--<dd><a>广州</a></dd>--%>
 <%--<dd><a>北京</a></dd>--%>
 <%--<dd><a>上海</a></dd>--%>
 <%--<dd><a>南京</a></dd>--%>
</dl>
<dl class="cityLi" name="zimu">
    <dt>首字母</dt>
    <dd><a>A</a></dd>
    <dd><a>B</a></dd>
    <dd><a>C</a></dd>
    <dd><a>D</a></dd>
    <dd><a>E</a></dd>
    <dd><a>F</a></dd>
    <dd><a>G</a></dd>
    <dd><a>H</a></dd>
    <dd><a>I</a></dd>
    <dd><a>J</a></dd>
    <dd><a>L</a></dd>
    <dd><a>M</a></dd>
    <dd><a>N</a></dd>
    <dd><a>O</a></dd>
    <dd><a>P</a></dd>
    <dd><a>R</a></dd>
    <dd><a>S</a></dd>
    <dd><a>T</a></dd>
    <dd><a>U</a></dd>
    <dd><a>V</a></dd>
    <dd><a>W</a></dd>
    <dd><a>S</a></dd>
    <dd><a>Y</a></dd>
    <dd><a>Z</a></dd>
</dl>
<dl class="cityLi" name="list" id="citylist">
</dl>
</body>
</html>
