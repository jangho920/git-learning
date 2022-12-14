/**
 * 공통 function 모음
 */

CmmnUtil = {
	/****************************************************************
	* 입력된 email값이 유효한지 check하는 함수(format check)
	*
	* @param    url       호출 Url
	* @param    data      파라메터데이타(JSON)
	* @param    id        Object ID
	* @param    default   default value
	* @param    allYn     Y:전체, N:선택, 없음
	***************************************************************/
	selectComboCdList : function(pParam)
	{
	    var param = {
                            "url"          : pParam.url
                          , "data"         : pParam.data
                          , "succCallback" : function(data){
                        	                     if(!StringUtil.isNull(pParam.id)){
	                                                 var sTag = "";
	                                                 
	                                                 for(var nIdx=0; nIdx<data.length; nIdx++){
												         sTag += "<option value='"+data[nIdx].cmmnDtlCd+"' ";
												         if(StringUtil.isNull(pParam.default) && pParam.default == data[nIdx].cmmnDtlCd){
													         sTag += "selected"
												         }
												         sTag += ">";
												         sTag += data[nIdx].cmmnDtlNm
												         sTag += "</option>";
												     }
												     $("#"+pParam.id).empty();
             	 									 $("#"+pParam.id).append(sTag);
             	 									 
             	 									 if(!StringUtil.isNull(pParam.callback)){
             	 									 	pParam.callback(pParam);
             	 									 }
												 }	
                                             } 
                    };
                    AjaxUtil.send(param);
	}

}