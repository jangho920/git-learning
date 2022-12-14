package com.dasan.smartop.userInfo.Service;

import java.util.List;
import java.util.Map;

import com.dasan.smartop.dataAccess.util.SmartopMap;

public interface UserInfoService {
	public List<SmartopMap> selectUserInfoList(Map param) throws Exception;
	
	public SmartopMap selectUserInfo(Map param) throws Exception;
	
	public int saveUserInfo(Map param) throws Exception;
	
	public int deleteUserInfoList(Map param) throws Exception;
}