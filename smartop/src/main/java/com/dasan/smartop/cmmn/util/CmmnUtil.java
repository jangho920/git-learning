package com.dasan.smartop.cmmn.util;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CmmnUtil {
	final static Logger logger = LogManager.getLogger(CmmnUtil.class);
	
    /**
     * 파라메터 셋팅
     *
     * @param request
     * @param hashmap
     * @throws Exception
     */
    public static HashMap setParam(HttpServletRequest request, HashMap data) throws Exception {
    	if(data == null) {
    		data = new HashMap<>();
    	}
    	Enumeration e = request.getParameterNames();
		while(e.hasMoreElements()) {
			String name = (String) e.nextElement();
			data.put(name, request.getParameter(name));
			String[] value = request.getParameterValues(name);
			logger.debug("name:{}", name);
			logger.debug("value:{}", value);
		}
		
		return data;
    }

 
}