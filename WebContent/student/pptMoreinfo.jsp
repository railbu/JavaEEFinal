<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="${ctx}/resources/style/app2.css" rel="stylesheet"></link>
<link type="text/css" href="${ctx}/resources/style/mainstyle.css" rel="stylesheet"></link>
<%@include file="/common/meta.jsp"%>
<title>课件管理-</title>

</head>
<body style="padding-top: 50px; margin: 0 15px;">
	<%@include file="/common/top.jsp"%>

	<div class="container">
		<div class="row" style="padding-top: 100px;">
			<%@include file="/student/navi.jsp"%>
			<div class="col-xs-12 col-md-9">
				<div class="maincontent">
				<fieldset>
					<s:hidden name="cid" value="id" />
					<legend>课件详情</legend>
					<s:if test="message != null">
						<div class="alert alert-<s:property value="message.type"/>">
							<s:property value="message.content" />
						</div>
					</s:if>
					<!--  之前表格显示的结果
					<table class="table">
						<thead>
							<tr>
								<th>PPT名称</th>
								<th>上传时间</th>
								<th>类型</th>
								<th>下载次数</th>
								<th width="280">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="pptlist" var="item">
								<tr>
									<td><s:property value="name" /></td>
									<td><s:property value="creTime" /></td>
									<td><s:property value="type" /></td>
									<td><s:property value="clickCount" /></td>
									<td><s:url value="/%{nameInDisk}" id="download">
										</s:url> <s:a href="%{download}" cssClass="btn btn-default">下载</s:a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</fieldset>
				 -->
					<div class="container" style="width: 800px; padding-right: 20px">

						<ul id="faceul" style="margin-left:-35px">
							<s:iterator value="pptlist" var="item">
								<li>
									<div class="span4">
										<div class="hollow card " data-type="mobile"
											data-is-development="0" data-is-design="1">
											<div>
												<img src="${ctx}/<s:property value="firPicPosition"/>"
													alt="预览" width="200px" height="170px" class="img-thumbnail">
											</div>
											<div style="margin-top:8px">
											<span style="font-size:14px;font-family:黑体;">
												名称:
												<span style="font-size:14px;font-family:黑体;color:#336699">
													<s:property value="name" />
												</span>
											</span>
											</div>
											
											<span style="font-size:14px;font-family:黑体;">
												时间:
												<span style="font-size:14px;font-family:黑体;color:#009933">
													<s:property value="creTime" />
												</span>
											</span>

											<s:url value="/download_down" id="download2">
												<s:param name="filePath" value="%{nameInDisk}" />
												<s:param name="fileName" value="%{name}" />
											</s:url>

											<div class="centered">
												<s:a href="%{download2}">下载</s:a>
											</div>

											<!-- close .padding -->
										</div>
										<!-- close .card -->
									</div>
								</li>
								<!-- close .span4 -->
							</s:iterator>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- row -->

	<!-- container -->

	<%@include file="/common/footer.jsp"%>

</body>
</html>