package com.dasan.smartop.storInfo.Service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasan.smartop.cmmn.AbstractServiceImpl;
import com.dasan.smartop.cmmn.exception.BizException;
import com.dasan.smartop.dataAccess.util.SmartopMap;

@Service("StorInfoService")
public class StorInfoServiceImpl extends AbstractServiceImpl implements StorInfoService{
	private static final Logger logger = LoggerFactory.getLogger(StorInfoServiceImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<SmartopMap> selectStorList(Map param) throws Exception{
		logger.debug("selectStorList");
		List<SmartopMap> result = sqlSessionTemplate.selectList("com.dasan.smartop.mapper.StorInfoMapper.selectStorList", param);
		
		logger.debug("result : {}", result);
		return result;
	}
	
	@Override
	public SmartopMap selectStorInfo(Map param) throws Exception{
		return sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.StorInfoMapper.selectStorInfo", param);
	}
	
	@Override
	public int insertStorInfo(Map param)  throws Exception{
		return sqlSessionTemplate.insert("com.dasan.smartop.mapper.StorInfoMapper.insertStorInfo", param);
	}
	
	@Override
	public int updateStorInfo(Map param) throws Exception {
		return sqlSessionTemplate.update("com.dasan.smartop.mapper.StorInfoMapper.updateStorInfo", param);
	}
	
	@Override
	public int deleteStorList(Map param) throws Exception {
		int delCnt = 0;
		List<Map<String, Object>> delList = (List<Map<String, Object>>)param.get("delInfo");
		logger.debug("delList {}", delList);
		for(int nIdx=0; nIdx<delList.size(); nIdx++) {
			Map<String, Object> delInfo = delList.get(nIdx);
			int userCnt = sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.StorInfoMapper.selectStorUserCnt", delInfo);
			
			if(userCnt > 0) { // 브랜드 하위의 사용자 정보 확인
				throw new BizException("등록된 사용자가 존재하여 브랜드를 삭제할 수 없습니다.");
			}else {
				delCnt = sqlSessionTemplate.delete("com.dasan.smartop.mapper.StorInfoMapper.deleteStorInfo", delInfo);
				
			}
		}
		return delCnt;
	}
}