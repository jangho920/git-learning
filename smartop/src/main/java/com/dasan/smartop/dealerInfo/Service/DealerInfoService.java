package com.dasan.smartop.dealerInfo.Service;

import java.util.List;
import java.util.Map;

import com.dasan.smartop.dataAccess.util.SmartopMap;

public interface DealerInfoService {
	//final static Logger logger = LogManager.getLogger(DealerMmService.class);
	
	public List<SmartopMap> selectDealerList(Map param) throws Exception;
	
	public SmartopMap selectDealerInfo(Map param) throws Exception;
	
	public int insertDealerInfo(Map param) throws Exception;
	
	public int updateDealerInfo(Map param) throws Exception;
	
	public int deleteDealerList(Map param) throws Exception;
}