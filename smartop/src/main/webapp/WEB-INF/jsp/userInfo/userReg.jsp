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
        <title>User Registration</title>
    
        <%-- <jsp:include page="../include/include.jsp" />   --%>
        <script type="text/javascript">
            $('document').ready(function(){
            	fnInit();
        	});
            
            var fnInit = function(){
            	<c:if test="${data.menuParam == '110'}">
            	$("#divDealerId").css("display", "none")
            	$("#divBrandId").css("display",  "none")
            	$("#divStorId").css("display",   "none")
            	$("#userSecCd").val("10");
            	</c:if>
            	<c:if test="${data.menuParam == '120'}">
           		$("#divDealerId").css("display", "");
               	$("#divBrandId").css("display",  "none")
               	$("#divStorId").css("display",   "none")
               	$("#userSecCd").val("20");
               	fnSelectDealerList();
            	</c:if>
            	<c:if test="${data.menuParam == '140'}">
           		$("#divDealerId").css("display", "");
                $("#divBrandId").css("display",  "");
                $("#divStorId").css("display",   "");
                $("#userSecCd").attr("disabled", false);
                $("#dealerId").attr("disabled",  false);
                $("#brandId").attr("disabled",   false);
            	$("#storId").attr("disabled",    false);
                fnSelectDealerList();
            	</c:if>
            	<c:if test="${data.menuParam == '240'}">
            	$("#divDealerId").css("display", "");
            	$("#divBrandId").css("display",  "")
            	$("#divStorId").css("display",   "")
            	$("#userSecCd").attr("disabled", false);
            	$("#dealerId").attr("disabled",  true);
            	$("#brandId").attr("disabled",   false);
            	$("#storId").attr("disabled",    false);
            	fnSelectDealerList();
            	</c:if>
            	<c:if test="${data.menuParam == '440'}">
            	$("#divDealerId").css("display", "");
            	$("#divBrandId").css("display",  "");
            	$("#divStorId").css("display",   "");
            	$("#userSecCd").attr("disabled", false);
            	$("#dealerId").attr("disabled",  true);
            	$("#brandId").attr("disabled",   true);
            	$("#storId").attr("disabled",    false);
            	fnSelectDealerList();
            	</c:if>
            }
            
            var fnSelectDealerList = function(){
            	var param = {
                        "url"          : "/cdMm/selectDealerList.ajax"
                      , "id"           : "dealerId"
                      , "default"      : "<c:out value="${dealerId}" />"
                      , "callback"     : comboCallback
                };
                
            	CmmnUtil.selectComboCdList(param);
            	
            }
            
            var fnSelectBrandList = function(){
            	var param = {
                        "url"          : "/cdMm/selectBrandList.ajax"
                      , "data"         : {"dealerId" : $("#dealerId").val()}
                      , "id"           : "brandId"
                      , "default"      : "<c:out value="${brandId}" />"
                      , "callback"     : comboCallback
                };
                
            	CmmnUtil.selectComboCdList(param);
            }
            
            var fnSelectStorList = function(){
            	var param = {
                        "url"          : "/cdMm/selectStorList.ajax"
                      , "data"         : {  "dealerId" : $("#dealerId").val()
                    	                  , "brandId"  : $("#brandId").val()}
                      , "id"           : "storId"
                      , "default"      : "<c:out value="${brandId}" />"
                };
                
            	CmmnUtil.selectComboCdList(param);
            	
            }
            
            var comboCallback = function(param){
            	debugger;
            	if(param.id == "dealerId"){
            		if($("#dealerId").val() != "" && $("#divBrandId").css("display") != ""){
    	            	$("#storId").empty();
    	            	fnSelectBrandList();
                	}
            	}else if(param.id == "brandId"){
            		if($("#dealerId").val() != "" && $("#brandId").val() != "" && $("#divStorId").css("display") != ""){
            			fnSelectStorList();
                	}
            	}else if(param.id == "brandId"){
            	}
            	
            }
            
            $(document).on("change", "#dealerId", function(e){
            	if($("#divBrandId").is(":visible")){
	            	$("#storId").empty();
	            	fnSelectBrandList();
            	}
            });
            
            $(document).on("change", "#brandId", function(e){
            	if($("#divStorId").is(":visible")){
            		fnSelectStorList();
            	}
            });
            
            $(document).on("click", "#btnSave", function(e){
            	if($("#userId").val() == ""){
                	$("#userId").focus();
                	alert("Please enter user ID");
        			return;
        		}
            	
            	if( !ValidUtil.isValidEmail($("#userId").val())){
                	$("#userId").focus();
                	alert("Please enter user ID as email format");
        			return;
        		}
            	
            	if($("#userNm").val() == ""){
                	$("#userNm").focus();
                	alert("Please enter user name");
        			return;
        		}
            	
            	if($("#divDealerId").is(":visible")){
            		if($("#dealerId").val() == ""){
                    	$("#dealerId").focus();
                    	alert("Please select dealer");
            			return;
            		}
            	}
            	
				if($("#divBrandId").is(":visible")){
					if($("#bandId").val() == ""){
                    	$("#bandId").focus();
                    	alert("Please select brand");
            			return;
            		}
            		
            	}
				
                if($("#divStorId").is(":visible")){
                	if($("#storId").val() == ""){
                    	$("#storId").focus();
                    	alert("Please select store");
            			return;
            		}
            		
            	}

            	if($("#email").val() == ""){
                	$("#email").focus();
                	alert("Please enter your email");
        			return;
        		}
            	
            	if( !ValidUtil.isValidEmail($("#email").val())){
                	$("#email").focus();
                	alert("Email Address format is not valid");
        			return;
        		}
            	
            	if($("#telNum1").val() == ""){
                	$("#telNum1").focus();
                	alert("Please enter your phone number");
        			return;
        		}
            	
            	if($("#telNum2").val() == ""){
                	$("#telNum2").focus();
                	alert("Please enter your phone number");
        			return;
        		}
            	
            	if($("#telNum3").val() == ""){
                	$("#telNum3").focus();
                	alert("Please enter your phone number");
        			return;
        		}
            	
            	if(!ValidUtil.isDigit($("#telNum1").val())){
                	$("#telNum1").focus();
                	alert("Only Number Please");
        			return;
        		}
            	
            	if(!ValidUtil.isDigit($("#telNum2").val())){
                	$("#telNum2").focus();
                	alert("Only Number Please");
        			return;
        		}
            	
            	if(!ValidUtil.isDigit($("#telNum3").val())){
                	$("#telNum3").focus();
                	alert("Only Number Please");
        			return;
        		}
            	
                if($("#userPass").val() == ""){
                	$("#userPass").focus();
                	alert("Please enter your password");
        			return;
        		}
                
                if($("#userPass").val().length < 5){
                	$("#userPass").focus();
                	alert("Please enter password with 5-letter words more");
        			return;
        		}
                
                if($("#userPassConfirm").val() == ""){
                	$("#userPassConfirm").focus();
                	alert("Please enter your passWord");
        			return;
        		}
        		
        		if($("#userPass").val() != $("#userPassConfirm").val()){
        			$("#userPassConfirm").focus();
        			alert("Password don't match");
        			return;
        		}
        		
        		if($("#empNum").val().length != 5){
                	$("#empNum").focus();
                	alert("Please enter employee code with 5-letter words");
        			return;
        		}
        		
        		if(!ValidUtil.isDigit($("#empNum").val())){
                	$("#empNum").focus();
                	alert("Only Number Please");
        			return;
        		}
        		
        		if($("#areaNm").val() == ""){
                	$("#areaNm").focus();
                	alert("Please enter your location");
        			return;
        		}
        		
        		var param = {
                        "url"          : "insertUserInfo.ajax"	
                      , "data"         : {  "userId"        : $("#userId").val()
                                          , "userNm"        : $("#userNm").val()
                                          , "userSecCd"     : $("#userSecCd").val()
                                          , "dealerId"      : $("#dealerId").val()
                                          , "brandId"       : $("#brandId").val()
                                          , "storId"        : $("#storId").val()
                    	                  , "email"         : $("#email").val()
                    	                  , "telNum"        : $("#telNum1").val()+"-"+$("#telNum2").val()+"-"+$("#telNum3").val()
                    	                  , "userPass"      : $("#userPass").val()
                    	                  , "empNum"        : $("#empNum").val()
                    	                  , "areaNm"        : $("#areaNm").val()
                                         }
                      , "succCallback" : function(data){
                    	                     alert("It has been saved.");
                    	                     popClose();
                    	                     fnSelectUserList();
                    	                     
                                         } 
                };
                AjaxUtil.send(param);
        		
        	});
            
            $(document).on("click", "#btnCancle", function(e){
            	popClose();
        	});
         </script>  
    </head>
    <body>   
        <div class="bbs-cond flex-md mb15">
            <h1 class="ti mb0">User Registration</h1>
            <div class="cond-btns">
                <button type="button" class="btn btn-sz1 bgColor12 pop-close" id="btnCancle">Close</button>
                <button type="button" class="btn btn-sz1 bgColor1"            id="btnSave">Save</button>
            </div>
        </div>
        
        <div class="cardCont">
            <div class="formbox pt0">
                <label for="userNm" class="req">User ID</label>
                <div class="form-inp"><input type="text" class="inp" id="userId" maxlength="30"></div>
            </div>
            
             <div class="formbox">
                <label for="userNm" class="req">User Name</label>
                <div class="form-inp"><input type="text" class="inp" id="userNm" maxlength="30"></div>
            </div>
            
            <div class="formbox">
                <label for="userSecCd" class="req">User Group</label>
                <div class="form-inp">
                	<div class="form-sel flex-f1">
                		<select id="userSecCd" class="select" disabled>
                        <c:forEach var="userSecCd" items="${userSecCdList}" varStatus="status">
                        <option value="<c:out value="${userSecCd.cmmnDtlCd}" />" <c:if test="${userSecCd == userSecCd.cmmnDtlCd}">selected</c:if>><c:out value="${userSecCd.cmmnDtlNm}" /></option>
                       	</c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            
            <div class="formbox" id="divDealerId" display="none">
                <label for="dealerId" class="req">Dealer</label>
                <div class="form-inp">
                	<div class="form-sel flex-f1">
                		<select id="dealerId" class="select" >
                        </select>
                    </div>
                </div>
            </div>
            
            <div class="formbox" id="divBrandId" display="none">
                <label for="brandId" class="req">Brand</label>
                <div class="form-inp">
                	<div class="form-sel flex-f1">
                		<select id="brandId" class="select" >
                        </select>
                    </div>
                </div>
            </div>
            
            <div class="formbox" id="divStorId" display="none">
                <label for="storId" class="req">Store</label>
                <div class="form-inp">
                	<div class="form-sel flex-f1">
                		<select id="storId" class="select" >
                        </select>
                    </div>
                </div>
            </div>
            
            <div class="formbox">
                <label for="" class="req">Email</label>
                <div class="flex flex-md">
                    <div class="form-inp flex-f1"><input type="text" class="inp" id="email" maxlength="30"></div>
                    <!-- <div class="ml10 mr10">@</div>    
                    <div class="form-sel flex-f1">
                        <select id="eml2" class="select">
                            <option value="">gmail.com</option>
                        </select>
                    </div> -->
                </div>
            </div>
            
            <div class="formbox">
                <label for="phone1">Phone Number</label>
                <div class="formbox3 flex-md">
                    <div class="formCol form-inp"><input type="text" class="inp" id="telNum1" maxlength="4"></div>
                    <div class="formCol form-inp"><input type="text" class="inp" id="telNum2" maxlength="4"></div>
                    <div class="formCol form-inp"><input type="text" class="inp" id="telNum3" maxlength="4"></div>
                </div>
            </div>
            
            <div class="formbox">
                <label for="userPass" class="req">PassWord</label>
                <div class="form-inp"><input type="password" class="inp" id="userPass" maxlength="12"></div>
            </div>
            
            <div class="formbox">
                <label for="userPassConfirm" class="req">Confirm PassWord</label>
                <div class="form-inp"><input type="password" class="inp" id="userPassConfirm" maxlength="12"></div>
            </div>
            
            <div class="formbox">
                <label for="empNum" class="req">Employee Code</label>
                <div class="form-inp"><input type="text" class="inp" id="empNum" maxlength="5"></div>
                <p class="copy ts-s mt5">It will be used when using POS.</p>
            </div>
            
            <div class="formbox">
                <label for="areaNm" class="req">Location</label>
                <div class="form-inp"><input type="text" class="inp" id="areaNm" maxlength="10"></div>
            </div>
        </div>
    </body>
</html>
