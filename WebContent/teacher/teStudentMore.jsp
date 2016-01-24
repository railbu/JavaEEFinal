<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/style/mainstyle.css" rel="stylesheet">
<title>课件管理-</title>
</head>
<body style="padding-top: 50px; margin: 0 15px;">
	<%@include file="/common/top.jsp"%>

	<div class="container">
		<div class="row" style="padding-top: 100px;">
			<%@include file="/teacher/navi.jsp"%>

			<div class="col-xs-12 col-md-8">
				<div class="maincontent">
				<fieldset>
					<s:hidden name ="cid" value="cid"/>
					<legend>学生详情</legend>
					<s:if test="message != null">
						<div class="alert alert-<s:property value="message.type"/>">
						<s:property value="message.content" />
						</div>
					</s:if>
					
					<table class="table">
					<thead>
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<th>班级</th>
							
							<th width="180">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="mslist" var="item">
							<tr>
								<td><s:property value="stuNo" /></td>
								<td><s:property value="realName" /></td>
								<td><s:property value="className" /></td>
								
								<td>
									<s:url value="/teacher/courstu_delete" id="delete">
										<s:param name="id" value="id" />
										<!-- 需要去查看这里cid是怎么找到值的 -->
										<s:param name="cid" value="cid" />
									</s:url>
									<s:a href="%{delete}" cssClass="button button-flat-caution button-tiny" style="height:32px;">删除</s:a> 
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
					
				</fieldset>
			</div>
			</div>
		</div>
		<!-- row -->
	</div>
	<!-- container -->

	<%@include file="/common/footer.jsp"%>

</body>
</html>