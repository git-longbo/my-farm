<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String wsBasePath = "ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
     <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/land.css">
        <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/seedBag.css">
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.draggable.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/locale/easyui-lang-zh_CN.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>ext/farm/helper.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/farm/imgPosition.js"></script>
    <style type="text/css">
    	
    	</style>
</head>
<body>
<div class="container">
<div class="line1"></div>
<div class="line2"></div>
<div class="line3"></div>
<div class="line4"></div>
</div>
<div id="Bag" class="easyui-dialog"  closed="true">
	<div class="seedBag">
		<botton class="btleft bt2" onclick="lastPage()"><img src="<%=basePath%>images/left.png" width="60px" height="150px"/></botton>
		<div class="seeds"></div>
		<botton class="btright bt2" onclick="nextPage()"><img src="<%=basePath%>images/right.png" width="60px" height="150px"/></botton>
	</div>
</div>
<audio id="sound_harvest" src="<%=basePath%>sound/harvest.mp3"></audio>
<audio id="sound_cleanLand" src="<%=basePath%>sound/cleanLand.mp3"></audio>
<audio id="sound_plant" src="<%=basePath%>sound/plant.mp3"></audio>
<audio id="sound_worm" src="<%=basePath%>sound/worm.mp3"></audio>
<audio id="sound_hasPlant" src="<%=basePath%>sound/hasPlanted.mp3"></audio>
<audio id="sound_killPest" src="<%=basePath%>sound/killWorm.mp3"></audio>
<script>
	initLand();//初始化土地
	initGroundContent();//初始化每块地的作物
	initWebSocket();
	const lands = document.querySelectorAll(".land");
	const crops = document.querySelectorAll(".crop");
	const pests = document.querySelectorAll(".pest");
	var pageNum;
	var totalPages;
	var landId;
	

	for(let i=0;i<lands.length;i++){
		 lands[i].onmouseover = function (){
			 getRemoteData("<%=basePath%>ground/check?groundId="+i,function(res){	
				 if(res=="" || res.groundStatus==0 ){
						//改变cur
						 $('.land').css("cursor","url(<%=basePath%>images/cur/plant.png),default");
					 }
				 if(res.sStatus==5){
					//改变cur
					 if(res.hasInsect==1){
						 $('.land').css("cursor","url(<%=basePath%>images/cur/pest.png),default");
					 }else{
						 $('.land').css("cursor","url(<%=basePath%>images/cur/receive.png),default");
					 }				 
					}
				 else if(res.sStatus==9){
					//改变cur
					 $('.land').css("cursor","url(<%=basePath%>images/cur/clean.png),default");
					}
				 else if(res.sStatus>=0 && res.sStatus<5){
					 if(res.hasInsect==1){
						 $('.land').css("cursor","url(<%=basePath%>images/cur/pest.png),default");
					 }else{
					 $('.land').css("cursor","url(<%=basePath%>images/cur/alarm.png),default");
					 }
				 }
				
				})
		
	        }

		  lands[i].onclick=function(){
			  landId = i;
				getRemoteData("<%=basePath%>ground/check?groundId="+i,function(res){	
					if(res.sStatus==5){
						if(res.hasInsect==1){
							$("#sound_killPest")[0].play();
							killPest(i);
						}else{
							$("#sound_harvest")[0].play();
							receive(i);
						}
						
					}
					if(res.sStatus<5&&res.sStatus>0){
						if(res.hasInsect==1){
							$("#sound_killPest")[0].play();
							killPest(i);
						}
					}
					if(res.sStatus==9){
						$("#sound_cleanLand")[0].play();
						cleanGlass(i);
					}
					if(res=="" || res.groundStatus==0 ){
						$("#sound_plant")[0].play();
						 $('#Bag').dialog('open').dialog('setTitle', '种子收纳袋');
						  getRemoteData("<%=basePath%>user/seedList",function(data){
								show(data);
							});
					}
				})

				
		  }
	}
	window.close = function () {
	    websocket.onclose();
	}
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
	//种子仓库
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
			$('.boxSeed')[index].onclick = function(){
				$("#sound_hasPlant")[0].play();
				plant(landId,data.seedId);
				 $('#Bag').dialog('close');
			}
		});
	}
	//土地初始化,获取数据
	function initGroundContent(){
		getRemoteData("<%=basePath%>ground/data",function(res){	
			 initGroundSeed(res);
		})
	}
	//开始初始化土地
	function initLand(){
		for(let j=0;j<6;j++){
			var land=$('<div class="land yellow"></div>');
			var crop=$('<div class="crop"></div>');
			var pest=$('<div class="pest"></div>');
			land.append(crop).append(pest);
			$(".line1").append(land);
		}
		for(let j=0;j<6;j++){
			var land=$('<div class="land gold"></div>');
			var crop=$('<div class="crop"></div>');
			var pest=$('<div class="pest"></div>');
			land.append(crop).append(pest);
			$(".line2").append(land);
		}
		for(let j=0;j<6;j++){
			var land=$('<div class="land green"></div>');	
			var crop=$('<div class="crop"></div>');
			var pest=$('<div class="pest"></div>');
			land.append(crop).append(pest);
			$(".line3").append(land);
		}
		for(let j=0;j<6;j++){
			var land=$('<div class="land black"></div>');
			var crop=$('<div class="crop"></div>');
			var pest=$('<div class="pest"></div>');
			land.append(crop).append(pest);
			
			$(".line4").append(land);
		}
	}
	//初始化土地种子信息
	function initGroundSeed(data){
		for(let i=0;i<data.length;i++){
			
			if(data[i].groundStatus != 0 && data[i].sStatus!=-1){
				//初始化种子状态
				if(data[i].sStatus == 0){
					crops[data[i].groundId].style.backgroundImage = "url(<%=basePath%>images/crops/basic/0.png)";			
				}else if(data[i].sStatus == 9){
					
					crops[data[i].groundId].style.backgroundImage = "url(<%=basePath%>images/crops/basic/9.png)";	
				}else{
					var titleText = "生长阶段："+data[i].sStatus+"\n"+"当前季度："+data[i].nowSeason+"\n";
					crops[data[i].groundId].setAttribute("title",titleText);
					crops[data[i].groundId].style.backgroundImage = "url(<%=basePath%>images/crops/"+data[i].sId+"/"+(data[i].sStatus)+".png)";
					//是否有虫
					if(data[i].hasInsect == 1){
						pests[data[i].groundId].style.backgroundImage = "url(<%=basePath%>images/pest.png)";
					}else if(data[i].hasInsect == 0){
						pests[data[i].groundId].style.backgroundImage = "";
					}
				}
				
			}else{
				crops[data[i].groundId].style.backgroundImage = "";
			}
		}
	}
	var websocket = null;
	function initWebSocket(){	    	     
		if ('WebSocket' in window) {  
	        //Websocket的连接  
	        websocket = new WebSocket("<%=wsBasePath%>fields/action");//WebSocket对应的地址  
	    }  
	    else if ('MozWebSocket' in window) {  
	        //Websocket的连接  
	        websocket = new MozWebSocket("<%=wsBasePath%>fields/action");//SockJS对应的地址  
	    }
	    else {
	        //SockJS的连接
	        websocket = new SockJS("<%=wsBasePath%>fields/action");//SockJS对应的地址    
	    }
	    websocket.onopen = onOpen;  
	    websocket.onmessage = onMessage;  
	    websocket.onerror = onError;  
	    websocket.onclose = onClose;
	}

	function onOpen(evt) {  
	    console.log("连接打开：",evt);  
	}
	function onError(evt) {  
		console.log("出现错误：",evt);
	}  
	function onClose(evt) {  
		console.log("连接关闭：",evt);
	}
	function onMessage(evt) {
		let data = JSON.parse(evt.data);
		getAndGenderGroundSeed(JSON.parse(evt.data));
	}

	//初始化土地种子信息
	function getAndGenderGroundSeed(){
		getRemoteData("<%=basePath%>ground/data",function(res){
			 initGroundSeed(res);
		})
	}
	//种植
	function plant(groundId,sId){
		getRemoteData("<%=basePath%>ground/plant?groundId="+groundId+"&sId="+sId,function(res){
			if(res.code == 0){
				getAndGenderGroundSeed();
			}
			message(res);
		})
	}
	function message(res){
		$.messager.show({
	    	title: "消息",
	    	height:160,
	    	msg: res.msg
	    });
	}
	//收获
	function receive(groundId){
			getRemoteData("<%=basePath%>ground/receive?groundId="+groundId,function(res){
				if(res.code == 0){
					getAndGenderGroundSeed();
					refreshUserInfo();
				}
				message(res);
			});
		}
	//除枯草
	function cleanGlass(groundId){	
			getRemoteData("<%=basePath%>ground/cleanGlass?groundId="+groundId,function(res){
				if(res.code == 0){			
					getAndGenderGroundSeed();
					refreshUserInfo();
				}
				message(res);
			})
	}
	
	//除虫
	function killPest(groundId){
			getRemoteData("<%=basePath%>ground/killWorm?groundId="+groundId,function(res){
				if(res.code == 0){
					getAndGenderGroundSeed();
					refreshUserInfo();
				}
				message(res);
			})
	}
	//更新用户信息
	function refreshUserInfo(){
		parent.document.getElementById("menu").setAttribute("src","menu.jsp");
	}
</script>
</body>
</html>