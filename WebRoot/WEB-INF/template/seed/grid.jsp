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
    	</style>
</head>
<body>
<div id="controlBox">
        <span style="color:white;">种子名称:</span>
        <input id="nameSearch"  class="easyui-textbox" name="seedName" panelHeight="auto">
        <a href="#" class="easyui-linkbutton c1" iconCls="icon-search" onclick="doSearch()">查询</a>
        <a href="#" class="easyui-linkbutton c2" iconCls="icon-add" onclick=" loadAddForm()">添加</a>
        <a href="#" class="easyui-linkbutton c4" iconCls="icon-edit" onclick="loadForm()">编辑</a>
        <a href="#" class="easyui-linkbutton c3" iconCls="icon-remove" onclick="javascript:grid.edatagrid('cancelRow')">取消</a>
        <a href="#" class="easyui-linkbutton c5" iconCls="icon-cancel"onclick="javascript:grid.edatagrid('destroyRow')">删除</a>
</div>
<table id="grid">
</table>
<div id="formSeedContainer" class="easyui-dialog" style="width:850px;height:400px;padding:10px 10px;" closed="true" buttons="#formSeedButtons">
    <form id="formSeed" method="POST" novalidate>
        <table class='tbledit'>
            <tr>
                <td>ID:</td>
                <td><input id="staticId" required="true" name="id"  class="easyui-numberbox" value="0" readonly="true">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           		
                <td>种子ID:</td>
              <td><input name="seedId" required="true" class="easyui-numberbox"></td>
            </tr>
            
            <tr>
                <td>种子名称:</td>
                <td><input name="seedName" required="true" class="easyui-textbox">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           	
                <td>X季作物:</td>
                <td><input name="seasons" required="true" class="easyui-numberbox" ></td>
            </tr>
            <tr>
                <td>种子等级:</td>
               <td><input name="seedLevel" required="true" class="easyui-numberbox" >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
          	
                <td>种子类型:</td>
                <td><input name="seedType" required="true" class="easyui-combobox" panelHeight="auto"
                data-options="editable:false,
                                    valueField:'code',
                                    textField:'typeName',
                                    url:'<%=basePath%>seedType/getSeedType'" ></td>
            </tr>
            <tr>
                <td>可获经验:</td>
                <td><input name="experience" required="true" class="easyui-numberbox">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           		
                <td>每季成熟所需时间:</td>
                <td><input name="growTime" required="true" class="easyui-numberbox" >秒</td>
            </tr>
            <tr>
                <td>每季成熟可获收成:</td>
                <td><input name="receiveNum" required="true" class="easyui-numberbox">个果实&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           	
                <td>种子采购价:</td>
                <td><input name="price" required="true" class="easyui-numberbox">个金币</td>
            </tr>
            <tr>
                <td>每个收获的果实单价:</td>
                <td><input name="eachPrice" required="true" class="easyui-numberbox">个金币&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
           	
                <td>土地需求:</td>
                <td><input name="ground" required="true" class="easyui-combobox" panelHeight="auto" 
                data-options="editable:false,
                                    valueField:'code',
                                    textField:'groundName',
                                    url:'<%=basePath%>ground/getGroundType'" ></td>
            </tr>
            <tr>
                <td>每季成熟可获积分:</td>
                <td><input name="credit" required="true" class="easyui-numberbox">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
              
                <td>提示信息:</td>
                <td><input name="message" required="true" class="easyui-textbox"></td>
            </tr>
            
        </table>
    </form>
</div>
<div id="formSeedButtons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveForm()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#formSeedContainer').dialog('close')">取消</a>
</div>
<div id="growEdit" class="easyui-dialog"  closed="true">
	<iframe id="seedStages" src="" style="width:860px;height:500px;padding:10px 10px;"></iframe>
</div>
<script>
var params = {
        id: '',
        mode: 'insert'
    };
