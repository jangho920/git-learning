package com.dasan.smartop.cdMm.Service;

import java.util.List;
import java.util.Map;

import com.dasan.smartop.dataAccess.util.SmartopMap;

public interface CdService {
	public List<SmartopMap> selectCdList(Map param) throws Exception;
	
	public List<SmartopMap> selectDealerList(Map param) throws Exception;
	
	public List<SmartopMap> selectBrandList(Map param) throws Exception;
	
	public List<SmartopMap> selectStorList(Map param) throws Exception;
}