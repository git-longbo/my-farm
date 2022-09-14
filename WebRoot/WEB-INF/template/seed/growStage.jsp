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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/imgPosition.css">
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.min.js"  ></script>
     <script type="text/javascript" src="<%=basePath%>ext/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.easyui.min.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.edatagrid.js" ></script>
  
    <script type="text/javascript" src="<%=basePath%>ext/farm/helper.js?346t"  ></script>    
</head>
<body>
<div id="controlBox">
        <a href="#" class="easyui-linkbutton c2" iconCls="icon-add" onclick=" loadAddForm()">添加</a>
        <a href="#" class="easyui-linkbutton c4" iconCls="icon-edit" onclick="loadForm()">编辑</a>
        <a href="#" class="easyui-linkbutton c3" iconCls="icon-remove" onclick="javascript:grid.edatagrid('cancelRow')">取消</a>
        <a href="#" class="easyui-linkbutton c5" iconCls="icon-cancel"onclick="javascript:grid.edatagrid('destroyRow')">删除</a>
</div>
<table id="grid">
</table>
<div id="formGrowContainer" class="easyui-dialog" style="width:750px;height:400px;padding:10px 10px;" closed="true" buttons="#formSeedButtons">
    <form id="formSeedGrow" method="POST" novalidate>
        <table class='tbledit'>
            <tr>
                <td>ID:</td>
                <td><input id="staticId" required="true" name="id"  class="easyui-numberbox" value="0" readonly="true">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           		
                <td>种子ID:</td>
              <td><input name="seedId" required="true" class="easyui-numberbox" value="${seedId}" readonly="true"></td>
            </tr>
            
            <tr>
                <td>生长阶段:</td>
                <td><input id="growState" name="growState" required="true" class="easyui-numberbox">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
          
                <td>生长阶段标题:</td>
                <td><input name="title" required="true" class="easyui-textbox" ></td>
            </tr>
            <tr>
                <td>阶段生长时间:</td>
               <td><input name="stateTime" required="true" class="easyui-numberbox">秒&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
          	
                <td>生虫概率:</td>
                <td><input name="pest" required="true" class="easyui-numberbox" data-options="precision:2"></td>
            </tr>
            <tr>
                <td>图片宽度:</td>
                <td><input id="imgWidth" name="imgWidth" required="true" class="easyui-numberbox">px&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           		
                <td>图片高度:</td>
                <td><input id="imgHeight" name="imgHeight" required="true" class="easyui-numberbox" >px</td>
            </tr>
            <tr>
                <td>图片offsetX:</td>
                <td><input id="offsetX" name="offsetX" required="true" class="easyui-numberbox">px&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           	
                <td>图片offsetY:</td>
                <td><input id="offsetY" name="offsetY" required="true" class="easyui-numberbox">px</td>
            </tr>
            <tr>
                <td>作物状态:</td>
                <td><input name="state" required="true" class="easyui-combobox" panelHeight="auto" 
                data-options="editable:false,
                                    valueField:'code',
                                    textField:'state',
                                    url:'<%=basePath%>state/getState'">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           	
                <td></td>
                <td><a id="btn" href="#" class="easyui-linkbutton" onclick="doEditPosition()">编辑图片位置</a></td>
            </tr>       
        </table>
    </form>
</div>



<div id="formSeedButtons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveForm()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#formGrowContainer').dialog('close')">取消</a>
</div>

<!-- 图片定位 -->
<div id="positionDialog" class="easyui-dialog" style="width:240px;height:420px;padding:10px 10px" closed="true" buttons="#positionDialogButtons">

     <div id="tools-imagePositioner-display" class="tools-imagePositioner-display">
    
       <img id="targetImg" class="easyui-draggable easyui-resizable" data-options="onDrag:imagePositioneronDrag" src="">
  </div>
</div>

<div id="positionDialogButtons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="gainPostion()">确定</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#positionDialog').dialog('close')">取消</a>
</div>  


<script>
var params = {
        id: '',
        mode: 'insert'
    };
