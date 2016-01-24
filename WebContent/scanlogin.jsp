<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${ctx}/resources/jquery/jquery-1.10.2.js"></script>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/style/log1.css" rel="stylesheet">
<link href="${ctx}/resources/style/log2.css" rel="stylesheet">
<link href="${ctx}/resources/style/log3.css" rel="stylesheet">
<title>登陆-课件网</title>

<script type="text/javascript">
	function testlogin(){
		var xmlhttp;
		xmlhttp=new XMLHttpRequest();

		xmlhttp.onreadystatechange=function()
		  {
		  	if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    	document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
		    }	 
		  };
		  
		xmlhttp.open("GET","loginscan_loginscanweb.action",true);
		xmlhttp.send();
	}
	
</script>

</head>
<body class="landing site">
	<div class="navbar">
	    <div class="container">
	        <a class="navbar-brand">
	            <img width="48" alt="Logo" src="resources/images/logo-inc-g.png"></img>
	        </a>
	        <ul class="nav navbar-nav">
	            <li>
	                <a class="nav-about" href="lastaction_last">首页</a>
	            </li>
	            <li class="sep"></li>
	            <li>
	                <a href="#">关于我们</a>
	            </li>
	            <li class="sep"></li>
	            <li>
	                <a href="login.jsp">登录</a>
	            </li>
	            <li class="signup">
	                <a class="btn signup-link" href="${ctx}/sregister_input.action">注册</a>
	            </li>
	        </ul>
	    </div>
	</div>
	<header>
	    <div class="bg"></div>
	    <div class="container">
	        <div class="col col-xs-5 col-md-offset-1">
	            <div class="text">
	                <h1>Say no to copy, build a better interaction</h1>
	                <p>Share ppts, pdfs and publish homework in one place.</p>
	            </div>
	        </div>
	        <div class="col col-xs-4 col-md-offset-2">
	            <div class="box" style="height:315px">
	                <h3>登 录</h3>
		                <center><img class="img-thumbnail" style="width:180px;height:180px;margin-top:15px" src="common/qrcode.jsp" /><br/>
		                <a href="login.jsp" style="margin-top:12px;height:35px;width:80px" class="btn signup-link">返 回</a></center>
	                
	            </div>
	        </div>
	    </div>
	</header>
	<div class="row">
	<div class="col-xs-6 col-xs-offset-3">
			<hr/>
			<span style="color: #000000;text-align:center;display:block">
				Copyright ©2013-2014 BYZZ Corporation<br/>
				All Rights Reserved
			</span>
	</div>
</div>
<script type="text/javascript">
	var timeC = setInterval(testlogin,2000);
</script>
</body>
</html>





