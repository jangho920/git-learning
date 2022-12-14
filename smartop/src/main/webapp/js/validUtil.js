/**
 * validation 체크 관련 공통 function 모음
 */

ValidUtil = {
	/****************************************************************
	* 입력된 email값이 유효한지 check하는 함수(format check)
	*
	* @param    sEmail       입력된 email 값
	* @return   boolean     true : 유효한 email, false : 유효하지 않은 email
	***************************************************************/
	isValidEmail : function(sEmail)
	{
	    var cnt=0;
	    var dot=0;
	    var firstdot = -1;
	    var lastdot;
	    var pos;
	    for(var i=0;i<sEmail.length;i++){
	        if(sEmail.charAt(i)=='@'){
	            cnt=cnt+1;
	        }
	        if(sEmail.charAt(i)=='.'){
	            dot=dot+1;
	            lastdot=i;
	            if(dot==1) firstdot=i;
	        }
	    }
	    if(cnt==0 || dot==0 || firstdot == 0 || lastdot+1==sEmail.length) return false;
	 
	    return true;
	},
	/****************************************************************
	* 해당 문자가 알파벳인지 check
	*
	* @param    c           입력된 문자
	* @return   boolean     true : 알파벳이다
	***************************************************************/
	isAlphabet : function(c) {  
	   return ( ((c >= "a") && (c <= "z")) || ((c >= "A") && (c <= "Z")) )
	},
	
	
	/****************************************************************
	* 해당 문자가 숫자인지 check
	*
	* @param    c           입력된 문자
	* @return   boolean     true : 숫자이다
	***************************************************************/
	isDigit : function(c) {  
	   return ((c >= "0") && (c <= "9"))
	},
	
	/****************************************************************
	* 해당 문자가 한글인지 check
	*
	* @param    c           입력된 문자
	* @return   boolean     true : 한글이다
	***************************************************************/
	isKorean : function(s) { 
	    
	    if (isEmpty(s)) return true;
	    
	    var pattern = /[^(가-힣ㄱ-ㅎ" "-" ")]/;
	    if(pattern.test(s)){
	        return false;
	    }
	    return true;
	},
	/****************************************************************
	* 입력된 문자열의 값이 숫자로만 구성되어 있는지
	*
	* @param    str         입력된 문자열
	* @param    enableEmpty 입력값이 없는경우도 포함시킬지 여부
	* @return   boolean     true : 입력값에 알파벳과 숫자가 모두 있다.
	***************************************************************/
	isNumber : function(s, enableEmpty) {
	    var i;
	
	    if (isEmpty(s))
	       if (enableEmpty == null) return _defaultEmptyOK;
	       else return (enableEmpty == true);
	
	   if ((s.charAt(0) == '.') || (s.charAt(s.length-1) == '.')) return false;
	
	    for (i = 0; i < s.length; i++)
	    {  
	        var c = s.charAt(i);
	
	        if (!isDigit(c) && (c != '.') && (c != ',')&& (c != '+') && (c != '-')) return false;
	    }
	
	    return true;
	},
	/****************************************************************
	* 입력된 문자열의 값에 알파벳과 숫자가 모두 있는지 check(비밀번호등에 사용)
	*
	* @param    str         입력된 문자열
	* @return   boolean     true : 입력값에 알파벳과 숫자가 모두 있다.
	***************************************************************/
	isAlphaAndDigit : function(str){
	     var isL = false;
	     var isD = false;
	     
	     for(i = 0; i < str.length; i ++){
	     if(isAlphabet(str.substring(i, i+1)))
	         isL = true;
	             if(isDigit(str.substring(i, i+1)))
	                 isD = true; 
	             
	             if(isL && isD)
	                 return true;
	     }
	     return false;
	},
	/****************************************************************
	* 입력된 기간이 올바른지 check
	*
	* @param    fromDate    시작일
	* @param    toDate      종료일
	* @return   boolean     true : 유효한 기간
	***************************************************************/
	isValidTerm : function(fromDate, toDate){
	
	 if(fromDate.length < 1 || toDate.length <1) return false;
	
	 var sDate = parseDate(fromDate);
	 var eDate = parseDate(toDate);
	
	 if(sDate < eDate) return true;
	 
	 return false;
	},
	/****************************************************************
	* 문자열이 지정한 길이보다 큰지 체크
	* 
	* @param str           문자열
	* @param len           지정한 길이
	* @return boolean       true : 지정한 길이보다 크다
	***************************************************************/
	isLength : function(str, len)
	{
	    var complen   = complen   = str.length;;
	    
	    if ( complen > len) return false;
	    
	    return true;
	},
	/****************************************************************
	* 문자열이 지정한 길이보다 큰지 체크(바이트)
	* 
	* @param str           문자열
	* @param len           지정한 길이
	* @return boolean       true : 지정한 길이보다 크다
	***************************************************************/
	ibLengthB(str, len)
	{
	    var byteLength= 0;
	    for(var inx=0; inx < str.length; inx++)
	    {
	        var oneChar = escape(str.charAt(inx));
	        if( oneChar.length == 1 )
	            byteLength ++;
	        else if(oneChar.indexOf("%u") != -1)
	            byteLength += 2;
	        else if(oneChar.indexOf("%") != -1)
	            byteLength += oneChar.length/3;
	    }
	    
	    if ( byteLength > len) return false;
	    
	    return true;
	}
}