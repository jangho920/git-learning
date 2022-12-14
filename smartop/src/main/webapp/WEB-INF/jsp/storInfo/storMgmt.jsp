<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>     
<jsp:include page="../include/include.jsp" />  
<script type="text/javascript">
    $('document').ready(function(){
    	fnSelectStorList();
	});
    
    var fnSelectStorList = function(){
    	var sTxt = $("#sTxt").val();
    	var data = { "order"         : $("#order").val() };
    	if($("#sTxtGb").val() == "1"){
    		data.dealerNm = sTxt;
    	}else if($("#sTxtGb").val() == "2"){
    		data.brandNm = sTxt;
    	}else if($("#sTxtGb").val() == "3"){
    		data.storNm = sTxt;
    	}
    	var param = {
                "url"          : "selectStorList.ajax"
              , "data"         : data
    	
              , "succCallback" : function(data){
             	 					var sTag = "";
             	 					for(var nIdx=0; nIdx<data.length; nIdx++){
             	 						sTag += "<tr class='tx-c'>";
                                        sTag += "<td>";
                                        sTag += "    <span class=\"chkbox\">";
                                        sTag += "        <!-- input 아이디와 label for 값이 동일해야 됩니다. -->";
                                        sTag += "        <input type=\"checkbox\" id=\"chk"+nIdx+"\" name=\"chk\">";
                                        sTag += "        <label for=\"chk"+nIdx+"\"></label>";
                                        sTag += "        <input type=\"hidden\" name=\"dealerId\" value=\""+data[nIdx].dealerId+"\">";
                                        sTag += "        <input type=\"hidden\" name=\"brandId\" value=\""+data[nIdx].brandId+"\">";
                                        sTag += "        <input type=\"hidden\" name=\"storId\" value=\""+data[nIdx].storId+"\">";
                                        sTag += "    </span>";
                                        sTag += "</td>";
                                        sTag += "<td>"+(nIdx+1)+"</td>";
                                        sTag += "<td>"+data[nIdx].dealerNm+"</td>";
                                        sTag += "<td>"+data[nIdx].brandNm+"</td>";
                                        sTag += "<td onclick=\"fnOpenPopup('"+data[nIdx].dealerId+"', '"+data[nIdx].brandId+"', '"+data[nIdx].storId+"')\">"+data[nIdx].storNm+"</td>";
                                        sTag += "<td class=\"";
                                        if(data[nIdx].storStatCd == "10"){
                                        	sTag += "ccl1";
                                        }else if(data[nIdx].storStatCd == "20"){
                                        	sTag += "ccl2";
                                        }else{
                                        	sTag += "ccl3";
                                        }
                                        sTag += "\">"+data[nIdx].storStatNm+"</td>";
                                        sTag += "<td>"+data[nIdx].apprDevCnt+"</td>";
                                        sTag += "<td>"+data[nIdx].regDt+"</td>";
                                        sTag += "<td>"+data[nIdx].modDt+"</td>";
                                        sTag += "</tr>";
             	 					}
             	 					
             	 					$('#grdList > tbody').empty();
             	 					$("#grdList").append(sTag).trigger("create");
             	 					$(".bbs-cnt").empty(); 
             	 					$(".bbs-cnt").append("<span>Total Count: "+data.length+"</span>");
                                 } 
        };
        AjaxUtil.send(param);
    	
    }
    
    var fnOpenPopup = function(dealerId, brandId, storId){
    	let param = {  "menuParam" : '<c:out value="${data.menuParam}" />'
    			     , "dealerId"  : dealerId
    			     , "brandId"   : brandId
    			     , "storId"    : storId
    			    };
    	$("#layerPopup").load(  '<c:url value="/storInfo/storInfo.do" />'
    			              , param
    			              , function(){
    			            	  layerPopup("layerPopup");
    	                        });
    }
    
    var fnDeleteStorList = function(){
    	var chk = $("input[name=chk]");
    	
    	if(chk.length == 0 ){
    		alert("There are no list of query");
    		return;
    	}
    	let chkCnt = 0;
    	let delData = new Array();
    	for(var nIdx=0; nIdx<chk.length; nIdx++){
    		if($("input[name=chk]")[nIdx].checked){
    			delInfo = {  "dealerId" : $("input[name=dealerId]")[nIdx].value
    					   , "brandId"  : $("input[name=brandId]")[nIdx].value
    					   , "storId"   : $("input[name=storId]")[nIdx].value
    					  };
    			delData.push(delInfo);
    			chkCnt++;
    		}
    	}
    	
    	if(chkCnt == 0 ){
    		alert("Please select contents to delete");
    		return;
    	}
    	
    	var result = confirm("Are you sure you want to delete?");
    	if(result){
    		var param = {
                                "url"          : "deleteStorList.ajax"
                              , "data"         : { "delInfo" : delData }
                              , "succCallback" : function(data){
                            	                     fnSelectStorList();
                                                 } 
                        };
            AjaxUtil.send(param);
    		
    	}
    	
    }
    
    
    $(document).on("click", "#btnAdd", function(e){
    	let param = {"menuParam" : '<c:out value="${data.menuParam}" />'};
    	$("#layerPopup").load(  '<c:url value="/storInfo/storReg.do" />'
    			              , param
    			              , function(){
    			            	  layerPopup("layerPopup");
    	                        });
	});
    
    $(document).on("click", "#btnDel", function(e){
    	fnDeleteStorList();
	});
    
    $(document).on("click", "#chkAll", function(e){
		//만약 전체 선택 체크박스가 체크된상태일경우
		if($("#chkAll").prop("checked")) {
			//해당화면에 전체 checkbox들을 체크해준다
			$("input[type=checkbox]").prop("checked",true);
		// 전체선택 체크박스가 해제된 경우
		} else {
			//해당화면에 모든 checkbox들의 체크를해제시킨다.
			$("input[type=checkbox]").prop("checked",false);
		}
	});
    
    $(document).on("click", "#btnSearch", function(e){
    	fnSelectStorList();
	});
    
    $(document).on("click", "#btnInit", function(e){
    	$("#sTxt").val("");
	});
 </script>     
 			<!-- contents in [E] -->
            <!-- ========== 탭 : 사용자 관리 [S] ========== -->
            <!-- 탭 선택시 display none, block -->
            <div class="contents" >
                <!-- <h2 class="ti">사용자 관리</h2> -->
                <div class="cardCont">
                    <div class="bbs-cond ccl4">
                        <h2 class="ti">Store Management</h2>
                    </div>
                
                     <!-- 검색영역 -->
                    <div class="formbox">
                    	<label for="sTxtGb">Word Search</label>
                        <div class="flex">
                            <select id="sTxtGb" class="select">
                            	<option value="1">Dealer</option>
                                <option value="2">Brand</option>
                                <option value="3">Store</option>
                            </select>
                            <div class="inp-box flex-b-auto flex-g1 ml10 mr10"><input type="search" id="sTxt" placeholder="search"></div>
                        
                            <div class="btnBox">
                                <a href="#" class="btn btn-sz3 bgColor1" id="btnSearch">Search</a>
                                <a href="#" class="btn btn-sz3 bgColor11" id="btnInit">Reset</a>
                            </div>
                        </div>
                    </div>
                </div>
				<div class="cardCont mt25">
				    <div class="cardCont-in">
                        <div class="bbs-cond">
                            <div class="cond-sort">
                                <fieldset class="s-fm">
                                    <legend>Searching Area</legend>
                                    <select id="order" class="select2">
                                        <option value="1">Registration Date</option>
                                        <option value="2">Registration Date Reverse</option>
                                    </select>
                    
                                    <!-- <select id="sel002" class="select2">
                                        <option value="">20개씩 보기</option>
                                    </select> -->
                                </fieldset>
                    
                            </div>
                            <div class="cond-btns">
                            	<button type="button" class="btn btn-sz1 bgColor1" id="btnAdd">Add</button>
                                <button type="button" class="btn btn-sz1 bgColor3" id="btnDel">Remove</button>
                                <!-- <button type="button" class="btn btn-sz1 bgColor8">PDF 다운로드</button>
                                <button type="button" class="btn btn-sz1 bgColor8">타임카드</button> -->
                            </div>
                        </div>
                    
                        <div class="bbs-cnt mt15">총 4개</div>
                    
                        <!-- 게시판 -->
                        <div class="table-l1 tx-c  mt15">
                            <table id="grdList">
                                <colgroup>
                                    <col width="3%">
                                    <col width="5%">
                                    <col width="15%">
                                    <col width="15%">
                                    <col width="*">
                                    <col width="10%">
                                    <col width="7%">
                                    <col width="15%">
                                    <col width="15%">
                                    
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>
                                            <span class="chkbox">
                                                <input type="checkbox" id="ch-all">
                                                <label for="ch-all"></label>
                                            </span>
                                        </th>
                                        <th>No.</th>
                                        <th>Dealer</th>
                                        <th>Brand</th>
                                        <th>Store Name</th>
                                        <th>Store Status</th>
                                        <th>License</th>
                                        <th>Registration Date</th>
                                        <th>Modified Date</th>
                                    </tr>
                                </thead>
                                <tbody >
                                </tbody>
                            </table>
                        </div>
                    
                        <!-- paging [S] -->
                        <!-- <div class="paging">
                            <button type="button"><<</button>
                            <button type="button"><</button>
                    
                            <a href="#">1</a>
                            현재페이지 strong 태그 사용
                            <strong class="p-now">2</strong>
                            <a href="#">3</a>
                    
                            <button type="button">></button>
                            <button type="button">>></button>
                        </div> -->
                        <!-- paging [E] -->
                    </div>
				</div>
                
            </div>
            <!-- ========== 탭 : 사용자 관리 [E] ========== -->
            <!-- contents in [E] -->
            <!-- 팝업창 띄울대 딤드참 뒷 배경 -->
			<div class="mask"></div>
			<div class="layerPopup" id="layerPopup" style="display: none;" />
