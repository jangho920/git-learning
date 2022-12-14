/**
 * 문자를 관련 공통 function 모음
 */

StringUtil = {
	/****************************************************************
	* 입력한 string의 값 존재여부 확인
	* 사용 예)   isNull('  abc  ')  => true
	*
	* @param    sParam       변환 대상 string
	* @return   boolean      여부
	***************************************************************/
	isNull : function(sParam){
		if(sParam == undefined || sParam == ""){
			return true;
		}else{
			return false;
		}
		
	},
	/****************************************************************
	* 입력한 string의 값 존재여부 확인하여 defaultValue 리컨
	* 사용 예)   nvl('  abc  ', "")  => true
	*
	* @param    sParam         변환 대상 string
	* @return   boolean      여부
	***************************************************************/
	nvl : function(sParam, defaultValue){
		if(StringUtil.isNull(sParam)){
			return defaultValue;
		}else{
			return sParam;
		}
	},
	/****************************************************************
	* 입력한 string의 값 중 불필요한 space,tab등을 제거하는 함수
	* 사용 예)   trim('  abc  ')  => 'abc'
	*
	* @param    sParam         변환 대상 string
	* @return   string      trim된 문자열
	***************************************************************/
	trim : function(sParam) {
	    return sParam.replace(/^\s*/,'').replace(/\s*$/, '');
	},
	/****************************************************************
	* 입력한 string의 값 중 앞의의 불필요한 space,tab등을 제거하는 함수
	* 사용 예)   leftTrim('  abc  ')  => 'abc  '
	*
	* @param    sParam         변환 대상 string
	* @return   string      trim된 문자열
	***************************************************************/
	leftTrim : function(sParam) {
	    return sParam.replace(/^\s*/,'');
	},
	/****************************************************************
	* 입력한 string의 값 중 뒤의 불필요한 space,tab등을 제거하는 함수
	* 사용 예)   rightTrim('  abc  ')  => '  abc'
	*
	* @param    sParam         변환 대상 string
	* @return   string      trim된 문자열
	***************************************************************/
	rightTrim : function(sParam) {
	    return sParam.replace(/\s*$/, '');
	},
	/****************************************************************
	* 입력한 string의 값 중 앞부분에서 필요한 길이만큼 가져오는 함수
	* 사용 예)   leftStr('123456789', 3)  => '123'
	*
	* @param    sParam         변환 대상 string
	* @param    inLen       가져올 길이
	* @return   string      가져온 문자열
	***************************************************************/
	leftStr : function(sParam,inLen) {
	  return sParam.substring(0,inLen);
	},
	/****************************************************************
	* 입력한 string의 값 중 뒷부분에서 필요한 길이만큼 가져오는 함수
	* 사용 예)   rightStr('123456789', 3)  => '789'
	*
	* @param    sParam         변환 대상 string
	* @param    inLen       가져올 길이
	* @return   string      가져온 문자열
	***************************************************************/
	rightStr : function(sParam,inLen) {
	  return sParam.substring((sParam.length-inLen),sParam.length);
	},
	/****************************************************************
	* 입력한 string의 값 중 중간에서 필요한 길이만큼 가져오는 함수
	* 사용 예)   midStr('123456789', 3, 3)  => '456'
	*
	* @param    sParam         변환 대상 string
	* @param    inStart     가져오기 시작할 위치
	* @param    inLen       가져올 길이
	* @return   string      가져온 문자열
	***************************************************************/
	midStr : function(sParam,inStart,inLen) {
	  var iEnd;
	  if (!inLen)
	    iEnd = sParam.length;
	  else
	    iEnd = inStart + inLen;
	  return sParam.substring(inStart,iEnd);
	},
	/****************************************************************
	* 문자열을 해당 char로 padding하는 함수
	* 사용 예)   padding('3','0', 2, 'left')  => '03'
	*
	* @param    sParam      대상 문자열
	* @param    sPadChr     padding할 char
	* @param    nSize        변환된 후의 총 길이
	* @param    sFlag        'left'/'right'
	* @return   string      Padding된 문자열
	***************************************************************/
	padding : function(sParam, sPadChr, nSize, sFlag) {
	    var instr  = sParam + '';
		var strlen = instr.length;
		var chrlen = sPadChr.length;
		var count  = (nSize - strlen)/chrlen;
		var result = sParam;
		
		if (sPadChr == ' ') sPadChr = "&nbsp;";
		for(var i=0;  i<count; i+= chrlen)
		{
		    if(sFlag == 'left')        result = sPadChr + result;
		    else if (sFlag == 'right') result = result + sPadChr;
		}
		
		return result;
	},
	/****************************************************************
	* str에 comma 처리. string으로 변환함
	*
	* @param    sParam      대상 문자열
	* @return   string      comma 처리된 string
	***************************************************************/
	setComma : function(sParam) {
	    var sInput    = new String;
	    var sOut      = new String;
	    var nIdx   = 0;
	    var nDecPoint = 0;
	    var nEnd      = 0;
	    sInput        = delChar(sParam, ",");
	    sOut          = '';
	
	    nDecPoint = sInput.indexOf('.', 1);
	
	    if(nDecPoint == -1) {
	        nEnd = sInput.length;
	        for (nIdx = 1; nIdx <= sInput.length; nIdx++)
	        {
	            sOut = (nIdx % 3 == 0  && nIdx < nEnd ? ',' : '') +
	                           sInput.charAt(sInput.length - nIdx) +
	                           sOut;
	        }
	    } else {
	        nEnd = nDecPoint - (sInput.charAt(0) == '-' ? 1 : 0);
	        for (nIdx = 1; nIdx <= nDecPoint; nIdx++) {
	            sOut = (nIdx % 3 == 0 && nIdx < nEnd ? ',' : '') +
	                         sInput.charAt(nDecPoint - nIdx) + 
	                         sOut;
	        }
	        sOut += sInput.substring(nDecPoint);      
	    }
	    return sOut;
	},
	/****************************************************************
	* Argument로 받은 특정 문자를 제거하는 함수
	*
	* @param    sParam      대상 문자열
	* @param    sRmChar        제거하고자 하는 특정 문자
	* @return   string      변환된 string
	***************************************************************/
	delChar : function(sParam, sRmChar){
	    if (StringUtil.isNull(sParam)) return '';
	    
	    var temp = sParam.replaceAll(sRmChar, '');
	    return temp;
	},
	/****************************************************************
	* 입력된 값을 대문자로 변환함
	* 사용 예 ) toUpper("123abc한DEF.4") => 123ABC한DEF.4
	*
	* @param    sParam           입력된 값
	* @return   string      변경된 대문자 문자열
	***************************************************************/
	toUpper : function(sParam) {
	    var strNew = "";
	    var str    = sParam.toString();
	    for( i=0 ; i<str.length; i++ ) {
	        if( str.charAt(i) >= 'a' && str.charAt(i) <= 'z' )
	            strNew = strNew + str.charAt(i).toUpperCase();
	        else
	            strNew = strNew + str.charAt(i);
	    }
	    return strNew;
	},
	/****************************************************************
	* 입력된 값을 소문자로 변환함
	* 사용 예 ) toLower("123abc한DEF.4") => 123abc한def.4
	*
	* @param    sParam           입력된 값
	* @return   Number      수치값
	***************************************************************/
	toLower : function(sParam) {
	    var strNew = "";
	    var str    = sParam.toString();
	    for( i=0 ; i<str.length; i++ ) {
	        if( str.charAt(i) >= 'A' && str.charAt(i) <= 'Z' )
	            strNew = strNew + str.charAt(i).toLowerCase();
	        else
	            strNew = strNew + str.charAt(i);
	    }
	    return strNew;
	}
}