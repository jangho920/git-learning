<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>     
<jsp:include page="../include/include.jsp" />  
<script type="text/javascript">
    $('document').ready(function(){
    	fnSelectUserList();
    	console.log('<c:out value="${data.menuParam}" />');
	});
    
    var fnSelectUserList = function(){
    	var param = {
                "url"          : "selectUserInfoList.ajax"	
              , "data"         : {"userSecCdList" : ["10"]}
              , "succCallback" : function(data){
             	 					var sTag = "";
             	 					for(var nIdx=0; nIdx<data.length; nIdx++){
             	 					    sTag += "<tr class='tx-c'>";
                                        sTag += "<td>";
                                        sTag += "    <span class=\"chkbox\">";
                                        sTag += "        <!-- input 아이디와 label for 값이 동일해야 됩니다. -->";
                                        sTag += "        <input type=\"checkbox\" id=\"chk"+nIdx+"\" name=\"chk\">";
                                        sTag += "        <label for=\"chk"+nIdx+"\"></label>";
                                        sTag += "        <input type=\"hidden\" name=\"userId\" value=\""+data[nIdx].userId+"\">";
                                        sTag += "    </span>";
                                        sTag += "</td>";
                                        sTag += "<td>"+(nIdx+1)+"</td>";
                                        sTag += "<td onclick=\"fnOpenPopup('"+data[nIdx].userId+"')\">"+data[nIdx].userId+"</td>";
                                        sTag += "<td>"+data[nIdx].userSecNm+"</td>";
                                        sTag += "<td>"+data[nIdx].userNm+"</td>";
                                        sTag += "<td>"+data[nIdx].regDt+"</td>";
                                        sTag += "<td>"+data[nIdx].modDt+"</td>";
                                        sTag += "<td>"+data[nIdx].modNm+"</td>";
                                        sTag += "</tr>";
             	 					}
             	 					
             	 					$('#grdUser > tbody').empty();
             	 					$("#grdUser > tbody").append(sTag);
             	 					$(".bbs-cnt").empty(); 
             	 					$(".bbs-cnt").append("<span>Total Count "+data.length+"</span>");
                                 } 
        };
        AjaxUtil.send(param);
    	
    }
    
    var fnOpenPopup = function(userId){
    	let param = {  "menuParam" : '<c:out value="${data.menuParam}" />'
    			     , "userId"    : userId
    			    };
    	$("#layerPopup").load(  '<c:url value="/userInfo/dasanUserInfo.do" />'
    			              , param
    			              , function(){
    			            	  layerPopup("layerPopup");
    	                        });
    }
    
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
    
    $(document).on("click", "#btnDel", function(e){
        var chk = $("input[name=chk]");
    	
    	if(chk.length == 0 ){
    		alert("There are no list of query");
    		return;
    	}
    	let chkCnt = 0;
    	let delData = new Array();
    	for(var nIdx=0; nIdx<chk.length; nIdx++){
    		if($("input[name=chk]")[nIdx].checked){
    			delData.push($("input[name=userId]")[nIdx].value);
    			chkCnt++;
    		}
    	}
    	
    	if(chkCnt == 0 ){
    		alert("Select your contents to delete");
    		return;
    	}
    	
    	var result = confirm("Are you sure you want to delete?");
    	if(result){
    		var param = {
                                "url"          : "deleteUserInfoList.ajax"
                              , "data"         : {"userInfo" : delData}
                              , "succCallback" : function(data){
                            	                     fnSelectUserList();
                                                 } 
                        };
            AjaxUtil.send(param);
    		
    	}
	});
    
    $(document).on("click", "#btnAdd", function(e){
    	let param = {"menuParam" : '<c:out value="${data.menuParam}" />'};
    	$("#layerPopup").load(  '<c:url value="/userInfo/dasanUserReg.do" />'
    			              , param
    			              , function(){
    			            	  layerPopup("layerPopup");
    	                        });
	});
 </script>     
 			<!-- contents in [E] -->
            <!-- ========== 탭 : 사용자 관리 [S] ========== -->
            <!-- 탭 선택시 display none, block -->
            <div class="tabBox cardCont mt25" >
                <div class="bbs-cond ccl4">
                    <h2 class="ti">Dasan User</h2>
                </div>

                <div class="cardCont-in">
                    <div class="bbs-cond flex-md">
                        <div class="bbs-cnt">총 4개</div>
                        <div class="cond-btns">
                        	<button type="button" class="btn btn-sz1 bgColor1" id="btnAdd">Add</button>
                            <button type="button" class="btn btn-sz1 bgColor3" id="btnDel">Remove</button>
                            <!-- <button type="button" class="btn btn-sz1 bgColor8">PDF 다운로드</button> -->
                        </div>
                    </div>

                    <!-- 게시판 -->
                    <div class="table-l1 tx-c  mt15">
                        <table id="grdUser">
                            <colgroup>
                                <col width="3%">
                                <col width="10%">
                                <col width="10%">
                                <col width="20%">
                                <col width="*">
                                <col width="15%">
                                <col width="15%">
                                <col width="10%">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>
                                        <span class="chkbox">
                                            <input type="checkbox" id="chkAll">
                                            <label for="chkAll"></label>
                                        </span>
                                    </th>
                                    <th>No.</th>
                                    <th>Employee Code</th>
                                    <th>Group Name</th>
                                    <th>User Name</th>
                                    <th>Registration Date</th>
                                    <th>Modified Date</th>
                                    <th>Modifier</th>
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
            <!-- ========== 탭 : 사용자 관리 [E] ========== -->
            <!-- contents in [E] -->
            <!-- 팝업창 띄울대 딤드참 뒷 배경 -->
			<div class="mask"></div>
			<div class="layerPopup" id="layerPopup" style="display: none;" />
			