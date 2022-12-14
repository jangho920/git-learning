package com.dasan.smartop.cmmn.session;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dasan.smartop.cmmn.exception.BizException;
import com.dasan.smartop.login.Controller.LoginController;

/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 */

public class SessionUtil {
	final static Logger logger = LogManager.getLogger(SessionUtil.class);
	
	public static Object getSessionValue(HttpServletRequest request, String name) throws Exception{
		Object returnValue= "";
		
		if(request == null) {
			throw new BizException("세션이 존재하지 않습니다.");
		}
		
		HttpSession session = request.getSession(true);
		
		if(session == null) {
			throw new BizException("세션이 존재하지 않습니다.");
		}
		
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		if(sessionVO == null) {
			throw new BizException("세션이 존재하지 않습니다.");
		}
		
		if(name == null || "".equals(name)) {
			throw new BizException("세션명이 없습니다.");
		}
		
		try{
	        Object obj=sessionVO;
	        
	        logger.debug("obj"+obj);
	        logger.debug("obj.getClass()"+obj.getClass());
	        logger.debug("obj.getClass().getDeclaredFields()"+obj.getClass().getDeclaredFields());
	        for (Field field : obj.getClass().getDeclaredFields()){
	        	logger.debug("field.getName()"+name+"||"+field.getName());
	            field.setAccessible(true);
	            
	            if(name.equals(field.getName())) {
	            	returnValue=field.get(obj);
	            }
	        }
	    }catch (Exception e){
	    	logger.debug("SessionUtil.getSessionValue {}", e);
	    }
		
		return returnValue;
	}


}