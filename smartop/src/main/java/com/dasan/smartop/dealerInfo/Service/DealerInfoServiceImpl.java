package com.dasan.smartop.dealerInfo.Service;

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

@Service("DealerInfoService")
public class DealerInfoServiceImpl extends AbstractServiceImpl implements DealerInfoService{
	private static final Logger logger = LoggerFactory.getLogger(DealerInfoServiceImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<SmartopMap> selectDealerList(Map param) throws Exception{
		logger.debug("selectDealerList");
		List<SmartopMap> result = sqlSessionTemplate.selectList("com.dasan.smartop.mapper.DealerInfoMapper.selectDealerList", param);
		
		logger.debug("result : {}", result);
		return result;
	}
	
	@Override
	public SmartopMap selectDealerInfo(Map param) throws Exception{
		return sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.DealerInfoMapper.selectDealerInfo", param);
	}
	
	@Override
	public int insertDealerInfo(Map param)  throws Exception{
		return sqlSessionTemplate.insert("com.dasan.smartop.mapper.DealerInfoMapper.insertDealerInfo", param);
	}
	
	@Override
	public int updateDealerInfo(Map param) throws Exception {
		return sqlSessionTemplate.update("com.dasan.smartop.mapper.DealerInfoMapper.updateDealerInfo", param);
	}
	
	@Override
	public int deleteDealerList(Map param) throws Exception {
		int delCnt = 0;
		List<Map<String, Object>> delList = (List<Map<String, Object>>)param.get("delInfo");
		logger.debug("delList {}", delList);
		for(int nIdx=0; nIdx<delList.size(); nIdx++) {
			Map<String, Object> delInfo = delList.get(nIdx);
			int userCnt = sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.DealerInfoMapper.selectDealerUserCnt", delInfo);
			
			if(userCnt > 0) { // 브랜드 하위의 사용자 정보 확인
				throw new BizException("등록된 사용자가 존재하여 브랜드를 삭제할 수 없습니다.");
			}else {
				delCnt = sqlSessionTemplate.delete("com.dasan.smartop.mapper.DealerInfoMapper.deleteDealerInfo", delInfo);
				
			}
		}
		return delCnt;
	}
}