<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/green/easyui.css?t564">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/farm.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/imgPosition.css?t=0901">
     <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/seedBag.css">
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.draggable.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/locale/easyui-lang-zh_CN.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>ext/farm/helper.js"></script>
  
</head>
<body>
<div class="seedBag">
<botton class="btleft bt2" onclick="lastPage()"><img src="<%=basePath%>images/left.png" width="60px" height="150px"/></botton>
<div class="seeds"></div>
<botton class="btright bt2" onclick="nextPage()"><img src="<%=basePath%>images/right.png" width="60px" height="150px"/></botton>
</div>
<script>
var pageNum;
var totalPages;
$(document).ready(function(){
	getRemoteData("<%=basePath%>user/seedList",function(data){
		show(data);
	});
});
function lastPage(){
	if(pageNum-1>=0){
		getRemoteData("<%=basePath%>user/seedList?pageNum="+(pageNum-1),function(data){
			show(data);
		});
	}	
}
function nextPage(){
	if(pageNum+1 < totalPages){
		getRemoteData("<%=basePath%>user/seedList?pageNum="+(pageNum+1),function(data){
			show(data);
		});
	}
}
function show(data){
	pageNum = data.number;
	totalPages = data.totalPages;
	$(".seeds").empty();
	$(data.content).each(function(index,data){
		var boxSeed = $('<div class="boxSeed"></div>');
		var numNode = $('<div class="num"></div>').append(data.num);
		var imgNode = $('<img width="90px" height="150px"></img>').prop("src","<%=basePath%>images/crops/"+data.seedId+"/5.png")
		boxSeed.append(numNode).append(imgNode);
		$(".seeds").append(boxSeed);
	});
}
</script>
</body>
</html>