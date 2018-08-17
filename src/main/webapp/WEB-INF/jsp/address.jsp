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
<title>我的地址-合众饰品专卖</title>
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
        .userForm>ul>li{
            position: relative;
        }

        .add_tip{
            display: block;
            height: 43px;
            width: auto;
            top:0px;
            right:0px;
            float:right;
            text-align: right;
            line-height: 43px;
            font-size: 14px;
            color:#777;
            margin-top:-43px;
            z-index: 100;
        }

    </style>
<script>
$(document).ready(function(){
  //测试返回页面，程序对接删除即可
  $(".userForm input[type='button']").click(function(){
      if($("#prov").val()!=""){

          if($("#city").val()!=""){

              if($("#area").val()!=""){
                  $.post("area/commitaddr",{provincename:$("#prov").val(),cityname:$("#city").val(),
                      areaname:$("#area").val(),detailaddress:$("textarea").eq(0).val()
                  },function (data) {
                      if(data.title==1){
                          //location.href="user/user_set";
                      }

                  });
              }else{
                  $("#area").siblings("label").css("color","#d93420");
              }
          }else{
              $("#city").siblings("label").css("color","#d93420");
          }
      }else{
          $("#prov").siblings("label").css("color","#d93420");
      }




  });

  $(".userForm li>select").focus(function () {
      var la = $(this).siblings("label");
      la.css("color","#777");
  });

    $("#prov").bind("change",function () {
        if($("#prov").val()!=""){
            $.post("area/arealist",{pname:$("#prov").val(),deep:2},function (data) {
                var opts = new Array();
                for(var i=0;i<data.length;i++){
                    opts.push("<option>"+data[i].areaName+"</option>");
                }
                $("#city").html(opts.join());
                if($("#city").val()!=""){
                    $.post("area/arealist",{pname:$("#city").val(),deep:3},function (data) {
                        var opts = new Array();
                        for(var i=0;i<data.length;i++){
                            opts.push("<option>"+data[i].areaName+"</option>");
                        }
                        $("#area").html(opts.join(""));
                    });
                }
            });
        }
    });

    $("#city").bind("change",function () {
        if($("#city").val()!=""){
            $.post("area/arealist",{pname:$("#city").val(),deep:3},function (data) {
                var opts = new Array();
                for(var i=0;i<data.length;i++){
                    opts.push("<option>"+data[i].areaName+"</option>");
                }
                $("#area").html(opts.join(""));
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
 <h1>我的地址</h1>
</header>
<ul class="userForm">
 <li>
  <input id="add_tip" placeholder="备注" type="text" value="${memberid}"/>
 </li>
 <li>
  <select id="prov" autocomplete="off">
   <c:forEach items="${pros}" var="p">
     <c:if test="${addr.provincename==p.areaName&&addr.provincename!=null}">
      <option selected="selected">
              ${p.areaName}
      </option>
     </c:if>
    <c:if test="${provincename!=p.areaName}">
     <option>
       ${p.areaName}
     </option>
    </c:if>
   </c:forEach>
  </select>
     <label class="add_tip">省份</label>
 </li>
 <li>
  <select id="city">
   <option>${addr.cityname}</option>
  </select>
     <label class="add_tip">城市</label>
 </li>
 <li>
  <select id="area">
   <option>${addr.areaname}</option>
  </select>
     <label class="add_tip">区</label>
 </li>
 <li>
  <textarea id="#detail" placeholder="请填写地址的详细信息！">${addr.detailaddress}</textarea>
 </li>
 <li>
  <input type="button" value="修改地址" class="formLastBtn"/>
 </li>
</ul>
</body>
</html>
