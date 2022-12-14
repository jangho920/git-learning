/**
 * ajax 호출을 위한 공통
 */

AjaxUtil = {
/****************************************************************
* ajax 호출
*
* @param    sParam.url             호출 url
* @param    sParam.type            get, post
* @param    sParam.async           true(비동기), false(동기)
* @param    sParam.timeout         timeout 시간
* @param    sParam.data            전달 데이타
* @param    sParam.beforeCallback  호출전 callback
* @param    sParam.succCallback    성공시 callback
* @param    sParam.errCallback     실패시 callback
* @param    sParam.complCallback   완료시 callback
***************************************************************/
    send : function(param){
		if(param != null){
			if(StringUtil.isNull(param.url)){
				alert("There is no URL");
			}
			
			if(StringUtil.isNull(param.type)){
				param.type = "POST";
			}
			
			if(StringUtil.isNull(param.async)){
				param.async = true;
			}
			
			if(StringUtil.isNull(param.timeout)){
				param.timeout = 3000;
			}
			
			if(!StringUtil.isNull(param.contentType)){
				if(param.contentType == "form"){
					param.contentType = "application/x-www-form-urlencoded";
				}
			}else{
				param.contentType = "application/json; charset=utf-8";
			}
		}else{
			
			alert("Parameter has no information");
		}
		
		$.ajax ({
		      // URL은 필수 요소이므로 반드시 구현해야 하는 Property입니다.
		      url	      : param.url          // 요청이 전송될 URL 주소
		    , type	      : param.type         // http 요청 방식 (default: ‘GET’)
		    , async       : param.async        // 요청 시 동기화 여부. 기본은 비동기(asynchronous) 요청 (default: true)
		    , cache       : true               // 캐시 여부
		    , timeout     : param.timeout      // 요청 제한 시간 안에 완료되지 않으면 요청을 취소하거나 error 콜백을 호출.(단위: ms)
		    , data        : JSON.stringify(param.data)         // 요청 시 포함되어질 데이터
		    , processData : true               // 데이터를 컨텐트 타입에 맞게 변환 여부
		    , contentType : "application/json; charset=utf-8" // 요청 컨텐트 타입 
		    , dataType    : "json"             // 응답 데이터 형식 (명시하지 않을 경우 자동으로 추측)
		    , beforeSend  : function () {
		        // XHR Header를 포함해서 HTTP Request를 하기전에 호출됩니다.
		        if(!StringUtil.isNull(param.beforeCallback)) param.beforeCallback;
		    }
		    , success : function(data, status, xhr) {
		        // 정상적으로 응답 받았을 경우에는 success 콜백이 호출되게 됩니다.
		        // 이 콜백 함수의 파라미터에서는 응답 바디, 응답 코드 그리고 XHR 헤더를 확인할 수 있습니다.
		        if(!StringUtil.isNull(param.succCallback)) param.succCallback(data);
		      
		    }
		    , error	: function(xhr, status, error) {
	            // 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에 
	            // error 콜백이 호출될 수 있습니다.
	            // 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만,
	            // 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
	            if(!StringUtil.isNull(param.errCallback)) param.errCallback;
		          
		    }
		    , complete : function(xhr, status) {
		        // success와 error 콜백이 호출된 후에 반드시 호출됩니다.
		        // try - catch - finally의 finally 구문과 동일합니다.
		      	if(!StringUtil.isNull(param.complCallback)) param.complCallback;
		    }
		});
	},
	sendFile : function(param){
		const formData = new FormData();
		
		if(param != null){
			
			if(StringUtil.isNull(param.type)){
				param.type = "POST";
			}
			
			if(StringUtil.isNull(param.async)){
				param.async = true;
			}
			
			if(StringUtil.isNull(param.timeout)){
				param.timeout = 3000;
			}
			
			if(StringUtil.isNull(param.fileId)){
				alert("There is no file object");
			    return;
			}
			
			const fileInput = $("#"+param.fileId)[0];
			// 파일을 여러개 선택할 수 있으므로 files 라는 객체에 담긴다.
			console.log("imageInput: ", fileInput.files)
			
			if(fileInput.files.length === 0){
			    alert("Please select your file");
			    return;
			}
			
			formData.append("image", fileInput.files[0]);

		}else{
			
			alert("Parameter has no information");
		}
		
		$.ajax ({
		      // URL은 필수 요소이므로 반드시 구현해야 하는 Property입니다.
		      url	      : "uploadFile.do"    // 요청이 전송될 URL 주소
		    , type	      : param.type         // http 요청 방식 (default: ‘GET’)
		    , async       : param.async        // 요청 시 동기화 여부. 기본은 비동기(asynchronous) 요청 (default: true)
		    , cache       : true               // 캐시 여부
		    , timeout     : param.timeout      // 요청 제한 시간 안에 완료되지 않으면 요청을 취소하거나 error 콜백을 호출.(단위: ms)
		    , enctype     : 'multipart/form-data'
		    , data        : formData           // 요청 시 포함되어질 데이터
		    , processData : false              // 데이터를 컨텐트 타입에 맞게 변환 여부
		    , contentType : false              // 요청 컨텐트 타입 
		    , beforeSend  : function () {
		        // XHR Header를 포함해서 HTTP Request를 하기전에 호출됩니다.
		        if(!StringUtil.isNull(param.beforeCallback)) param.beforeCallback;
		    }
		    , success : function(data, status, xhr) {
		        // 정상적으로 응답 받았을 경우에는 success 콜백이 호출되게 됩니다.
		        // 이 콜백 함수의 파라미터에서는 응답 바디, 응답 코드 그리고 XHR 헤더를 확인할 수 있습니다.
		        if(!StringUtil.isNull(param.succCallback)) param.succCallback(data);
		      
		    }
		    , error	: function(xhr, status, error) {
	            // 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에 
	            // error 콜백이 호출될 수 있습니다.
	            // 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만,
	            // 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
	            if(!StringUtil.isNull(param.errCallback)) param.errCallback;
		          
		    }
		    , complete : function(xhr, status) {
		        // success와 error 콜백이 호출된 후에 반드시 호출됩니다.
		        // try - catch - finally의 finally 구문과 동일합니다.
		      	if(!StringUtil.isNull(param.complCallback)) param.complCallback;
		    }
		});
	}
}