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
 
    	html{
    		height:100%;
		
    	}
    	.bt{
    	background-color:#ADFF2F;
    	}
     	.hlong *{
		display:inline-block;
		vertical-align:middle;
    	}
    	
	</style>
</head>
<body>

<div id="main" class="easyui-dialog" style="width:950px;height:558px;padding:10px 10px;" title="" buttons="#closeBottons" >
	<div id="controlBox">
	        <span style="color:white;">用户名:</span>
	        <input id="nameSearch"  class="easyui-textbox" name="username" panelHeight="auto">
	        <a href="#" class="easyui-linkbutton c1" iconCls="icon-search" onclick="doSearch()">查询</a>
	        <a href="#" class="easyui-linkbutton c2" iconCls="icon-add" onclick="javascript:grid.edatagrid('addRow')">添加</a>
	        <a href="#" class="easyui-linkbutton c3" iconCls="icon-remove" onclick="javascript:grid.edatagrid('cancelRow')">取消</a>
	        <a href="#" class="easyui-linkbutton c5" iconCls="icon-cancel"onclick="javascript:grid.edatagrid('destroyRow')">删除</a>
	</div>
<table id="grid">
</table>
</div>
<div id="closeBottons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#main').dialog('close');$('#fileUploadPage').dialog('close');">关闭窗口</a>
</div> 
<div id="fileUploadPage" class="easyui-dialog" style="width:600px;height:130px;padding:10px 10px" closed="true" buttons="#fileUploadButtons">
    <form id="formFileUpload" method="post" enctype="multipart/form-data">
    	头像上传：<input class="easyui-filebox" id="fb" name="filePathName" style="width:400px"/>
    </form>
</div>
<div id="fileUploadButtons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="upload()">开始上传</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#fileUploadPage').dialog('close')">关闭窗口</a>
</div>
<script>
$('#fb').filebox({
    buttonText: '选择文件',
})
var params = {
        id: '',
        mode: 'insert'
    };
var grid;
$(document).ready(function () {
	var listUrl = "<%=basePath%>user/getUsers";
	
    grid = $('#grid').edatagrid({
        title: '用户清单',
       	width:900,
       	height:450,
    
        method:'post',
        url:listUrl,      
        saveUrl: '<%=basePath%>user/save',
        updateUrl: '<%=basePath%>user/save',
        destroyUrl: '<%=basePath%>user/delete',  
        nowrap: false,
        fitColumns:true,
        striped:true,
        idField:'id',
        rownumbers:true,
        remoteSort:true,    
        pagination:true,  
        pageSize: 5,
        pageList: [5, 10],

       	columns: [[
       	 {field: 'id', title: 'ID', width: 50, halign:'center',align: 'center',sortable: true },
       
       	 { field: 'headImg', title: '头像', width: 80, align: 'center',  halign: 'center',
       		editor: {
                type: 'validatebox',
                options: {
                    validType: 'length[1,50]',
                    invalidMessage: '有效长度1-50',
                    required: true
                }
            },
           formatter: function (value, row, index) {
              return '<img src="<%=basePath%>/images/headImages/' +
						value + '" width="50px">';
			}},
         {field: 'username',title: '用户名',width: 70,halign:'center',align: 'center',sortable: true,
       		 editor:{
       			 type:"validatebox",
       			 options:{ 
       			 	validType:'length[0,50]',                         
                    	invalidMessage:'有效长度0-50',
                    	required:true
                    	}
       	 		}
       	 },
       	{field: 'nicheng', title: '昵称', width: 70, halign:'center',align: 'center',sortable: true,
       		 editor:{
       			 type:"validatebox",
       			 options:{validType:'length[0,20]',invalidMessage:'有效长度0-20',required:true}
       	 			}},
       	{field: 'exp', title: '经验值', width: 80, halign:'center',align: 'center',sortable: true,
       		editor:{
       			type:"numberbox", 
       			options: {required: true}
       		},
       	  	formatter: function (value, row, index) {
       	  		var exp = '<div class="hlong"><img src="<%=basePath%>images/exp.png" width="30px"/>';
       	  		var val =  value +'</div>';
              return exp+val;
              
       	  	}},
       	{field: 'points', title: '积分', width: 70, halign:'center',align: 'center',sortable: true,
       		editor:{type:"numberbox",
       			options: {required: true}
       		},
       	 	formatter: function (value, row, index) {
       	 		var points = '<div class="hlong"><img src="<%=basePath%>images/points.png" width="30px"/>' + value +'</div>';
         		return points;
				}},
       	{field: 'coins', title: '金币', width: 70, halign:'center',align: 'center',sortable: true,
       		editor:{type:"numberbox",
       			options: {required: true}
       			},
       		formatter: function (value, row, index) {
       			var re = '<div class="hlong"><img src="<%=basePath%>images/coins.png" width="30px"/>' + value +'</div>';
             	return re;
				}},
       	{field: 'op',title: '操作',width: 100,align: 'center',halign: 'center',
			formatter: function (value, row, index){
				var bt1 = '<input class="bt" type="button" onclick="fileUploadPage('+index+')" value="上传头像" />' ;
				var bt2 ='<input class="bt" type="button" onclick="doSave('+index+')" value="保存数据">';
				   return bt1+bt2;                         
				 }
            }
       	]],
    destroyMsg:{
        norecord:{
            title:'警告',
            msg:'首先需要选中记录，然后在点击删除按钮'
        },
        confirm:{
            title:'确认',
            msg:'是否删除选中记录?'
        }
    },
});  

    grid.datagrid("getPager").pagination({
        beforePageText: '第',
        afterPageText:'页，共{pages}页',
        displayMsg:'当前  {from} 到 {to}  ,共{total}条记录',
    });
		

	
});    
function doSearch(){
    grid.datagrid("load",{
        username: $("#nameSearch").val()
    });
}
var ed;
var index;
function fileUploadPage(editIndex){	
	index = editIndex;
	$('#grid').datagrid("beginEdit", editIndex);
	ed = $("#grid").datagrid('getEditor', {index:editIndex,field:'headImg'});
 	$('#fileUploadPage').dialog('open').dialog('setTitle', '上传头像');
}
function upload(){
	$('#formFileUpload').form('submit', {
    	url: '<%=basePath%>file/saveHeadImg',
        success: function (data) {
           	 var data = eval('(' + data + ')');
           	 if (data.code == 0) {
           	 	$(ed.target).val($("#fb").filebox("files")[0].name);
           	 	$('#fileUploadPage').dialog('close');	 	
             }
             $.messager.show({
             	title: "消息",
                msg: data.msg
             });
        }
     });
}
function doSave(index){
	$('#grid').datagrid("endEdit", index);
	 $.messager.show({
      	title: "消息",
         msg: "保存成功"
      });
}
</script>
</body>
</html>