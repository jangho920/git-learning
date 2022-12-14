<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="include/include.jsp" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>SMART ORDER PLATFORM</title>
		<script type="text/javascript">
			var fnUpload = function (){
			
				var param = {
					  "url"          : "/uploadFile.do"
					, "fileId"       : "imgFile"
					, "succCallback" : fileCallback
				};
				AjaxUtil.sendFile(param);
			};
			
			var fileCallback = function (data){
				console.log("fileCallback:"+data );
				
			};
			/*var fnFindId = function (){
				
				
			}
			
			var fnFindPass = function (){
				
				
			}*/
			
		</script>
	</head>
	<body>
		<form name="frm" method="post" enctype="multipart/form-data">
		<input type="file" id="imgFile">
		<input type="button" onclick="fnUpload();">
		</form>
	</body>
</html>