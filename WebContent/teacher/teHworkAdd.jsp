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
					<legend>布置作业</legend>
					<s:if test="fieldErrors != null && fieldErrors.size > 0">
						<div class="alert alert-danger">
							<s:fielderror />
							<s:actionerror />
						</div>
					</s:if>

					<form class="form-horizontal" action="${ctx}/teacher/hworkMa_submit" method="post">
						<div class="form-group">
							<label for="cid" class="col-md-2 control-label">课程名</label>
							<div class="col-md-3">
								<s:select list="clist" cssClass="form-control" name="cid" 
								     listKey="id" listValue="name" headerKey="" headerValue="--请选择一门课程--"></s:select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="hworkLabel" class="col-md-2 control-label">作业标题</label>
							<div class="col-md-3">
								<s:textfield name="hworkLabel" cssClass="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label for="content" class="col-md-2 control-label">详细内容</label>
							<div class="col-md-6">
								<s:textarea name="content" style="width:420px; height:200px;"/>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-2 col-md-3">
								<s:submit value="提交" cssClass="button button-flat-primary button-tiny" style="height:32px;width:100px;"/> 
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