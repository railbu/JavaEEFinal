<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/carousel.js"></script>
<link href="${ctx}/resources/style/mainstyle.css" rel="stylesheet">
<link href="${ctx}/resources/style/modern.css" rel="stylesheet">
<title>教师|个人中心</title>
</head>
<body style="padding-top: 50px;margin:0 15px;">
	<%@include file="/common/top.jsp"%>
	
	<div class="container">
		<div class="row" style="padding-top: 100px;">

			<%@include file="/teacher/navi.jsp"%>
			<div class="col-xs-12 col-md-6">
				<div class="maincontent">
				<fieldset>
					<legend>最近更新</legend>
					<div class="span5" style="margin-left:42px">
					    <div class="carousel span5" style="height: 300px;" data-role="carousel" data-param-effect="slide" data-param-direction="left" data-param-duration="2000" data-param-period="4000">
					        <div class="slides">
					            <div id="slide1" class="slide image" style="left: 0px;">
					                <img src="${ctx}/resources/photo/5.png"></img>
					                <div class="description">fdsafasdf</div>
					            </div>
					            <div id="slide2" class="slide image" style="display: block; left: -380px;">
					                <img src="${ctx}/resources/photo/2.png"></img>
					                <div class="description">fdsafasdf</div>
					            </div>
					            <div id="slide3" class="slide image" style="left: -380px;">
					                <img src="${ctx}/resources/photo/3.png"></img>
					                <div class="description">fdsafasdf</div>
					            </div>
					        </div>
					        <span class="control left bg-color-blue">‹</span>
					        <span class="control right bg-color-blue">›</span>
					        <div class="markers">
					            <ul>
					                <li class="">
					                    <a href="javascript:void(0)" data-num="0"></a>
					                </li>
					                <li class="active">
					                    <a href="javascript:void(0)" data-num="1"></a>
					                </li>
					                <li>
					                    <a href="javascript:void(0)" data-num="2"></a>
					                </li>
					            </ul>
					        </div>
					        <div class="markers">
					            <ul>
					                <li class="active">
					                    <a data-num="0" href="javascript:void(0)"></a>
					                </li>
					                <li class="">
					                    <a data-num="1" href="javascript:void(0)"></a>
					                </li>
					                <li class="">
					                    <a data-num="2" href="javascript:void(0)"></a>
					                </li>
					            </ul>
					        </div>
					    </div>
					</div>
					</fieldset>
				</div>
			</div>
			<div class="col-xs-12 col-md-3">
				<div class="maincontent">
					<fieldset>
						<legend>个人档</legend>
						<ul style="list-style:disc;">
							<li class="myul"><i class="ui-icon sp-detail-avatar"></i>　　${sessionScope.currentUser}　　男</li>
							<li class="myul"><i class="ui-icon sp-detail-type"></i>　　老师 </li>
							<li class="myul"><i class="ui-icon sp-detail-school"></i>　　${sessionScope.teacher.school} </li>
							<li class="myul" style="border-bottom:0px"><i class="ui-icon sp-detail-honor"></i>　　打篮球,编程 </li>
						</ul>
					</fieldset>
				</div>
			</div>
			
		</div> <!-- row -->
	</div> <!-- container -->
	
	<%@include file="/common/footer.jsp"%>

</body>
</html>