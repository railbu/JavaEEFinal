 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation"
	style="background:#41A7C6;border-bottom:0px;">
	
	<div class="container">
		<div class="navbar-header">
	    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	      		<span class="sr-only">Toggle navigation</span>
	      		<span class="icon-bar"></span>
	      		<span class="icon-bar"></span>
	      		<span class="icon-bar"></span>
	    	</button>
	    	<a class="navbar-brand hidden-sm" href="${ctx}/lastaction_last">
	    	<span style="color:white;font-family:迷你简稚艺">课件
	    	</span>
	    	<span style="color:yellow;font-family:迷你简稚艺">仓库
	    	</span></a>
	    	
	  	</div>
	  	
		<div class="collapse navbar-collapse">
			  <form role="form" action="${ctx}/search_search" method="post" class="navbar-form navbar-right">
			  	<c:if test="${sessionScope.currentUser == null}"> 
	            	<a href="${ctx}/login.jsp" class="button button-flat-action button-tiny">登陆</a>
	            	<a href="${ctx}/sregister_input.action" class="button button-flat-action button-tiny">注册</a>
	            </c:if>	
	            
	            <c:if test="${sessionScope.currentUser != null}">
	            	<div class="form-group">
		              <input type="text" class="form-control input-sm" name="input" style="width:250px;">
		            </div>
		            <button type="submit" class="button button-flat-action button-tiny" style="height:30px">
		            	<span class="glyphicon glyphicon-search"></span>
		            </button>
		            &nbsp&nbsp&nbsp&nbsp
	            	<c:if test="${sessionScope.currentUser1.type == 1}">　　　　
		            	<img src="${ctx}/common/image.jsp" width="26px" height="26px" />　
		            	<div class="btn-group">
		            		
		            		<button type="button" class="btn btn-default dropdown-toggle" style="color:#fff; font-weight: bold;background:#41A7C6;text-decoration:underline;border-style:hidden" data-toggle="dropdown">
					        	<a href="${ctx}/student/studentHome.jsp" style="color:#fff; font-weight: bold;">${sessionScope.currentUser1.username} </a><span class="caret" style="color:#fff"></span>
				            </button>
						    <ul class="dropdown-menu" role="menu">
							    <li><a href="${ctx}/student/home_stuhome.action">个人资料</a></li>
							    <li><a href="${ctx}/student/table_list.action">我的课表</a></li>
						    	<li>
									<a href="${ctx}/student/home_input.action">帐号设置</a>
								</li>
							    <li class="divider"></li>
							    <li><a href="${ctx}/logout.jsp">退出</a></li>
						    </ul>
						</div>
	            	</c:if>
	            	<c:if test="${sessionScope.currentUser1.type == 2}">　　　　
		            	<img src="${ctx}/common/image.jsp" width="26px" height="26px" />　
		            	<div class="btn-group">
		            		
		            		<button type="button" class="btn btn-default dropdown-toggle" style="color:#fff; font-weight: bold;background:#41A7C6;text-decoration:underline;border-style:hidden" data-toggle="dropdown">
					        	<a style="color:#fff;font-weight: bold;">${sessionScope.currentUser1.username} </a><span class="caret" style="color:#fff"></span>
				            </button>
						    <ul class="dropdown-menu" role="menu">
							    <li><a href="${ctx}/teacher/home_teahome.action">个人资料</a></li>
							    <li><a href="${ctx}/teacher/home_input.action">帐号设置</a></li>
							    <li class="divider"></li>
							    <li><a href="${ctx}/logout.jsp">退出</a></li>
						    </ul>
						</div>
	            	</c:if>
	            	
		        	<%-- <a href="${ctx}/logout.jsp" style="color:white;">　退出</a> --%>
	            </c:if>
	            
	          </form>
		</div>
	</div>
</nav>


