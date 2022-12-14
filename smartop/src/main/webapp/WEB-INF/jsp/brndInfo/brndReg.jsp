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
        <title>Brand Registration</title>
    
        <%-- <jsp:include page="../include/include.jsp" />   --%>
        <script type="text/javascript">
            $('document').ready(function(){
            	fnInit();
        	});
            
            var fnInit = function(){
            	fnSelectDealerList();
            }
            
            var fnSelectDealerList = function(){
            	var param = {
                        "url"          : "/cdMm/selectDealerList.ajax"
                      , "id"           : "dealerId"
                      , "default"      : "<c:out value="${data.dealerId}" />"
                      , "callback"     : comboCallback
                };
                
            	CmmnUtil.selectComboCdList(param);
            	
            }
            
            var comboCallback = function(param){
            	if(param.id == "dealerId"){
            		if(!StringUtil.isNull("<c:out value="${data.dealerId}" />")){
            			$("#dealerId").attr("disabled", true);
                	}
            	}            	
            }
            
            $(document).on("click", "#btnSave", function(e){
            	if($("#brandNm").val() == ""){
                	$("#brandNm").focus();
                	alert("Please enter brand name");
        			return;
        		}
            	
            	if($("#email").val() != "" && !ValidUtil.isValidEmail($("#email").val())){
                	$("#email").focus();
                	alert("Email Address format is not valid");
        			return;
        		}
            	
            	if($("#telNum1").val() != "" || $("#telNum2").val() != "" || $("#telNum3").val() != ""){
            		if($("#telNum1").val() == "" || $("#telNum2").val() == "" || $("#telNum3").val() == ""){
            			if($("#telNum1").val() == ""){
            				$("#telNum1").focus();	
            			}else if($("#telNum1").val() == ""){
            				$("#telNum2").focus();
            			}else{
            				$("#telNum3").focus();
            			}
                    	alert("Please check your phone number");
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
            	}
            	
        		var param = {
                        "url"          : "insertBrndInfo.ajax"	
                      , "data"         : {  "dealerId"      : $("#dealerId").val()
                    	                  , "brandNm"       : $("#brandNm").val()
                    	                  , "repNm"         : $("#repNm").val()
                    	                  , "email"         : $("#email").val()
                    	                  , "telNum"        : $("#telNum1").val()+"-"+$("#telNum2").val()+"-"+$("#telNum3").val()
                    	                  , "zipCd"         : $("#zipCd").val()
                    	                  , "addr1"         : $("#addr1").val()
                    	                  , "addr2"         : $("#addr2").val()
                                         }
                      , "succCallback" : function(data){
                    	                     alert("It has been saved.");
                    	                     popClose();
                    	                     fnSelectBrndList();
                    	                     
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
            <h1 class="ti mb0">Brand Registration</h1>
            <div class="cond-btns">
                <button type="button" class="btn btn-sz1 bgColor12 pop-close" id="btnCancle">Close</button>
                <button type="button" class="btn btn-sz1 bgColor1"            id="btnSave">Save</button>
            </div>
        </div>
        
        <div class="cardCont">
        	<div class="formbox pt0" id="divDealerId">
                <label for="dealerId" class="req">Dealer</label>
                <div class="form-inp">
                	<div class="form-sel flex-f1">
                		<select id="dealerId" class="select" >
                        </select>
                    </div>
                </div>
            </div>
            
            <div class="formbox">
                <label for="brandNm" class="req">Brand Name</label>
                <div class="form-inp"><input type="text" class="inp" id="brandNm" maxlength="30"></div>
            </div>
            
             <div class="formbox">
                <label for="repNm" >Representative Name</label>
                <div class="form-inp"><input type="text" class="inp" id="repNm" maxlength="30"></div>
            </div>
            
            <div class="formbox">
                <label for="email" >Email</label>
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
                <label for="">Phone Number</label>
                <div class="formbox3 flex-md">
                    <div class="formCol form-inp"><input type="text" class="inp" id="telNum1" maxlength="4"></div>
                    <div class="formCol form-inp"><input type="text" class="inp" id="telNum2" maxlength="4"></div>
                    <div class="formCol form-inp"><input type="text" class="inp" id="telNum3" maxlength="4"></div>
                </div>
            </div>
            
            <div class="formbox">
                <label for="">Address</label>
                <div class="form-inp"><input type="text" class="inp mb5" id="addr1"  maxlength="30" placeholder="State"></div>
                <div class="form-inp"><input type="text" class="inp mb5" id="addr2"  maxlength="30" placeholder="City"></div>
                <div class="form-inp"><input type="text" class="inp" id="zipCd"      maxlength="5"  placeholder="Zip Code"></div>
            </div>
        </div>
	</body>
</html>	
