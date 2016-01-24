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
	<script src="${ctx}/resources/jquery/jquery-1.10.2.js"></script>
	<script src="${ctx}/resources/bootstrap/js/bootstrap.min.js"></script>
	<%@include file="/common/top.jsp"%>


	<div class="container">
		<div class="row" style="padding-top: 100px;">
			<%@include file="/student/navi.jsp"%>

			<div class="col-xs-12 col-lg-8">
			<div class="maincontent">
				<fieldset>
					<s:hidden name="cid" value="id" />
					<legend>作业详情</legend>
					<s:if test="message != null">
						<div class="alert alert-<s:property value="message.type"/>">
							<s:property value="message.content" />
						</div>
					</s:if>
					
					<table class="table">
						<thead>
							<tr>
								<th>上传时间</th>
								<th>作业名称</th>
								<th width="280">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="hlist" var="item">
								<tr>
									<td><s:property value="creTime" /></td>
									<td><s:property value="hworkLabel" />
										<button class="btn btn-link" data-toggle="modal"
											data-target="#myModal<s:property value="id" />">详情</button> <!-- Modal -->
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
													
														<s:hidden name="id" value="%{id}"/>
														<s:hidden name="cid" value="%{cid}"/>
														
														<div class="modal-body">
															<div class="form-group">
																<label for="content" class="control-label">详细内容</label>
																<s:textarea name="content" value="%{content}"
																	style="width:550px;height:200px;" readonly="true"/>
															</div>
														</div>
														<div class="modal-footer">
															<button type="button" class="button button-flat-primary button-tiny" style="height:32px"
																data-dismiss="modal">Close</button>
														</div>
													
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div> <!-- /.modal --></td>
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