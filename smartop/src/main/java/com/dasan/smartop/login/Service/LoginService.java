package com.dasan.smartop.login.Service;

import java.util.Map;

public interface LoginService {
	//final static Logger logger = LogManager.getLogger(BrndMmService.class);
	
	public Map<String, Object> selectUserInfo(Map param) throws Exception;
	
	public Map<String, Object> selectUserId(Map param) throws Exception;
	
	public Map<String, Object> selectUserPass(Map param) throws Exception;
}