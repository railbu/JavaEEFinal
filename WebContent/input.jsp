<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/style/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/style/icon.css">
	<style type="text/css">
		.item-img{
			display:inline-block;
			vertical-align:middle;
			width:16px;
			height:16px;
		}
		.item-text{
			display:inline-block;
			vertical-align:middle;
			padding:3px 0 3px 3px;
		}
	</style>
	<script type="text/javascript" src="resources/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="resources/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#cc').combobox({
				formatter:function(row){
					var imageFile = 'resources/photo/' + row.icon;
					return '<img class="item-img" src="'+imageFile+'"/><span class="item-text">'+row.text+'</span>';
				}
			});
		});
	</script>
</head>
<body>
	<s:textfield id="cc" style="width:130px; height:33px" name="photoId"
			url="resources/data/combobox_data.json"
			valueField="id"  />
</body>
</html>