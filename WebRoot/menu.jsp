<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单</title>
<style>
        body {
            margin: 0px;           
        }
        
        .bar{
        	background-image:url(images/topbar.png);        	
            background-size:25% 60px;
            background-repeat:repeat-x;            
        }
        
        .shadow{
        	-moz-box-shadow:2px 2px 5px #333333;
        	-webkit-box-shadow:2px 2px 5px #333333;
        	box-shadow:2px 2px 5px #333333;
        }
        
        .menu{
           margin: 3px 5px 5px 5px;
        }
        .userMessage{
        width:30%;
        float:left;
        }
        .men{
        width:70%;
        float:left;
        }
        .headImg{
        width:60px;
        height:60px;
        float:left;      
        }
        .otherMesg{
        height:60px;      
        float:left;
        }
	     .username{	 
	     height:30px;
	     font-size:24px;
	     font-family: normal;
     	 color:#FF8C00;
         font-weight:bold;
         text-shadow: -1px 1px 0 #000, 1px 1px 0 #000,1px -1px 0 #000,-1px -1px 0 #000; 	    
	     }
	     #gameMessage{	  
	     height:20px;
	     border-radius:10px;
     	 padding:0 15px;
     	 background-color:#A9A9A9;
     	 text-align:center;
     	
	     }
	     .exp{
	     font-size:14px;
	     color:white;
	     }
	     .points{
	     font-size:14px;
	     color:yellow;
	     }
	     .coins{
	     font-size:14px;
	     color:#7FFF00;
	     }
	     
    </style>
</head>
<body class="bar">
	<div class="userMessage" >	
		<div class="headImg">
			<c:if test="${user == null}">
			<img src='<%=basePath%>images/QQ.png' width="60px" height="60px" />
			</c:if>	
			<c:if test="${user != null}">
			<img src='<%=basePath%>images/headImages/${user.headImg}' width="60px" height="60px" />
			</c:if>
		</div>
	
		<div class="otherMesg">
			<c:if test="${user == null}">
				<div class="username"><span>未知用户</span></div>
				<div id="gameMessage">
				<span class="exp">经验：0</span>&nbsp;
				<span class="points">积分：0</span>&nbsp;
				<span class="coins">金币：0</span>&nbsp;
				</div>
			</c:if>	
			<c:if test="${user != null}">
				<div class="username"><span>${user.nicheng }</span></div>
				<div id="gameMessage">
				<span class="exp">经验：${user.exp}</span>&nbsp;
				<span class="points">积分：${user.points }</span>&nbsp;
				<span class="coins">金币：${user.coins }</span>&nbsp;
				</div>
			</c:if>	
		</div>
	
	</div>
	
	<div class="men" align="right">
	
	<a href="ground/farm" target="workspace" class="tools"><img class="menu shadow" src="images/farm.png" width="50px" height="50px"></a>
	<a href="user/login" target="workspace" class="tools"><img class="menu shadow" src="images/login.png" width="50px" height="50px"></a>
	<a href="shop/grid" target="workspace" id="shop"><img class="menu shadow" src="images/shop.png" width="50px" height="50px"></a>
	<a href="user/grid" target="workspace" class="tools"><img class="menu shadow" src="images/users.png" width="50px" height="50px"></a>
    <a href="seed/grid" target="workspace" class="tools"><img class="menu shadow" src="images/seedManager.png" width="50px" height="50px"></a>
		
	</div>
<script type="text/javascript">
var as = document.getElementsByTagName("a");
for(let i=0;i<as.length;i++){
	//如果是购买界面，改变底部
	if(as[i].id == "shop"){
		as[i].onclick = function(){
			parent.document.getElementById("main").setAttribute("rows","60,*,200");
			parent.document.getElementById("tools").setAttribute("src","<%=basePath%>user/mySeed");
		};
	}else{
		as[i].onclick = function(){
			parent.document.getElementById("main").setAttribute("rows","60,*,50");
			parent.document.getElementById("tools").setAttribute("src","tools.jsp");
		};
	}
}
</script>
</body>
</html>