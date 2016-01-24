<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Test</title>
<link type="text/css" href="resources/style/test.css" rel="stylesheet"></link>
<%-- <script src="${ctx}/resources/jquery/jquery-1.10.2.js"></script>
<script src="${ctx}/resources/bootstrap/js/bootstrap.min.js"></script> --%>
<script type="text/javascript"> 
	function show(){
//		var x=event.clientX;
//		var y=event.clientY;
//		document.getElementById("pic").style.top=y+100;
//		document.getElementById("pic").style.left=x;
		document.getElementById("pic").style.visibility="visible";
//		var formDiv="<form action='#'>";
//		formDiv+="用户名: <input type='text' name='user' /><br>";
//		formDiv+="密码:    <input type='password' name='pass' /><br><br>";	 
//      formDiv+="<input type='submit' value='提交' onclick='hide()' /></form>";	 
//		document.getElementById("pic").innerHTML=formDiv;
	}
		
	function hide(){
		document.getElementById("pic").style.visibility="hidden";
	}
</script>
</head>

<body>
	<br></br>
	<!-- <div id="pic" style="border: 1;position: absolute;width: 357;height: 800;visibility: hidden"> -->
		<div id="pic" class="tang-pass-pop-login-noimg tang-pass-pop-login-merge tang-pass-pop-login-color-blue tang-pass-pop-login" style="margin-left: 478px; top: 18px; z-index: 60001;visibility: hidden">
			<div class="tang-background" style="position:absolute; top:0px; left:0px;width:100%;height:100%;z-index:-9; -webkit-user-select:none; -moz-user-select:none;"></div>
			<div class="tang-foreground" style="width: 393px;">
				<div class="tang-title tang-title-dragable">
			        <div class="buttons">
			            <a class="close-btn" onclick="hide()" onmousedown="event.stopPropagation && event.stopPropagation(); event.cancelBubble = true; return false;" href="###"></a>
			        </div>
			        <span style="width: 357px;">
			        	登录
			       	</span>
			    </div>
			    <div class="tang-body">
			    	<div class="tang-content">
			    		<div class="clearfix">
			    			<div class="pass-login-pop-content">
								<div class="pass-login-pop-form">
									<div id="passport-login-pop-api" class="tang-pass-login">
										<fieldset>
											<br/>
											<s:if test="mess != null">
												<div class="alert alert-<s:property value="mess.type"/>">
												<s:property value="mess.content" />
												</div>
											</s:if>				
											
											<form class="form-horizontal" action="${ctx}/download_logins.action"
												method="post">
												<div class="form-group">
													<label for="username" class="col-lg-4 control-label">用户名</label>
													<div class="col-lg-6">
														<s:textfield name="username" cssClass="form-control"
															id="username" />
													</div>
												</div>
							
												<div class="form-group">
													<label for="password" class="col-lg-4 control-label">密码</label>
													<div class="col-lg-6">
														<s:password name="password" cssClass="form-control" id="password" />
													</div>
												</div>
												
												<div class="form-group">
													<label for="code" class="col-lg-4 control-label">验证码</label>
													<div class="col-lg-3">
														<s:textfield name="code" cssClass="form-control" id="code" />
													</div>
													<div class="col-lg-3">
														<img class="img-thumbnail" src="common/code.jsp" onClick="this.src='common/code.jsp?'+Math.random()" data-toggle="tooltip" data-placement="right" title="点击可以切换图片"></img>
													</div>
												</div>
												
												
												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-6">
														<s:submit cssClass="button button-flat-primary button-tiny" style="height:32px" value="登陆"/>
													</div>
												</div>
											</form>
										</fieldset>
									</div>
								</div>
							</div>
			    		</div>
			    	</div>
			    </div>
			</div>
		</div>
	<!-- </div> -->
	<script type="text/javascript">
		document.getElementById("pic").style.visibility="visible";
	</script>
</body>
</html>
