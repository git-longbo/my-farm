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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/green/easyui.css?t=34355">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/farm.css">
   
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.min.js"  ></script>
     <script type="text/javascript" src="<%=basePath%>ext/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.edatagrid.js" ></script>
    <script type="text/javascript" src="<%=basePath%>ext/farm/helper.js?346t"  ></script>    
 <style type="text/css">
		*{
		font-size:20px;
		 
		 }
    	html{
    
    		height:100%;
    	}
    	
    	#login{
    	width:500px;
    	height:300px;
    	margin:auto;
    	}
    	.foot{
    	width:500px;
    	height:50px;
    	}
    	.font{
    	width:420px;
    	height:50px;
    	float:left;
    	}
	    .bt{
		width:70px;
	    height:50px;
	    float:right;
	    }
	    .hlong *{
	    display:inline-block;
	    vertical-align:middle;
	    }
	  
    	</style>
</head>
<body>
<div id="login" class="easyui-dialog" title="用户选择" collapsible="false" data-options="iconCls:'icon-us',resizable:true,modal:true">
	<p>当前用户：</p>
	<form id="userForm" method="POST">
		<input name="nicheng" id="nicheng" class="easyui-combobox" data-options="
			url: '<%=basePath%>user/getUserList',
			method: 'get',
			valueField: 'nicheng',
			textField: 'nicheng',
			panelWidth: 500,
			panelHeight: 350,
			formatter: formatItem,
			width:500"/>
	</form>
	<hr>
	<div class="foot"> 
	<div class="font">请在下拉框中选择用户昵称，并点击"确认"按钮设定当前用户信息</div>
	<div class="bt"><a href="#" class="easyui-linkbutton" style="width:60px; height:40px" onclick="selected()">确认</a></div>
	</div>
</div>

    
<script type="text/javascript">
	
	function formatItem(row){
		var s = '<div class="hlong"><img src="<%=basePath%>images/headImages/' + row.headImg + '" width="60px"/>'+
				row.nicheng + " | " +
				'经验：'+ row.exp + " | " +
				'金币：'+ row.coins + " | " + 
				'积分：'+ row.points + '</div>';
		return s;
	}
	
	function selected(){
		var user = {};
		var nicheng = $("#nicheng").val();
		user.nicheng = nicheng;
		request(user,"post","<%=basePath%>user/setCurUser",function(result){ 
				 $.messager.show({
		             	title: "消息",
		                msg: result.msg
		             });
            parent.document.getElementById("menu").src="menu.jsp";
        });
	}
</script>
	
</body>
</html>