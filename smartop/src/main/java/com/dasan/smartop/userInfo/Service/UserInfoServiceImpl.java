package com.dasan.smartop.userInfo.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasan.smartop.cmmn.AbstractServiceImpl;
import com.dasan.smartop.cmmn.crypto.CryptoUtil;
import com.dasan.smartop.cmmn.exception.BizException;
import com.dasan.smartop.dataAccess.util.SmartopMap;


@Service("UserInfoService")
public class UserInfoServiceImpl extends AbstractServiceImpl implements UserInfoService{
	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	//@Autowired
	//private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<SmartopMap> selectUserInfoList(Map param) throws Exception{
		logger.debug("selectBrndList");
		//List<SmartopMap> result = sqlSessionFactory.openSession().selectList("com.dasan.smartop.mapper.UserInfoMapper.selectUserInfoList", param);
		List<SmartopMap> result = sqlSessionTemplate.selectList("com.dasan.smartop.mapper.UserInfoMapper.selectUserInfoList", param);
		
		logger.debug("result : {}", result);
		return result;
	}
	
	@Override
	public SmartopMap selectUserInfo(Map param) throws Exception{
		logger.debug("selectBrndList");
		//SmartopMap result = sqlSessionFactory.openSession().selectOne("com.dasan.smartop.mapper.UserInfoMapper.selectUserInfo", param);
		SmartopMap result = sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.UserInfoMapper.selectUserInfo", param);
		
		logger.debug("result : {}", result);
		return result;
	}
	
	@Override
	public int saveUserInfo(Map param) throws Exception{
		int resultCnt = 0;
		
		if("I".equals((String)param.get("mode"))) {
			// 직원번호 검증
			Map<String, Object> empNumMap = new HashMap<String, Object>();
			empNumMap.put("dealerId",  param.get("dealerId"));
			empNumMap.put("brandId",   param.get("brandId"));
			empNumMap.put("storId",    param.get("storId"));
			
			logger.debug("saveUserInfo.param {}", param);
			if("10".equals(param.get("userSecCd")) || "20".equals(param.get("userSecCd"))) {
				empNumMap.put("userSecCd", param.get("userSecCd"));
			}
			empNumMap.put("empNum",    param.get("empNum"));
			//int cnt = sqlSessionFactory.openSession().selectOne("com.dasan.smartop.mapper.UserInfoMapper.selectUserInfoCnt", empNumMap);
			int cnt = sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.UserInfoMapper.selectUserInfoCnt", empNumMap);
			
			if(cnt > 0) {
				throw new BizException("동일 가 직원번호가 존재합니다.");
			}
			
			Map<String, Object> userIdMap = new HashMap<String, Object>();
			userIdMap.put("userId", param.get("userId"));
			//cnt = sqlSessionFactory.openSession().selectOne("com.dasan.smartop.mapper.UserInfoMapper.selectUserInfoCnt", userIdMap);
			cnt = sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.UserInfoMapper.selectUserInfoCnt", userIdMap);
			
			if(cnt > 0) {
				throw new BizException("동일 사용자ID가 존재합니다.");
			}
			
			param.put("userPass", CryptoUtil.sha256((String)param.get("userPass")));
			//resultCnt = sqlSessionFactory.openSession().insert("com.dasan.smartop.mapper.UserInfoMapper.insertUserInfo", param);
			resultCnt = sqlSessionTemplate.insert("com.dasan.smartop.mapper.UserInfoMapper.insertUserInfo", param);
		}else {
			//resultCnt = sqlSessionFactory.openSession().insert("com.dasan.smartop.mapper.UserInfoMapper.updateUserInfo", param);
			resultCnt = sqlSessionTemplate.insert("com.dasan.smartop.mapper.UserInfoMapper.updateUserInfo", param);
		}
		return resultCnt;
	}
	
	@Override
	public int deleteUserInfoList(Map param) throws Exception{
		//int cnt = sqlSessionFactory.openSession().delete("com.dasan.smartop.mapper.UserInfoMapper.deleteUserInfoList", param.get("userInfo"));
		int cnt = 0;
		if("140".equals(param.get("menuParam")) || "240".equals(param.get("menuParam")) || "440".equals(param.get("menuParam"))) {
			cnt = sqlSessionTemplate.delete("com.dasan.smartop.mapper.UserInfoMapper.updateUserInfoList", param.get("userInfo"));
		}else {
			cnt = sqlSessionTemplate.delete("com.dasan.smartop.mapper.UserInfoMapper.deleteUserInfoList", param.get("userInfo"));
		}
		
		return cnt;
	}

}