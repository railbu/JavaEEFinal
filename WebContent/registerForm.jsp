<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户注册</title>
<%@include file="/common/meta.jsp"%>
</head>
<body>
	<%@include file="/common/top.jsp"%>
	<br/><br/><br/><br/>
	<div id="content" class="container">
		<br>
		<div class="row">
			<div class="col-lg-5 col-lg-offset-3">
				<fieldset>
					<ul class="nav nav-tabs" id="myTab">
						<s:if test="type==1 || type==null">
							<li class="active"><a href="#stuReg" data-toggle="tab">学生注册</a></li>
						</s:if>
						<s:else>
							<li><a href="#stuReg" data-toggle="tab">学生注册</a></li>
						</s:else>
						<s:if test="type==2">
							<li class="active"><a href="#teaReg" data-toggle="tab">教师注册</a></li>
						</s:if>	
						<s:else>
							<li><a href="#teaReg" data-toggle="tab">教师注册</a></li>
						</s:else>
						<s:if test="type==3">
							<li class="active"><a href="#visReg" data-toggle="tab">游客注册</a></li>
						</s:if>
						<s:else>
							<li><a href="#visReg" data-toggle="tab">游客注册</a></li>
						</s:else>
					</ul>
					<br>
					<s:if
						test="fieldErrors != null && fieldErrors.size > 0 || actionErrors != null && actionErrors.size > 0">
						<div class="alert alert-danger">
							<s:fielderror />
							<s:actionerror />
						</div>
					</s:if>
					
					
					<div class="tab-content">
						<!-- 第一个Pane 学生注册 -->
						<s:if test="type==1 || type==null">
							<div class="tab-pane fade in active" id="stuReg">
						</s:if>
						<s:else>
							<div class="tab-pane fade" id="stuReg">
						</s:else>
							<form class="form-horizontal" action="${ctx}/sregister_submit.action"
								  method="post">
								<s:hidden name="type" value="1"></s:hidden>

								<div class="form-group">
									<label for="username" class="col-sm-4 control-label">用户名</label>
									<div class="col-sm-6">
										<s:textfield name="username" cssClass="form-control"
											id="username" />
									</div>
								</div>

								<div class="form-group">
									<label for="password" class="col-sm-4 control-label">密码</label>
									<div class="col-sm-6">
										<s:password name="password" cssClass="form-control"
											id="password" />
									</div>
								</div>

								<div class="form-group">
									<label for="passwordAgain" class="col-sm-4 control-label">密码确认</label>
									<div class="col-sm-6">
										<s:password name="passwordAgain" cssClass="form-control"
											id="passwordAgain" />
									</div>
								</div>

								<div class="form-group">
									<label for="email" class="col-sm-4 control-label">Email</label>
									<div class="col-sm-6">
										<s:textfield name="email" cssClass="form-control" id="email" />
									</div>
								</div>
								<div class="form-group">
									<label for="stuNo" class="col-sm-4 control-label">学号</label>
									<div class="col-sm-6">
										<s:textfield name="stuNo" cssClass="form-control"
											id="stuNo" />
									</div>
								</div>
								
								<div class="form-group">
									<label for="school" class="col-sm-4 control-label">学校</label>
									<div class="col-sm-6">
										<s:textfield name="school" cssClass="form-control" id="school" />
									</div>
								</div>
								
								<div class="form-group">
									<label for="className" class="col-sm-4 control-label">头像</label>
									<div class="col-sm-6">
										<%@include file="input.jsp" %>
									</div>
								</div>
								
								<div class="form-group">
									<label for="code" class="col-lg-4 control-label">验证码</label>
									<div class="col-lg-3">
										<s:textfield name="code" cssClass="form-control" id="code" />
									</div>
									<div class="col-lg-3">
										<img class="img-thumbnail" src="common/code.jsp" onClick="this.src='common/code.jsp?'+Math.random()" data-toggle="tooltip" data-placement="right" title="点击可以切换图片" style="margin-top: 1px">
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-6">
										<s:submit cssClass="button button-flat-primary button-tiny" style="height:32px" value="注册" />
									</div>
								</div>
							</form>
						</div>
						<!-- 第二个tab  教师注册 -->
						<s:if test="type==2">
							<div class="tab-pane fade in active" id="teaReg">
						</s:if>
						<s:else>
							<div class="tab-pane fade" id="teaReg">
						</s:else>
							<form class="form-horizontal" action="${ctx}/tregister_submit.action"
								 method="post">
								<s:hidden name="type" value="2"></s:hidden>
								<div class="form-group">
									<label for="username" class="col-sm-4 control-label">用户名</label>
									<div class="col-sm-6">
										<s:textfield name="username" cssClass="form-control"
											id="username" />
									</div>
								</div>

								<div class="form-group">
									<label for="password" class="col-sm-4 control-label">密码</label>
									<div class="col-sm-6">
										<s:password name="password" cssClass="form-control"
											id="password" />
									</div>
								</div>

								<div class="form-group">
									<label for="passwordAgain" class="col-sm-4 control-label">密码确认</label>
									<div class="col-sm-6">
										<s:password name="passwordAgain" cssClass="form-control"
											id="passwordAgain" />
									</div>
								</div>

								<div class="form-group">
									<label for="email" class="col-sm-4 control-label">Email</label>
									<div class="col-sm-6">
										<s:textfield name="email" cssClass="form-control" id="email" />
									</div>
								</div>
								
								<div class="form-group">
									<label for="realName" class="col-sm-4 control-label">真实姓名</label>
									<div class="col-sm-6">
										<s:textfield name="realName" cssClass="form-control" id="realName" />
									</div>
								</div>
								
								<div class="form-group">
									<label for="school" class="col-sm-4 control-label">学校</label>
									<div class="col-sm-6">
										<s:textfield name="school" cssClass="form-control" id="school" />
									</div>
								</div>
																
							
								
								<div class="form-group">
									<label for="code" class="col-lg-4 control-label">验证码</label>
									<div class="col-lg-3">
										<s:textfield name="code" cssClass="form-control" id="code" />
									</div>
									<div class="col-lg-3">
										<img class="img-thumbnail" src="common/code.jsp" onClick="this.src='common/code.jsp?'+Math.random()" data-toggle="tooltip" data-placement="right" title="点击可以切换图片" style="margin-top: 1px">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-6">
										<s:submit cssClass="button button-flat-primary button-tiny" style="height:32px" value="注册" />
									</div>
								</div>
							</form>
						</div>
						
						<!-- 第三个Pane 游客注册 -->
						<s:if test="type==3">
							<div class="tab-pane fade in active" id="visReg">
						</s:if>
						<s:else>
							<div class="tab-pane fade" id="visReg">
						</s:else>
							<form class="form-horizontal" action="${ctx}/vregister_submit.action"
								method="post">
								<s:hidden name="type" value="3"></s:hidden>
								<div class="form-group">
									<label for="username" class="col-sm-4 control-label">用户名</label>
									<div class="col-sm-6">
										<s:textfield name="username" cssClass="form-control"
											id="username" />
									</div>
								</div>

								<div class="form-group">
									<label for="password" class="col-sm-4 control-label">密码</label>
									<div class="col-sm-6">
										<s:password name="password" cssClass="form-control"
											id="password" />
									</div>
								</div>

								<div class="form-group">
									<label for="passwordAgain" class="col-sm-4 control-label">密码确认</label>
									<div class="col-sm-6">
										<s:password name="passwordAgain" cssClass="form-control"
											id="passwordAgain" />
									</div>
								</div>

								<div class="form-group">
									<label for="email" class="col-sm-4 control-label">Email</label>
									<div class="col-sm-6">
										<s:textfield name="email" cssClass="form-control" id="email" />
									</div>
								</div>
								
								<div class="form-group">
									<label for="code" class="col-lg-4 control-label">验证码</label>
									<div class="col-lg-3">
										<s:textfield name="code" cssClass="form-control" id="code" />
									</div>
									<div class="col-lg-3">
										<img class="img-thumbnail" src="common/code.jsp" onClick="this.src='common/code.jsp?'+Math.random()" data-toggle="tooltip" data-placement="right" title="点击可以切换图片" style="margin-top: 1px">
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-6">
										<s:submit cssClass="button button-flat-primary button-tiny" style="height:32px" value="注册" />
									</div>
								</div>
							</form>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<%@include file="/common/footer.jsp"%>
</body>
</html>