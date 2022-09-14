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
    
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.draggable.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/locale/easyui-lang-zh_CN.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>ext/farm/helper.js"></script>
    <style type="text/css">
    html{
   	height:100%;
    	}
    #products{
    width:1100px;
    height:440px;
    }
    .seedBox{
	width:250px;
	height:350px;
	float:left;
	text-align:center;
	display:inline-block;	
	background-size:250px 320px;
	background-repeat:no-repeat;
	background-image:url('<%=basePath%>images/back.png');
    }
    .seedInfo{
    width:180px;
    height:80px;
    float:left;
    margin:16px 30px 0px 50px;
    text-align:left;
    }
    .btn{
    width:180px;
    text-align:center;
    margin:20px 26px;
    
    }
    .buy{
    width:100px;
    background-color:#00CED1;
    }
    .buy:active{
    transform: translateY(4px);
    }
    </style>
</head>
<body>
<div id="products" class="easyui-dialog" title="种子仓库" data-options="iconCls:'icon-myicon'">
<table id="tt" style="width:100%;height:420px;"
			 singleSelect="true" fitColumns="true" remoteSort="false"
			url='<%=basePath%>/seed/getSeeds' pagination="true" sortOrder="asc" sortName="seedName">
		<thead>
			<tr>
				<th field="seedName" width="200" sortable="true">种子名称</th>
				<th field="seedLevel" width="200" sortable="true">种子等级</th>
				<th field="seedType" width="200" sortable="true">种子类型</th>
				<th field="ground" width="200" sortable="true">土地需求</th>			
			</tr>
		</thead>
	</table>	

</div>
<script>
var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
	renderRow: function(target, fields, frozen, rowIndex, rowData){	
		if (!frozen){
			var seedBox = $('<div class="seedBox"></div>');	
			var title = $('<div class="seedInfo">'+ rowData.message + '</div>');
			var img = $('<img width="140px" height="200px"/>').prop("src","<%=basePath%>images/crops/"+rowData.seedId+"/5.png");		
			var buttonBuy = $('<div class="btn"><input type="button" class="buy" onclick="buySeed('+rowData.seedId+')" value="我要购买"/></div>');		
			seedBox.append(title).append(img).append(buttonBuy);	
		}
		return seedBox.prop("outerHTML");
	}
});
$('#tt').datagrid({
	pageSize: 4,
	pageList: [4, 5, 6],
	view: cardview,
	
});
$('#tt').datagrid("getPager").pagination({
    beforePageText: '第',
    afterPageText:'页，共{pages}页',
    displayMsg:'当前  {from} 到 {to}  ,共{total}条记录',
});
function buySeed(seedId){
	$.messager.defaults = { ok: "购买", cancel: "取消" };
	$.messager.confirm('种子购买','确认要购买种子?',function(r){
	    if (r){
	    	getRemoteData("<%=basePath%>user/buySeed?seedId="+seedId,function(res){
	    		$.messager.show({
	            	title: "消息",
	            	msg: res.msg
	            });
	    		 parent.document.getElementById("menu").src="menu.jsp";
	    		 parent.document.getElementById("tools").setAttribute("src","<%=basePath%>user/mySeed");
	    	});
	    }
	});
	
}
</script>
</body>
</html>