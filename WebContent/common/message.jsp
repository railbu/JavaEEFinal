<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>教师信息管理</title>
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
						
						<a href="${ctx}<s:property value="message.backView"/>" class="button button-flat-action button-tiny">返回</a>
					</div>
				</div>
			</s:if>
		</div>
	</div>
	<%@include file="/common/footer.jsp"%>
</body>
</html>


