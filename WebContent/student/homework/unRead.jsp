<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/style/mainstyle.css" rel="stylesheet">
<title>学生|个人中心</title>
</head>
<body style="padding-top: 50px; margin: 0 15px;">
	<script src="${ctx}/resources/jquery/jquery-1.10.2.js"></script>
	<script src="${ctx}/resources/bootstrap/js/bootstrap.min.js"></script>
	<%@include file="/common/top.jsp"%>

	<div class="container">
		<div class="row" style="padding-top: 100px;">

			<%@include file="/student/navi.jsp"%>
			<div class="col-xs-12 col-lg-8">
				<div class="maincontent">
				<fieldset>
					<legend>未读作业</legend>
					<s:if test="message != null">
						<div class="alert alert-<s:property value="message.type"/>">
							<s:property value="message.content" />
						</div>
					</s:if>
					<table class="table">
						<thead>
							<tr>
								<th>作业名称</th>
								<th>上传时间</th>
								<th>所在课程</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="unhlist" var="item">
								<tr class="warning">
									<td><s:property value="hworkLabel" /></td>
									<td><s:property value="creTime" /></td>
									<td><s:property value="courseName" /></td>

									<td>
										<button class="btn btn-link" data-toggle="modal"
											data-target="#myModal<s:property value="id" />">查看详情</button>

										<div class="modal fade" id="myModal<s:property value="id" />"
											tabindex="1" role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title" id="myModalLabel">
															<s:property value="hworkLabel" />
														</h4>
													</div>
													<form action="stuHwork_read" method="post">
														<s:hidden name="id" value="%{id}" />

														<div class="modal-body">
															<div class="form-group">
																<label for="content" class="control-label">详细内容</label>
																<s:textarea name="content" value="%{content}"
																	style="width:550px;height:200px;" />
															</div>
														</div>

														<div class="modal-footer">
															<s:submit value="Close" cssClass="button button-flat-primary button-tiny" style="height:32px" />
														</div>
													</form>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div> <!-- /.modal -->
									</td>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>


					<a href="${ctx}/student/stuHwork_listAll.action"
						class="btn btn-default">查看所有</a>
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