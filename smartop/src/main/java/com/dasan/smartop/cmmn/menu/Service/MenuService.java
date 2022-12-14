package com.dasan.smartop.cmmn.menu.Service;

import java.util.List;
import java.util.Map;

public interface MenuService {
	//final static Logger logger = LogManager.getLogger(BrndMmService.class);
	
	public List<Map<String, Object>> selectMenuList(Map param) throws Exception;
	
}