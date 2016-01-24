<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<div class="col-xs-12 col-md-2 col-md-offset-1" style="width:${fn:contains(pageContext.request.servletPath,'ppt') ? 'auto' : '210px'}">
			<div class="maincontent">
			<ul class="nav nav-pills nav-stacked">
				<li class="${fn:contains(pageContext.request.servletPath,'studentHome') ? 'active' : ''}">
					<a href="${ctx}/student/home_stuhome.action"><span class="glyphicon glyphicon-home"></span> &nbsp我的主页</a>
				</li>
				<li class="${fn:contains(pageContext.request.servletPath,'Table') ? 'active' : ''}">
					<a href="${ctx}/student/table_list.action"><span class="glyphicon glyphicon-calendar"></span> &nbsp我的课表</a>
				</li >
				<li class="${fn:contains(pageContext.request.servletPath,'follow') ? 'active' : ''}">
					<a href="${ctx}/student/stuppt_followList.action"><span class="glyphicon glyphicon-star"></span> &nbsp我的收藏</a>
				</li>
				<li class="${fn:contains(pageContext.request.servletPath,'ppt') ? 'active' : ''}">
					<a href="${ctx}/student/pptMa_list.action"><span class="glyphicon glyphicon-list-alt"></span> &nbsp教学课件</a>
				</li>
				<%-- <li  class="${fn:contains(pageContext.request.servletPath,'Ppt') ? 'active' : ''}">
					<a href="${ctx}/student/stuppt_list.action"><span class="glyphicon glyphicon-list-alt"></span> &nbsp我的课件</a>
				</li> --%>
				<li class="${fn:contains(pageContext.request.servletPath,'Hwork') ? 'active' : ''}">
					<a href="${ctx}/student/stuHwork_listUnRead.action"><span class="glyphicon glyphicon-edit"></span>
					  &nbsp我的作业<span class="badge pull-right"><s:property value="#session.tips5"/></span>
					</a>
				</li>
			</ul>
			</div>
		</div>
	