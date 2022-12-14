package com.dasan.smartop.storInfo.Service;

import java.util.List;
import java.util.Map;

import com.dasan.smartop.dataAccess.util.SmartopMap;

public interface StorInfoService {
	//final static Logger logger = LogManager.getLogger(StorMmService.class);
	
	public List<SmartopMap> selectStorList(Map param) throws Exception;
	
	public SmartopMap selectStorInfo(Map param) throws Exception;
	
	public int insertStorInfo(Map param) throws Exception;
	
	public int updateStorInfo(Map param) throws Exception;
	
	public int deleteStorList(Map param) throws Exception;
}