<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
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
    <jsp:include page="include/include.jsp" />
    <title>공통 | 로그인</title>
    <script type="text/javascript">
    	var fnLogin = function (){
    		if(StringUtil.isNull($("#userId").val())){
    			
    			alert("ID(Email Address) does not exist");
    			return;
    		}
    		
			if(StringUtil.isNull($("#pass").val())){
    			
    			alert("ID(Email Address) does not exist");
    			return;
    		}
    		
			var param = {
				"url"          : "login.ajax"	
			  ,	"data"         : {  "userId" : $("#userId").val()
					              , "pass"   : $("#pass").val()
					             }
	          , "succCallback" : function(data){
	        	                     if(data.hasOwnProperty("userId") && !StringUtil.isNull(data.userId)){
	        	                    	 
	        	                    	 location.href="main.do";
	        	                     }else{
	        	                    	 alert("Please check your email or password");
	        	             			 return;
	        	                    	 
	        	                     }
	                             } 
			};
			AjaxUtil.send(param);
    	}
    </script>
</head>
<body>
<div id="loginWrap">
    <div id="header">
        <h1 id="ci"><a href="#">DASAN</a></h1>
    </div>

    <div id="loginBox">
        <div id="login-in">
            <fieldset>
                <legend>로그인영역</legend>
                <h2>Login</h2>

                <div class="formbox">
                    <label for="">ID(Email)</label>
                    <div class="form-inp"><input id="userId" type="text" class="inp" placeholder="Enter Email"></div>
                </div>
                <div class="formbox">
                    <label for="">Password</label>
                    <div class="form-inp"><input id="pass" type="password" class="inp" placeholder="Enter PassWord"></div>
                </div>

                <div id="loginOp" class="mt30">
                    <span class="chkbox">
                        <input type="checkbox" id="th001">
                        <label for="th001" class="th_chk">Auto Login</label>
                    </span>
                    <div class="user-find">
                        <a href="#">Find ID</a>
                        <a href="#">Find PassWord</a>
                    </div>
                </div>
                <a href="#" class="loginBtn"  onclick="fnLogin();">Login</a>

            </fieldset>
        </div>
    </div>
</div>
</body>
</html>