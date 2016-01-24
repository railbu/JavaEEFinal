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
	            <div class="box">
	                <h3>登 录　　　　　　　<a href="scanlogin.jsp"><img class="img-rounded" style="width:35px;height:35px" src="resources/images/QRlogo.jpg" data-toggle="tooltip" data-placement="right" title="二维码登录"/></a></h3>
	                
	                <form class	="form-horizontal" action="${ctx}/login_submit.action" method="post">
	                	<s:if
							test="fieldErrors != null && fieldErrors.size > 0 || actionErrors != null && actionErrors.size > 0">
							<div class="alert alert-danger" style="font-size:13px">
								<s:fielderror />
								<s:actionerror />
							</div>
						</s:if>
		                <div class="input-group input-group-lg">
							<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
							<input type="text" name="username" style="font-size:18px;font-weight: bold;width:205px" id="username" class="form-control" placeholder="用户名">
						</div>
						<br>
						<div class="input-group input-group-lg">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
							<input type="password" name="password" style="font-size:18px;font-weight: bold;width:205px" id="password" class="form-control" placeholder="密码">
						</div>
						<br>
						<div class="input-gourp">
							<img class="img-thumbnail" src="common/code.jsp" style="margin-top: 4px" onClick="this.src='common/code.jsp?'+Math.random()" data-toggle="tooltip" data-placement="right" title="点击可以切换图片">
							<input type="text" name="code" style="width:95px; height:38px;font-size:15px" id="code" />
							<input class="btn signup-link" type="submit" value="登 录"></input>
						</div>
	                    
	                </form>
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
</body>
</html>