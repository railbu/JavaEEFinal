<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/style/mainstyle.css" rel="stylesheet">
<title>学生管理-${SessionScope.CurrentUser}</title>
<style type="text/css">
.file-box {
	position: relative;
	width: 340px
}

.txt {
	height: 22px;
	border: 1px solid #cdcdcd;
	width: 220px;
	height: 30px;
}

.btnn {
	background-color: #FFF;
	border: 1px solid #CDCDCD;
	height: 30px;
	width: 70px;
}

.file {
	position: absolute;
	top: 0;
	height: 60px;
	filter: alpha(opacity :   0);
	opacity: 0;
	width: 230px
}

.filediv {
	padding: 4px 4px 4px 4px;
	border: 1px solid #CDCDCD;
	width: 230px;
	height: 70px;
}

iframe {
	border: none;
	width: 0;
	height: 0;
}

#p_out {
	width: 200px;
	height: 12px;
	margin: 10px 0 0 0;
	padding: 1px;
	font-size: 10px;
	border: solid #6b8e23 1px;
}

#p_first {
	width: 0%;
	height: 100%;
	background-color: red;
	margin: 0;
	padding: 0;
}

#p_second {
	width: 0%;
	height: 100%;
	background-color: blue;
	margin: 0;
	padding: 0;
}

#dis {
	margin: 0;
	padding: 0;
	text-align: center;
	font-size: 12px;
	height: 12px;
	width: 200px;
}
</style>
</head>
<body style="padding-top: 50px; margin: 0 15px;">
	<%@include file="/common/top.jsp"%>

	<div class="container">
		<div class="row" style="padding-top: 100px;">

			<%@include file="/teacher/navi.jsp"%>

			<div class="col-xs-12 col-md-8">
			<div class="maincontent">
				<fieldset>
				<legend>上传课件</legend>
				<s:if test="message != null">
					<div class="alert alert-<s:property value="message.type"/>">
						<s:property value="message.content" />
					</div>
				</s:if>

				<form class="form-horizontal" id="uploadfile_form"
					name="uploadfile_form" enctype="multipart/form-data" method="post"
					target="uploadfile_iframe">

					<div class="form-group">
						<label for="tname" class="col-lg-2 col-md-2 control-label">课程名</label>
						<div class="col-lg-3 col-md-3">
							<s:select id="clist" list="clist" cssClass="form-control" name="cid"
								listKey="id" listValue="name" headerKey=""
								headerValue="--请选择一门课程--"></s:select>
						</div>
					</div>

					<div class="form-group">
						<label for="file" class="col-lg-2 col-md-2 control-label">选择文件</label>
						<div class="col-lg-3 col-md-3">
							<div class="filediv">
								<input type='text' name='textfield' id='textfield' class='txt'
									onchange="document.getElementById('pptName').value=this.value" />
								<input type='button' class='btnn' value='浏览' /> <input
									class="file" type="file" name="file"
									onchange="autoName(this.value)" /> <br>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="tname" class="col-lg-2 col-md-2 control-label">请输入课件名</label>
						<div class="col-lg-3 col-md-3">
							<s:textfield name="pname" id="pname" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="price" class="col-lg-2 col-md-2 control-label">价格</label>
						<div class="col-lg-3 col-md-3">
							<s:textfield name="price" id="price" cssClass="form-control" />
						</div>
					</div>
					
					
					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-5 col-md-5 col-md-offset-2">
							<button class="button button-flat-primary button-tiny" style="height:32px;" onclick="progress()">提交</button>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-5 col-lg-md-2 col-md-5">
							<div class="progress progress-striped active">
								<div id="pbar" class="progress-bar" role="progressbar" aria-valuenow="0"
									aria-valuemin="0" aria-valuemax="100" style="width: 0%">
								</div>
							</div>
							<div id="dis"></div>
						</div>
					</div>


				</form>
				<iframe frameborder="0" id="uploadfile_iframe"
					name="uploadfile_iframe"></iframe>
				<iframe frameborder="0" id="progress_iframe" name="progress_iframe"></iframe>
				</fieldset>
			</div>
		</div>
		</div>
		<!-- row -->
	</div>
	<!-- container -->

	<%@include file="/common/footer.jsp"%>

</body>
<script type="text/javascript">
    //上传文件  
    function uploadFile(key){
    	var pname = document.getElementById('pname').value;
    	var pnameCode= encodeURIComponent(pname);

    	var price = document.getElementById('price').value;
    	var cid = document.getElementById("clist").options[document.getElementById("clist").selectedIndex].value;
        document.getElementById('uploadfile_form').action = 'uploadfile.action?callback=parent.upload&key='+key
        								+'&pname='+pnameCode+"&cid="+cid+"&price="+price;
        
        document.getElementById('uploadfile_form').submit();  
        document.getElementById('dis').innerHTML = "开始传送数据...";     
    }  
   
    //获取文件上传进度  
    function progress(){
        document.getElementById('progress_iframe').src = 'progress.action?callback1=parent.uploadFile&callback2=parent.upload';
        document.getElementById('dis').innerHTML = '初始化数据...';
        document.getElementById('pbar').style.width = "0%";
        var pbar = document.getElementById('pbar');
        pbar.setAttribute("aria-valuenow","0");
    }  
   
    //更新进度  
    function upload(len, total){  
        document.getElementById('pbar').style.width = (Math.round(len/total*100))+'%';
        var pbar = document.getElementById('pbar');
        pbar.setAttribute("aria-valuenow",(Math.round(len/total*100)));
        
        document.getElementById('dis').innerHTML = Math.round(len/1024) + '/' + Math.round(total/1024) + 'KB';  
        if(len === total) {  
            document.getElementById('dis').innerHTML = "文件上传完成!";
            winhref(2);
        }  
    }
    
    function winhref(x){
    	var i = x;
    	if(i == 0){
    		alert("PPT上传成功");
    		window.location.href="uploadFileLoad_load.action";
    	}
    	else{
    		i--;
			setTimeout("winhref("+i+")",500);
    	}
    }
    
    function autoName(name){
    	document.getElementById('textfield').value='   '+name;
    	var ts = name.lastIndexOf("\\");
    	if(ts != -1){
    		var name = name.substring(ts+1);
    	}
    	document.getElementById('pname').value=name;
    }
	</script>
</html>








