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
			<%@include file="/student/navi.jsp"%>

			<div class="col-xs-12 col-md-8">
			<div class="maincontent">
				<fieldset>
					<s:hidden name="cid" value="id"/>
					<legend>课件详情</legend>
					<s:if test="message != null">
						<div class="alert alert-<s:property value="message.type"/>">
						<s:property value="message.content" />
						</div>
					</s:if>
					
					<table class="table">
					<thead>
						<tr>
							<th>PPT名称</th>
							<th>上传时间</th>
							<th>类型</th>
							<th>价格</th>
							<th>下载次数</th>
							<th width="180">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="pptlist" var="item">
							<tr>
								<td><s:property value="name" /></td>
								<td><s:property value="creTime" /></td>
								<td><s:property value="type" /></td>
								<td><s:property value="price" /></td>
								<td><s:property value="clickCount" /></td>
								<td>
									<s:url value="/%{nameInDisk}" id="download">
									</s:url>
									
									<s:a href="%{download}" cssClass="button button-flat-primary button-tiny" style="height:32px">下载</s:a>
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