<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<div class="col-xs-12 col-md-2 col-md-offset-1">
			<div class="maincontent">
			<ul class="nav nav-pills nav-stacked">
				<%-- <li class="${fn:contains(pageContext.request.servletPath,'teacherHome') ? 'active' : ''}">
					<a href="${ctx}/teacher/teacherHome.jsp"><span class="glyphicon glyphicon-home"></span> &nbsp我的信息</a>
				</li> --%>
				<li class="${fn:contains(pageContext.request.servletPath,'Course') ? 'active' : ''}">
					<a href="${ctx}/teacher/course_list.action"><span class="glyphicon glyphicon-th-list"></span> &nbsp课程管理</a>
				</li >
				<li class="${fn:contains(pageContext.request.servletPath,'PPT') ? 'active' : ''}">
					<a href="${ctx}/teacher/uploadFileLoad_load.action"><span class="glyphicon glyphicon-open"></span> &nbsp上传课件</a>
				</li>
				<li class="${fn:contains(pageContext.request.servletPath,'pptMa') ? 'active' : ''}">
					<a href="${ctx}/teacher/pptMa_list.action"><span class="glyphicon glyphicon-list-alt"></span> &nbsp课件管理</a>
				</li>
				<li class="${fn:contains(pageContext.request.servletPath,'Hwork') ? 'active' : ''}">
					<a href="${ctx}/teacher/hworkMa_list"><span class="glyphicon glyphicon-edit"></span> &nbsp布置作业</a>
				</li>
				<li class="${fn:contains(pageContext.request.servletPath,'Student') ? 'active' : ''}">
					<a href="${ctx}/teacher/courstu_list"><span class="glyphicon glyphicon-user"></span> &nbsp学生管理</a>
				</li>
			</ul>
		</div>
		</div>
	