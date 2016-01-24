<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/style/mainstyle.css" rel="stylesheet">
<title>学生|个人中心</title>
<style type="text/css">
</style>
</head>
<body>
	<%@include file="/common/top.jsp"%>
	
	<div class="container">
		<div class="row" style="padding-top: 100px;">

			<%@include file="/student/navi.jsp"%>
			<div class="col-xs-12 col-md-8">
			<div class="maincontent">
				<form class="form-horizontal" action="${ctx}/student/home_modify.action" method="post">
					<fieldset>
					<legend>账户设置</legend>
					<s:if
						test="fieldErrors != null && fieldErrors.size > 0 || actionErrors != null && actionErrors.size > 0">
						<div class="alert alert-danger">
							<s:fielderror />
						</div>
					</s:if>
					<s:hidden name="id" />
					
					<div class="form-group">
						<label for="email" class="col-sm-4 control-label" style="margin-top:10px">用户名:</label>
						<div class="col-sm-2">
							<label style="font-size:22px;margin-top:10px">${sessionScope.currentUser} 　</label>
						</div>
						<div class="col-sm-2">
							<img src="${ctx}/common/image.jsp" width="55px" height="55px" style="margin-top:0px" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="email" class="col-sm-4 control-label">Email:</label>
						<div class="col-sm-4">
							<input type="text" name="email" class="form-control" id="email" value="${sessionScope.student.email}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="stuNo" class="col-sm-4 control-label">学号:</label>
						<div class="col-sm-4">
							<input type="text" name="stuNo" class="form-control" id="stuNo" value="${sessionScope.student.stuNo}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="school" class="col-sm-4 control-label">学校:</label>
						<div class="col-sm-4">
							<input type="text" name="school" class="form-control" id="school" value="${sessionScope.student.school}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="className" class="col-sm-4 control-label">头像:</label>
						<div class="col-sm-4">
							<%@include file="input.jsp" %>
						</div>
					</div>
					
					<div class="form-group">
						<label for="code" class="col-lg-4 control-label">验证码</label>
						<div class="col-sm-2">
							<s:textfield name="code" cssClass="form-control" id="code" />
						</div>
						<div class="col-sm-2">
							<img class="img-thumbnail" src="${ctx}/common/code.jsp" onClick="this.src='${ctx}/common/code.jsp?'+Math.random()" data-toggle="tooltip" data-placement="right" title="点击可以切换图片">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-6">
							<s:submit cssClass="button button-flat-primary button-tiny" style="height:32px" value="确认修改" />
						</div>
					</div>
					</fieldset>
				</form>
				</div>
			</div>
			
		</div> <!-- row -->
	</div> <!-- container -->
	
	<%@include file="/common/footer.jsp"%>

</body>
</html>