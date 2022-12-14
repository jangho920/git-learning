<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.dasan.smartop.cmmn.session.SessionVO" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
	<div id="header">
		<h1 id="ci"><a href="#">DASAN</a></h1>
        <div id="userInfo">
            <!-- user info -->
            <div id="user-i">
                <strong id="user-nm">Hello, <c:out value="${sessionScope.sessionVO.userNm}" /></strong><span id="user-pos"><c:out value="${sessionScope.sessionVO.userSecNm}" /></span><br>
                <span id="user-eml"><c:out value="${sessionScope.sessionVO.userId}" /></span>
            </div>
            <!-- user controll btn -->
            <div id="user-c">
                <a href="#" class="btn btn-sz1 bgColor1">My page</a>
                <a href="<c:url value="/logout.ajax" />" class="btn btn-sz1 bgColor2">Logout</a>
            </div>
        </div>
	</div>