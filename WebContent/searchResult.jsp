<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<title>课件网</title>

</head>
<body style="padding-top: 50px">
	<%@include file="/common/top.jsp"%>
	<!-- 判断用户是否登陆检查 -->
	<s:hidden name="iflogin" value="iflogin"/>
	<s:if test="iflogin eq 'false'">
		<%@include file="/slogin.jsp"%>
	
	</s:if>
	
	<div class="container">
		<p>&nbsp</p>
		<h3 style="font-family: 幼圆 ;">&nbsp课件仓库</h3>
		<div class="row">
			<div class="col-lg-12 col-md-12">
				<form role="form" action="${ctx}/search_search" method="post" id="searchbox">
					<div class="form-group col-lg-6 col-md-6">
						<label for="exampleInputEmail1"></label> 
						<input type="text" class="form-control" name="input" value="${sessionScope.search}">
							
						<hr>
					</div>
					<button type="submit" class="button button-flat-primary button-tiny" style="height:32px">
						<span class="glyphicon glyphicon-search"></span>&nbsp&nbsp学习一下
					</button>
				</form>
			</div>
		</div>
		
		
		<div class="row">
			<div class ="col-lg-8 col-md-8">
				<s:if
					test="fieldErrors != null && fieldErrors.size > 0 || actionErrors != null && actionErrors.size > 0">
					<div class="alert alert-success">
						<s:fielderror />
					</div>
				</s:if>
				<s:iterator value="srlist" var="item">
					<s:url value="/search_present" id="present">
						<s:param name="position" value="%{nameInDisk}"></s:param>
						<s:param name="content" value="%{content}"></s:param>
						<s:param name="price" value="%{price}"></s:param>
					</s:url>
					<s:a href="%{present}" cssClass="btn btn-link">
						<span style="font-family: 微软雅黑;font-size:20px"><s:property value="content"/></span>
					</s:a>
					<s:if test="#session.currentUser != null && #session.stuUser!=null">
						<s:if test="type == 0">
							<s:url value="/search_follow" id="follow">
								<s:param name="pptId" value="%{pptId}"/>
								<s:param name="type" value="%{type}"/>
							</s:url>
							<span>　　</span>
							<s:a href="%{follow}">
								<span class="glyphicon glyphicon-star-empty" style="font-size:23px; color:#FFCC00"></span>
							</s:a>
						</s:if>
						<s:else>
							<s:url value="/search_cancelFollow" id="cancelFollow">
								<s:param name="pptId" value="%{pptId}"/>
								<s:param name="type" value="%{type}"/>
							</s:url>
							<span>　　</span>
							<s:a href="%{cancelFollow}">
								<span class="glyphicon glyphicon-star" style="font-size:23px; color:#FFCC00"></span>
							</s:a>
						</s:else>
					</s:if>

					<s:url value="/download_down" id="download">
						<s:param name="filePath" value="%{nameInDisk}"/>
						<s:param name="fileName" value="%{content}"/>
						<s:param name="price" value="%{price}"/>
					</s:url>
					
					
					<br><span>&nbsp&nbsp&nbspThis is some information about this document. 
					    If you like this document, please click GREAT</span><br>
					<span>&nbsp&nbsp&nbspfor it.Welcome to visit our web site</span>
					<br/>
					<span style="font-family:宋体;color:#7F7F7F">&nbsp&nbsp上传时间&nbsp<s:property value="creTime"/></span>
					<span style="font-family:宋体;color:#7F7F7F">&nbsp&nbsp下载次数&nbsp<s:property value="clickCount"/></span>
					<br/>
					<span style="font-family:宋体;color:#008000">&nbsp&nbsp<s:property value="nameInDisk"/></span>
					<s:a href="%{download}" cssClass="btn btn-link">
						<span style="font-family: 宋体;font-size:15px;color:#0066EE">点此下载</span>
					</s:a>
					<span style="font-family:宋体;color:#7F7F7F">需要金币数&nbsp<s:property value="price"/></span>  
					<br/><br/>
				</s:iterator>
			</div>
		</div>
		
	</div>


	<%@include file="/common/footer.jsp"%>
</body>
</html>