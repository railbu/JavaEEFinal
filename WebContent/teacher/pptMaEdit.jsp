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
					<legend>编辑PPT信息</legend>
					<s:if test="fieldErrors != null && fieldErrors.size > 0">
						<div class="alert alert-danger">
							<s:fielderror />
							<s:actionerror />
						</div>
					</s:if>

					<form class="form-horizontal" action="${ctx}/teacher/pptMa_save" method="post">
						<s:hidden name="id"/>

						<div class="form-group">
							<label for="name" class="col-lg-2 control-label">名称</label>
							<div class="col-lg-3">
								<s:textfield name="name" id="name" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">价格</label>
							<div class="col-lg-3">
								<s:textfield name="price" id="price" cssClass="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-3">
								<s:submit value="保存" cssClass="button button-flat-primary button-tiny" style="height:32px;"/> 
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