var grid;
$(document).ready(function () {
	var listurl = '<%=basePath%>seedState/getSeedStates';
	var list2 = listurl+'?seedId='+"${seedId}";
    grid = $('#grid').edatagrid({
        title: '成长阶段定义',
        width:850,
        height:450,
        method:'post',
        url:list2,      
        saveUrl: '<%=basePath%>seedState/save',
        updateUrl: '<%=basePath%>seedState/save',
        destroyUrl: '<%=basePath%>seedState/delete',    
        striped:true,
        idField:'id',
        rownumbers:true,
        nowrap: false,
        fitColumns: true,
        remoteSort:true,
        pagination:true,  
        pageSize: 8,
        pageList: [4, 8, 10],
        
        columns: [[
       	 {field: 'id', title: 'ID', width: 50, halign:'center',align: 'center',sortable: true },
       	 {field: 'seedId', title: '种子ID', width: 50, align: 'center', halign: 'center'},
             
         {field: 'growState',title: '生长阶段',width: 80,halign:'center',align: 'center',sortable: true,
       		editor:{type:"numberbox",options:{required:true}}
       	 },      
         {field: 'title',title: '生长阶段标题',width: 90,halign:'center',align: 'center',sortable: true,
        	 editor:{type:"validatebox",options:{ validType:'length[0,10]',invalidMessage:'有效长度0-10',required:true}
       	 		}
          },
          {field: 'stateTime',title: '阶段生长时间',width: 100,halign:'center',align: 'center',sortable: true,
           formatter:function(value,row,index){ return value+"秒";},
           editor:{type:"numberbox",options:{required:true}}
          },  
          {field: 'pest',title:"生虫概率", width: 80, halign:'center',align: 'center',sortable: true, 
           editor:{type:'numberbox',options:{precision: 2,required:true}}   
           },
          {field: 'imgWidth',title: '宽度',width: 60,halign:'center',align: 'center',sortable: true,
           editor:{type:"numberbox",options:{required:true}}
            },
          {field: 'imgHeight',title: '高度',width: 60,halign:'center',align: 'center',sortable: true,
           editor:{type:"numberbox",options:{required:true}}
            },
          {field: 'offsetX',title: 'offsetX',width: 70,halign:'center',align: 'center',sortable: true, 
           editor:{type:"numberbox",options:{required:true}}
            },
          {field: 'offsetY',title: 'offsetY',width: 70,halign:'center',align: 'center',sortable: true,
           editor:{type:"numberbox",options:{required:true}}
            },
          {field: 'state',title: '作物状态',width: 100,halign:'center',align: 'center',sortable: true,
            	   formatter:function(value,row,index){              		
             		  var obj = $.ajax({
             				url : '<%=basePath%>state/getStateByCode',
             				type : "post",
             				traditional: true,
             				async : false,
             				data:{"code":value}	
             			});	
             		  return  obj.responseText;
             		 },
            	   editor:{
                       type:'combobox',
                       options:{
                           valueField:'code',
                           textField:'state',
                           url:'<%=basePath%>state/getState',
                           panelHeight:'auto',
                           required:true
                       }
               		 }   
                },        
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
	grid.edatagrid("getPager").pagination({
		beforePageText: '第',
        afterPageText:'页，共{pages}页',
        displayMsg:'当前  {from} 到 {to}  ,共{total}条记录',
		
	});
});    
function loadForm() {
    var row = grid.datagrid('getSelected');
    if (row) {
        params.id = row.id;
        params.mode = 'edit';
        $('#formGrowContainer').dialog('open').dialog('setTitle', '修改数据');
        $('#formSeedGrow').form('load', row);
    } else {
    	  alert("请先选择一行数据，然后再尝试点击操作按钮！");
    }
}
function loadAddForm() {
	params.id = 0;
	params.mode = 'edit';
	
	$('#formGrowContainer').dialog('open').dialog('setTitle', '添加数据');
	$('#formSeedGrow').form('reset');	
}
function saveForm() {
    $('#formSeedGrow').form('submit', {
        url: '<%=basePath%>seedState/save',
        onSubmit: function (param) {
		return $(this).form('validate');
        },
    success: function (result) {
        	 	var result = eval('(' + result + ')');
             	if (result.code == 0) {
            		$('#formSeedGrow').form('reset');
                	$('#formGrowContainer').dialog('close');
                	grid.datagrid('reload');
            	}
            //右下角的消息提示框
     $.messager.show({
            title: "消息",
            msg: result.msg
        });
      }
    });
}
function doEditPosition(){
	var state = $("#growState").val();
	var src = "";
	if(state == "" || state==null){
		alter("请输入生长阶段");
	}else{
		if(state==0){src = "<%=basePath%>images/crops/basic/0.png";}
		else if(state==9){src = "<%=basePath%>images/crops/basic/9.png";}
		else{
			var l1 = "<%=basePath%>images/crops/";
			src = l1 + "${seedId}" +"/" + state + ".png";
		}
	}
	var width = $("#imgWidth").val();
	var height = $("#imgHeight").val();
	var ofX = $("#offsetX").val();
	var ofY = $("#offsetY").val();
	$("#targetImg").css("position","absolute");
	$("#targetImg").css("left",ofX+"px");
	$("#targetImg").css("top",ofY+"px");
	$("#targetImg").css("width",width);
	$("#targetImg").css("height",height);
	
	$("#targetImg").attr("src",src);
	$('#positionDialog').dialog('open').dialog('setTitle', '定位编辑器');
	
	
}

function gainPostion(){
	var width = $("#targetImg").width();
	var height = $("#targetImg").height();
	var offsetX = $("#targetImg").position().left;
	var offsetY = $("#targetImg").position().top;
	$("#imgWidth").numberbox('setValue',width);
	$("#imgHeight").numberbox('setValue',height);
	$("#offsetX").numberbox('setValue',offsetX);
	$("#offsetY").numberbox('setValue',offsetY);
	$("#positionDialog").dialog('close');
}
function imagePositioneronDrag(e){
	var d = e.data;
	if (d.left < 0){d.left = 0}
	if (d.top < 0){d.top = 0}
	if (d.left + $(d.target).outerWidth() > $(d.parent).width()){
		d.left = $(d.parent).width() - $(d.target).outerWidth();
	}
	if (d.top + $(d.target).outerHeight()/2 > $(d.parent).height()){
		d.top = $(d.parent).height() - $(d.target).outerHeight()/2;
	}
}
</script>
</body>
</html>