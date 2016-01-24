<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/style/mainstyle.css" rel="stylesheet">
<title>教师登陆</title>
</head>
<body style="padding-top: 50px; margin: 0 15px;">
	<%@include file="/common/top.jsp"%>

	<div class="container">
		<div class="row" style="padding-top: 100px;">

			<%@include file="/teacher/navi.jsp"%>
			
			<div class="col-xs-12 col-md-8">
				<div class="maincontent">
				<fieldset>
				<legend>课程管理</legend>
				<s:if test="message != null">
					<div class="alert alert-<s:property value="message.type"/>">
						<s:property value="message.content" />
					</div>
				</s:if>
				<a href="${ctx}/teacher/course_edit" class="button button-flat-primary button-tiny" style="height:32px">添加课程</a>
				<table class="table">
					<thead>
						<tr>
							<th>编号</th>
							<th>名称</th>
							<th>创建时间</th>
							<th>现有人数</th>
							<th width="180">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list" var="item">
							<tr>
								<td><s:property value="snumber" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="createTime"/></td>
								<td><s:property value="stuCount" /></td>
								<td>
									<s:url value="/teacher/course_edit" id="edit">
										<s:param name="id" value="id" />
									</s:url> <s:url value="/teacher/course_delete" id="delete">
										<s:param name="id" value="id" />
									</s:url> <s:a href="%{edit}" cssClass="button button-flat-primary button-tiny" style="height:32px;">编辑</s:a> <s:a
										href="%{delete}" cssClass="button button-flat-caution button-tiny" style="height:32px;">删除</s:a>
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