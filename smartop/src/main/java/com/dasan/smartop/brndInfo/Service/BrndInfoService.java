package com.dasan.smartop.brndInfo.Service;

import java.util.List;
import java.util.Map;

import com.dasan.smartop.dataAccess.util.SmartopMap;

public interface BrndInfoService {
	//final static Logger logger = LogManager.getLogger(BrndMmService.class);
	
	public List<SmartopMap> selectBrndList(Map param) throws Exception;
	
	public SmartopMap selectBrndInfo(Map param) throws Exception;
	
	public int insertBrndInfo(Map param) throws Exception;
	
	public int updateBrndInfo(Map param) throws Exception;
	
	public int deleteBrndList(Map param) throws Exception;
}