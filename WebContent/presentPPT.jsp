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
	
	<s:if test="iflogin eq 'false'">
		<%@include file="/slogin2.jsp"%>
	</s:if>
	
	<div class="container">
		<div class="row">
			<br><br><br><br>
			<div class ="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2">
				<s:iterator value="piclist" var="item">
					<span style="font-family: 宋体;font-size:20px;color:#000000">
						第<s:property value="index"/>页
					</span>
					<img src="/JavaEEFinal/<s:property value="url"/>" alt="加载失败" class="img-thumbnail">
					<br><br><br><br>
				</s:iterator>
					<span style="font-family: 宋体;font-size:20px;color:#000000">
						第 6 页<br/>
						... ...<br/><br/>
					</span>
				<s:url value="%{nameIndisk}" id="dd">
				</s:url>
				
				<s:url value="/download_downIn" id="downloadppt">
						<s:param name="filePath" value="%{nameIndisk}"/>
						<s:param name="fileName" value="%{content}"/>
						<s:param name="price" value="%{price}"/>
				</s:url>
				
				<!-- 暂时没用 -->
				<s:url value="/download_downIn" id="downloadPDF">
						<s:param name="filePath" value="%{pdfNameIndisk}"/>
						<s:param name="fileName" value="%{pdfname}"/>
						<s:param name="price" value="%{price}"/>
				</s:url>
				
				<s:url value="%{pdfNameIndisk}" id="downloadP">
				</s:url>
				
				<span style="font-family: 宋体;font-size:15px;color:#000000">想查看更多内容？</span>
				<s:a href="%{downloadppt}" cssClass="btn btn-link">
					<span style="font-family: 宋体;font-size:15px;color:#0066EE">点此下载</span>
				</s:a>
				<br>
				<s:a href="%{downloadP}" cssClass="btn btn-link">
					<span style="font-family: 宋体;font-size:15px;color:#0066EE">下载PDF版本</span>
				</s:a>
				 <span style="font-family:宋体;color:#E3310B">需要金币数&nbsp<s:property value="price"/></span>
				 <br>
				
				
				<img src="/JavaEEFinal/<s:property value="dcPicPostion"/>" alt="加载失败" class="img-thumbnail">   
			</div>
			
		</div>
		
	</div>


	<%@include file="/common/footer.jsp"%>
</body>
</html>