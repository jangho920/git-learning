<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../include/include.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="title" content="DASAN">
    <meta name="description" content="">
    <meta property="og:title" content="">
    <meta property="og:description" content="">
    <title>SMART ORDER PLATFORM</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="header.jsp" />

    <div id="container">
        <!-- lnb [S] -->
        <jsp:include page="menu.jsp"/>
        <!-- lnb [E] -->

        <!-- contents Area [S] -->
        <div id="contents" name="right">
            <!-- contents in [S] -->
			<iframe src="" name="content" width="100%" height="100%" frameborder="0" sandbox="allow-same-origin allow-popups allow-scripts allow-modals"/>

            <!-- contents in [E] -->
        </div>
        <!-- contents Area [E] -->
    </div>
</div>


<!-- 팝업창 띄울대 딤드참 뒷 배경 -->
<div class="mask"></div>
</body>
</html>