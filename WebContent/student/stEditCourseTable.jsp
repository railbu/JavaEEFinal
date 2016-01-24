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
<body style="padding-top: 50px;margin:0 15px;">
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
				<s:form cssClass="form-horizontal" action="/student/table_save.action" method="post">
					<s:hidden name="id" />
					<fieldset>
						<legend>编辑课表</legend>
						<div class="form-group">
							<label for="name" class="col-md-3 control-label">时间</label>
							<div class="col-lg-2">
								<s:select list="weeks" cssClass="form-control" name="week" listValue="week"></s:select>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-md-3 control-label"></label>
							<div class="col-lg-2">
								<s:select list="tableList" cssClass="form-control" name="no" listValue="no" listKey="no"></s:select>
							</div>
						</div>
						<div class="form-group">
							<label for="teacher" class="col-md-3 control-label">课程信息</label>
							<div class="col-lg-3">
								<s:textfield data-trigger="tooltip" title="Course Name(Teacher Name)" name="cname" id="cname" cssClass="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-3 col-md-3">
								<s:submit value="保存" cssClass="button button-flat-primary button-tiny" style="height:32px"/> 
                           		<s:reset value="重置" cssClass="button button-flat-action button-tiny" style="height:32px"/> 
							</div>
						</div>
					</fieldset>
				</s:form>
			</div>
			</div>
		</div> <!-- row -->
	</div> <!-- container -->
	
	<%@include file="/common/footer.jsp"%>

</body>
</html>