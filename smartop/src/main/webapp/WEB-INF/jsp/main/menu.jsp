<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- <script src="../assets/js/common.js" defer></script> -->
    <script type="text/javascript">
	    $(document).ready(function () { 
			var param = {
				"url"          : "menuList.ajax"	
	          , "succCallback" : function(data){
	        	                     console.log(data);
	        	                     var sTag = "<ul>";
	        	                     for(var idx=0; idx<data.length; idx++){
	        	                    	 if(data[idx].menuLvl == '2'){
	        	                    		 sTag += "<li class=\""+data[idx].menuLvl+"\"><a href=\"";
	        	                    		 if(!StringUtil.isNull(data[idx].scrId)){
	        	                    			 //sTag += "#\" onclick=\"mvPage('"+data[idx].scrPath+"/"+data[idx].scrFileNm+".do', '"+data[idx].menuParam+"');\"";
	        	                    			 sTag += data[idx].scrPath+"/"+data[idx].scrFileNm+".do?menuParam="+StringUtil.nvl(data[idx].menuParam, "")+"\"";
	        	                    		 }else{
	        	                    			 
	        	                    			 sTag += "#\"";
	        	                    		 }
	        	                    		 
	        	                    	     sTag +=" target=\"content\">"+data[idx].menuNm+"</a>";
	        	                    		 
	        	                    		 if(((idx == data.length-1) || data[idx].menuLvl == data[idx+1].menuLvl)){
	        	                    			 sTag += "</li>";
	        	                    		 }else if(data[idx].menuLvl < data[idx+1].menuLvl){
	        	                    			 
	        	                    			 sTag += "<ol class=\"subMenu\">";
	        	                    			 
	        	                    		 }
	        	                    	}else if(data[idx].menuLvl == '3'){
	        	                    		 sTag += "<li class=\""+data[idx].menuLvl+"\"><a href=\"";
											 if(!StringUtil.isNull(data[idx].scrId)){
	        	                    			 sTag += data[idx].scrPath+"/"+data[idx].scrFileNm+".do?menuParam="+StringUtil.nvl(data[idx].menuParam, "")+"\"";
	        	                    			 //sTag += "#\" onclick=\"mvPage('"+data[idx].scrPath+"/"+data[idx].scrFileNm+".do', '"+data[idx].menuParam+"');\"";
	        	                    		 }else{
	        	                    			 sTag += "#\"";
	        	                    		 }
											 sTag +=" target=\"content\">"+data[idx].menuNm+"</a>";
	        	                    		 
	        	                    		 if((idx == data.length-1) || (data[idx].menuLvl > data[idx+1].menuLvl)){
	        	                    			 sTag += "</ol></li>";
	        	                    			 
	        	                    		 }
	        	                    	 }
	        	                     }
	        	                     
	        	                     sTag += "</ul>";
	        	                     $("#lnb").prepend(sTag);
	                             } 
			};
			AjaxUtil.send(param);
	    });
	    
	</script>
<form method="post" id="menuForm" target="content">
	<input type="hidden" id="url">
	<input type="hidden" id="menuParam">
</form>
		<nav id="lnb" >

        </nav>