<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="3; url='login.jsp'">
<title>注册信息</title>
<%@include file="/common/meta.jsp"%>
</head>
<body>
	<%@include file="/common/top.jsp"%>
	<div id="content" class="container" style="margin-top:100px;">
		<div id="main" class="col-md-offset-3 col-lg-6">
			<s:if test="message != null">
				<div class="alert alert-<s:property value="message.type"/>">
					<s:property value="message.content" />
					<div style="text-align:center;margin-top:10px;">
						<span style="color:#9D9D9D ; font-size:12px; font-family:'宋体'">3秒后页面将自动跳转...</span><br>
						<a href="${ctx}<s:property value="message.backView"/>" class="button button-flat-action button-tiny" style="height:32px">返回</a>
					</div>
				</div>
			</s:if>
		</div>
	</div>
	<%@include file="/common/footer.jsp"%>
</body>
</html>


