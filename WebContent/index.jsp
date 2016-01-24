<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<title>课件网</title>
</head>
<body
	style="padding-top: 50px; background: none repeat scroll 0% 0% rgb(239, 238, 228)">
	<%@include file="/common/top.jsp"%>

	<!-- 大屏展示版块 -->
	<div class="jumbotron"
		style="position: relative; min-height: 400px; text-align: center; background: none no-repeat scroll center 0px/cover rgb(34, 34, 34); background-image: url('resources/images/background1.jpg')">
		<div class="container">
			<br>
			<p>&nbsp</p>
			<h1
				style="font-family: 幼圆; color: rgb(255, 255, 255); font-weight: 700;">课件仓库</h1>
			<div class="row" style="text-align: center;">
				<div class="col-md-6 col-md-offset-3">
					<form role="form" action="${ctx}/search_search" method="post">
						<br>
						<div class="input-group" style="width: auto">
							<label for="exampleInputEmail1"></label> 
							<input type="text"
								class="form-control input-lg" placeholder="Start Searching..."
								name="input"> <br> 
							<span class="input-group-btn">
							<input class="btn btn-success btn-lg" type="submit"
								value="Search"></input>
							</span>
						</div>

						<!-- <button type="submit" class="btn btn-primary btn-shadow bs3-link" role="button" target="_blank">
							<span class="glyphicon glyphicon-search"></span>&nbsp&nbsp学习一下
						</button> -->
					</form>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-3">
				<h3>热度排行</h3>
				<br>

				<table class="table"">
					<thead>
						<tr>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr class="success">
							<td><s:property value="des[0]" /></td>
						</tr>
						<tr class="warning">
							<td><s:property value="des[1]" /></td>
						</tr>
						<tr class="warning">
							<td><s:property value="des[2]" /></td>
						</tr>
					</tbody>
				</table>

				<p>
					<a class="button button-flat-primary" style="height: 31px" href="#"
						role="button">View details &raquo;</a>
				</p>
			</div>
			<div class="col-md-3 col-md-offset-1">
				<h3>最新上传</h3>
				<br>
				<table class="table"">
					<thead>
						<tr>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr class="success">
							<td><s:property value="des[0]" />.ppt</td>
						</tr>
						<tr class="warning">
							<td><s:property value="des[1]" />.ppt</td>
						</tr>
						<tr class="warning">
							<td><s:property value="des[2]" />.ppt</td>
						</tr>
					</tbody>
				</table>
				<p>
					<a class="button button-flat-primary" style="height: 31px;"
						href="#" role="button">View details &raquo;</a>
				</p>
			</div>
			<div class="col-md-3 col-md-offset-1">
				<h3>教学名师</h3>
				<br>
			<table class="table"">
					<thead>
						<tr>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr class="success">
							<td>TeacherA</td>
						</tr>
						<tr class="warning">
							<td>TeacherB</td>
						</tr>
						<tr class="warning">
							<td>TeacherC</td>
						</tr>
					</tbody>
				</table>
				<p>
					<a class="button button-flat-primary" style="height: 31px" href="#"
						role="button">View details &raquo;</a>
				</p>
			</div>
		</div>
	</div>
	<%@include file="/common/footer.jsp"%>
</body>
</html>