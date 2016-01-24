<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/style/mainstyle.css" rel="stylesheet">
<title>添加课程-${SessionScope.currentUser}</title>
</head>
<body style="padding-top: 50px; margin: 0 15px;">
	<%@include file="/common/top.jsp"%>

	<div class="container">
		<div class="row" style="padding-top: 100px;">
			<%@include file="/teacher/navi.jsp"%>

			<div class="col-xs-12 col-md-8">
				<div class="maincontent">
				<fieldset>
					<legend>更新课程信息</legend>
					<s:if test="fieldErrors != null && fieldErrors.size > 0">
						<div class="alert alert-danger">
							<s:fielderror />
							<s:actionerror />
						</div>
					</s:if>

					<form class="form-horizontal" action="${ctx}/teacher/course_save" method="post"
						>
						<s:hidden name="id"/>

						<div class="form-group">
							<label for="snumber" class="col-md-2 control-label">课程号</label>
							<div class="col-md-3">
								<s:textfield name="snumber" id="snumber" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">名称</label>
							<div class="col-md-3">
								<s:textfield name="name" id="name" cssClass="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">时间</label>
							<div class="col-sm-2">
								<s:select list="weeks" cssClass="form-control" name="week" listValue="week"></s:select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">节次</label>
							<div class="col-sm-2">
								<s:select list="tableList" cssClass="form-control" name="no" ></s:select>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-2 col-md-3">
								<s:submit value="保存" cssClass="button button-flat-primary button-tiny" style="height:32px"/> 
							</div>
						</div>
						
					</form>
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