var grid;
$(document).ready(function () {
	var listUrl = "<%=basePath%>seed/getSeeds";
    grid = $('#grid').edatagrid({
        title: '种子清单',
        width:'100%',
        height: window.screen.height-300,
        method:'post',
        url:listUrl,      
        saveUrl: '<%=basePath%>seed/save',
        updateUrl: '<%=basePath%>seed/save',
        destroyUrl: '<%=basePath%>seed/delete',  
        nowrap: false,
        fitColumns:true,
        striped:true,
        idField:'id',
        rownumbers:true,
        remoteSort:true,
       
        pagination:true,  
        pageSize: 4,
        pageList: [2, 4, 6, 8],
        
        frozenColumns: [[
       	 {field: 'id', title: 'ID', width: 50, halign:'center',align: 'center',sortable: true },
       	 {field: 'seedId', title: '种子ID', width: 50, align: 'center', halign: 'center'},
             
         {field: 'seedName',title: '种子名称',width: 70,halign:'center',align: 'center',sortable: true,
       		 editor:{
       			 type:"validatebox",
       			 options:{ 
       			 	validType:'length[0,50]',                         
                    	invalidMessage:'有效长度0-50',
                    	required:true
                    	}
       	 		}
       	 }
       	]],
       	
       columns: [[           
          {field: 'seasons',title: 'X季作物',width: 100,halign:'center',align: 'center',sortable: true,
        	  formatter:function(value,row,index){         
                      return value+"季作物";
              },
              editor:{type:"numberbox",options:{required:true}}
          },
          {field: 'seedLevel',title: '种子等级',width: 100,halign:'center',align: 'center',sortable: true,
        	  formatter:function(value,row,index){         
                  return value+"级作物";     
          },
          editor:{type:"numberbox",options:{required:true}}
          },
       	
       	  
          {field: 'seedType',title:"种子类型", width: 80, halign:'center',align: 'center',sortable: true, 
        	  formatter:function(value,row,index){              		
        		  var obj = $.ajax({
        				url : '<%=basePath%>seedType/getSeedTypeByCode',
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
                  textField:'typeName',
                  url:'<%=basePath%>seedType/getSeedType',
                  panelHeight:'auto',
                  required:true
              }
      		 }   
           },
           
           {field: 'experience',title: '可获经验',width: 100,halign:'center',align: 'center',sortable: true,
        	   editor:{type:"numberbox",options:{required:true}}
           },
           {field: 'growTime',title: '每季成熟所需时间',width: 130,halign:'center',align: 'center',sortable: true,
        	   formatter:function(value,row,index){         
                   return value+"秒";
           	},
           editor:{type:"numberbox",options:{required:true}}
            },
            {field: 'receiveNum',title: '每季成熟可获收成',width: 130,halign:'center',align: 'center',sortable: true,
            	  editor:{type:"numberbox",options:{required:true}}
             },
             {field: 'price',title: '种子采购价',width: 100,halign:'center',align: 'center',sortable: true,
            	 formatter:function(value,row,index){         
                     return value+"金币";   
             		},
             editor:{type:"numberbox",options:{required:true}}
              },
              {field: 'eachPrice',title: '每个收获的果实单价',width: 130,halign:'center',align: 'center',sortable: true,
            	  formatter:function(value,row,index){         
                      return value+"金币";
                 
              },
              editor:{type:"numberbox",options:{required:true}}
               },
               {field: 'ground',title: '土地需求',width: 100,halign:'center',align: 'center',sortable: true,
            	   formatter:function(value,row,index){              		
             		  var obj = $.ajax({
             				url : '<%=basePath%>ground/getGroundTypeByCode',
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
                           textField:'groundName',
                           url:'<%=basePath%>ground/getGroundType',
                           panelHeight:'auto',
                           required:true
                       }
               		 }   
                },
                {field: 'credit',title: '每季成熟可获积分',width: 130,halign:'center',align: 'center',sortable: true,
                	 formatter:function(value,row,index){         
                         return value+"分";          
                 },
                 editor:{type:"numberbox",options:{required:true}}
                 },
                 {field: 'message',title: '提示信息',width: 100,halign:'center',align: 'center',sortable: true,
                	  editor:{type:'validatebox',options: { required: true }}
                  },
                 {field:'operate',title:'操作',align:'center',width:100,
                		formatter:function(value, row, index){ 	
                			var f = "openNewPage("+ row.seedId+")";
                			var botton='<input class="bt" type="button" value="成长阶" onclick='+f+' />' ;
                			return botton;
                			}}
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
function doSearch(){
    grid.datagrid("load",{
        seedName: $("#nameSearch").val()
    });
}
function loadForm() {
    var row = grid.datagrid('getSelected');
    if (row) {
        params.id = row.id;
        params.mode = 'edit';
        $('#formSeedContainer').dialog('open').dialog('setTitle', '修改数据');
        $('#formSeed').form('load', row);
    } else {
    	  alert("请先选择一行数据，然后再尝试点击操作按钮！");
    }
}
function loadAddForm() {
	params.id = 0;
	params.mode = 'edit';
	$('#formSeed').form('reset');
	$('#formSeedContainer').dialog('open').dialog('setTitle', '添加数据');
		
}
function saveForm() {
    $('#formSeed').form('submit', {
        url: '<%=basePath%>seed/save',
        onSubmit: function (param) {
		return $(this).form('validate');
        },
    success: function (result) {
        	 	var result = eval('(' + result + ')');
             	if (result.code == 0) {
            		$('#formSeed').form('reset');
                	$('#formSeedContainer').dialog('close');
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
function openNewPage(seedId){
	params.id = 0;
	params.mode = 'edit';
	$('#growEdit').dialog('open').dialog('setTitle', '编辑成长阶段');
	
	var listurl = "<%=basePath%>seed/turnToStage";
	var listurl2 = listurl +"?seedId="+seedId;
	$("#seedStages").attr('src',listurl2);
	
}

</script>
</body>
</html>