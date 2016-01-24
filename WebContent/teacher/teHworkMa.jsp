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
					<legend>作业管理</legend>
					<s:if test="message != null">
						<div class="alert alert-<s:property value="message.type"/>">
						<s:property value="message.content" />
						</div>
					</s:if>
					
					<a href="${ctx}/teacher/hworkMa_add" class="button button-flat-primary button-tiny" style="height:32px">布置作业</a>
					<table class="table">
					<thead>
						<tr>
							<th>课程名</th>
							<th>作业数量</th>
							<th>现有人数</th>
							<th width="180">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="clist" var="item">
							<tr>
								<td><s:property value="name" /></td>
								<td><s:property value="hworkCount" /></td>

								<td><s:property value="stuCount" /></td>
								<td>
									<s:url value="/teacher/hworkMa_moreinfo" id="moreinfo">
										<s:param name="cid" value="id" />
									</s:url> 
									<s:a href="%{moreinfo}" cssClass="button button-flat-primary button-tiny" style="height:32px">查看详情</s:a> 
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