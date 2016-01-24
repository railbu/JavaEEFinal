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
<body>
	<%@include file="/common/top.jsp"%>
	
	<div class="container">
		<div class="row" style="padding-top: 100px;">

			<%@include file="/student/navi.jsp"%>
			<div class="col-xs-12 col-md-8">
				<div class="maincontent">
				<s:if test="message != null">
					<div class="alert alert-<s:property value="message.type"/>">
						<s:property value="message.content" />
					</div>
				</s:if>
				<a href="${ctx}/student/table_edit.action" class="button button-flat-primary button-tiny" style="height:32px">编辑课表</a>
				
				<!-- 一键导入的内容 -->
						<button class="button button-flat-primary button-tiny" data-toggle="modal"
											data-target="#myModal">一键导入</button> <!-- Modal -->
										<div class="modal fade" id="myModal"
											tabindex="1" role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title" id="myModalLabel">
															请再下方输入您的学号和密码
														</h4>
													</div>
														
													<div class="modal-body">
														<form cssClass="form-horizontal" id="oneKey" action="onekey_leadin.action" method="post">
															<div class="form-group">
																<label for="stuIdLab" class="col-md-2 col-md-offset-2 control-label">学号</label>
																<div class="col-md-6">
																	<s:textfield name="studentID" cssClass="form-control"
																		id="studentID" />
																</div>
															</div>
															
															<div class="form-group">
																<label for="pwordLb" class="col-md-2 col-md-offset-2 control-label">密码</label>
																<div class="col-md-6">
																	<s:password name="password" cssClass="form-control"
																		id="password" />
																</div>
															</div>
															
															<div class="form-group">
																	<s:submit class="button button-flat-primary button-tiny" name="submit" text="导入"/>
															</div>
																
														</form>
													</div>
													
													<div class="modal-footer">
														<button type="button" class="button button-flat-primary button-tiny" style="height:32px"
															data-dismiss="modal">OK</button>
													</div>
													
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div> <!-- /.modal -->
						<!-- 一键导入结束 -->
						
				<br><br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><center>#</center></th>
							<th><center>星期一</center></th>
							<th><center>星期二</center></th>
							<th><center>星期三</center></th>
							<th><center>星期四</center></th>
							<th><center>星期五</center></th>
							<th><center>星期六</center></th>
							<th><center>星期日</center></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="tableList" var="item">
							<tr>
								<th><center><s:property value="no" /></center></th>
								
								<s:if test="type[0] == 0">
									<td><center><s:property value="Mon"/></center></td>
								</s:if>
								<s:else>
									<s:url value="/student/pptMa_moreinfo" id="smwy0">
										<s:param name="id" value="courseid[0]"/>
									</s:url>
									<td><center><s:a href="%{smwy0}"><s:property value="Mon"/></s:a></center></td>
								</s:else>
								
								<s:if test="type[1] == 0">
									<td><center><s:property value="Tue"/></center></td>
								</s:if>
								<s:else>
									<s:url value="/student/pptMa_moreinfo" id="smwy1">
										<s:param name="id" value="courseid[1]"/>
									</s:url>
									<td><center><s:a href="%{smwy1}"><s:property value="Tue"/></s:a></center></td>
								</s:else>
								
								<s:if test="type[2] == 0">
									<td><center><s:property value="Wed"/></center></td>
								</s:if>
								<s:else>
									<s:url value="/student/pptMa_moreinfo" id="smwy2">
										<s:param name="id" value="courseid[2]"/>
									</s:url>
									<td><center><s:a href="%{smwy2}"><s:property value="Wed"/></s:a></center></td>
								</s:else>
								
								<s:if test="type[3] == 0">
									<td><center><s:property value="Thu"/></center></td>
								</s:if>
								<s:else>
									<s:url value="/student/pptMa_moreinfo" id="smwy3">
										<s:param name="id" value="courseid[3]"/>
									</s:url>
									<td><center><s:a href="%{smwy3}"><s:property value="Thu"/></s:a></center></td>
								</s:else>
								
								<s:if test="type[4] == 0">
									<td><center><s:property value="Fri"/></center></td>
								</s:if>
								<s:else>
									<s:url value="/student/pptMa_moreinfo" id="smwy4">
										<s:param name="id" value="courseid[4]"/>
									</s:url>
									<td><center><s:a href="%{smwy4}"><s:property value="Fri"/></s:a></center></td>
								</s:else>
								
								<s:if test="type[5] == 0">
									<td><center><s:property value="Sat"/></center></td>
								</s:if>
								<s:else>
									<s:url value="/student/pptMa_moreinfo" id="smwy5">
										<s:param name="id" value="courseid[5]"/>
									</s:url>
									<td><center><s:a href="%{smwy5}"><s:property value="Sat"/></s:a></center></td>
								</s:else>
								
								<s:if test="type[6] == 0">
									<td><center><s:property value="Sun"/></center></td>
								</s:if>
								<s:else>
									<s:url value="/student/pptMa_moreinfo" id="smwy6">
										<s:param name="id" value="courseid[6]"/>
									</s:url>
									<td><center><s:a href="%{smwy6}"><s:property value="Sun"/></s:a></center></td>
								</s:else>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			</div>
		</div> <!-- row -->
	</div> <!-- container -->
	
	<%@include file="/common/footer.jsp"%>

</body>
</html>