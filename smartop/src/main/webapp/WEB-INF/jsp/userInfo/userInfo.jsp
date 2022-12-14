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
        <title>User Modification</title>
    
        <%-- <jsp:include page="../include/include.jsp" />   --%>
        <script type="text/javascript">
        	var userInfo;
            $('document').ready(function(){
            	fnInit();
        	});
            
            var fnInit = function(){
            	if('<c:out value="${data.menuParam}" />' == '110'){
	            	$("#divDealerId").css("display", "none")
	            	$("#divBrandId").css("display",  "none")
	            	$("#divStorId").css("display",   "none")
            	}else if('<c:out value="${data.menuParam}" />' == '120'){
	           		$("#divDealerId").css("display", "");
	               	$("#divBrandId").css("display",  "none")
	               	$("#divStorId").css("display",   "none")
            	}else if('<c:out value="${data.menuParam}" />' == '140'){
	           		$("#divDealerId").css("display", "");
	                $("#divBrandId").css("display",  "");
	                $("#divStorId").css("display",   "");
	            }else if('<c:out value="${data.menuParam}" />' == '240'){
	            	$("#divDealerId").css("display", "");
	            	$("#divBrandId").css("display",  "")
	            	$("#divStorId").css("display",   "")
	            	
            	}else if('<c:out value="${data.menuParam}" />' == '440'){
	            	$("#divDealerId").css("display", "");
	            	$("#divBrandId").css("display",  "");
	            	$("#divStorId").css("display",   "");
            	}
            	
            	$("#userSecCd").attr("disabled", true);
            	
            	fnSelectUserInfo();
            }
            
            var fnSelectUserInfo = function(){
            	var param = {
                        "url"          : "/userInfo/selectUserInfo.ajax"
                        	, "data"   : {"userId" : '<c:out value="${data.userId}" />'}
                      , "succCallback" : succCallback
                };
                
            	AjaxUtil.send(param);
            	
            }
            
            var succCallback = function(param){
            	userInfo = param;
            	
            	$("#userId").val(userInfo.userId);
            	$("#userNm").val(userInfo.userNm);
            	$("#email").val(userInfo.email); 
            	if(!StringUtil.isNull(userInfo.telNum) && userInfo.telNum.split("-").length == 3){
            		$("#telNum1").val(userInfo.telNum.split("-")[0]); 
                	$("#telNum2").val(userInfo.telNum.split("-")[1]); 
                	$("#telNum3").val(userInfo.telNum.split("-")[2]); 	
            	}
            	$("#areaNm").val(userInfo.areaNm); 
            	$("#useYn").val(userInfo.useYn);
            	
            	if(userInfo.useYn == "N"){
            	    $("#userNm").attr("disabled", true);
            	    $("#email").attr("disabled", true);
            	    $("#telNum1").attr("disabled", true);
            	    $("#telNum2").attr("disabled", true);
            	    $("#telNum3").attr("disabled", true);
            	    $("#areaNm").attr("disabled", true);
            	}
            	if(!StringUtil.isNull(userInfo.dealerId)){
            		fnSelectDealerList();
            	}

            }
            
            var fnSelectDealerList = function(){
            	var param = {
                        "url"          : "/cdMm/selectDealerList.ajax"
                      , "id"           : "dealerId"
                      , "default"      : userInfo.dealerId
                      , "callback"     : comboCallback
                };
                
            	CmmnUtil.selectComboCdList(param);
            	
            }
            
            var fnSelectBrandList = function(){
            	var param = {
                        "url"          : "/cdMm/selectBrandList.ajax"
                      , "data"         : {"dealerId" : $("#dealerId").val()}
                      , "id"           : "brandId"
                      , "default"      : userInfo.brandId
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
                      , "default"      : userInfo.storId
                      , "callback"     : comboCallback
                };
                
            	CmmnUtil.selectComboCdList(param);
            	
            }
            
            var comboCallback = function(param){

            	if(param.id == "dealerId"){
            		if($("#dealerId").val() != "" && $("#divBrandId").css("display") != ""){
            			
    	            	$("#storId").empty();
    	            	fnSelectBrandList();
                	}
            		
            		$("#dealerId").attr("disabled", true);
            	}else if(param.id == "brandId"){
            		if($("#dealerId").val() != "" && $("#brandId").val() != "" && $("#divStorId").css("display") != ""){
            			fnSelectStorList();
                	}
            		$("#brandId").attr("disabled", true);
            		
            	}else if(param.id == "storId"){
            		$("#storId").attr("disabled", true);
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
            	
        		if($("#areaNm").val() == ""){
                	$("#areaNm").focus();
                	alert("Please enter your location");
        			return;
        		}
        		
        		var param = {
                        "url"          : "udateUserInfo.ajax"	
                      , "data"         : {  "userNm"        : $("#userNm").val()
                                          , "dealerId"      : $("#dealerId").val()
                                          , "bandId"        : $("#bandId").val()
                                          , "storId"        : $("#storId").val()
                    	                  , "email"         : $("#email").val()
                    	                  , "telNum"        : $("#telNum1").val()+"-"+$("#telNum2").val()+"-"+$("#telNum3").val()
                    	                  , "areaNm"        : $("#areaNm").val()
                                         }
                      , "succCallback" : function(data){
                    	                     alert("저장되었습니다.");
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
            <h1 class="ti mb0">User Modification</h1>
            <div class="cond-btns">
                <button type="button" class="btn btn-sz1 bgColor12 pop-close" id="btnCancle">Close</button>
                <button type="button" class="btn btn-sz1 bgColor1"            id="btnSave">Save</button>
            </div>
        </div>
        
        <div class="cardCont">
            <div class="formbox pt0">
                <label for="userNm" class="req">User ID</label>
                <div class="form-inp"><input type="text" class="inp" id="userId" maxlength="30" disabled="disabled"></div>
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
                <label for="areaNm" class="req">Location</label>
                <div class="form-inp"><input type="text" class="inp" id="areaNm" maxlength="10"></div>
            </div>
            
            <div class="formbox">
                <label for="useYn" class="req">Availability</label>
                <div class="form-inp">
                	<div class="form-sel flex-f1">
                		<select id="useYn" class="select" disabled>
                        	<option value="Y">Y</option>
                        	<option value="N">N</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
