<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>404</title>
		<script type="text/javascript">
			function fncGoAfterErrorPage(){
				history.back(-2);
			}
		</script>
	</head>
	<body>
		<div style="width: 1000px; margin: 50px auto 50px;">
			<div style="border:0px solid #666; padding:20px;">
				<p style="color:red; margin-bottom:8px; " >404 Error</p>
				
				<div style="width: 500px">
					<div>
						<div>
							<p class="title " >HTTP 404 Error</p>
							<p class="cont mb20 " >웹페이지를 찾을 수 없습니다.<br/></p>
							<span class=""><a href="fncGoAfterErrorPage();">이전 페이지</a></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>