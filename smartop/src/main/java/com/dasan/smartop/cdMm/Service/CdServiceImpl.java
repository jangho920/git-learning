package com.dasan.smartop.cdMm.Service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasan.smartop.cmmn.AbstractServiceImpl;
import com.dasan.smartop.dataAccess.util.SmartopMap;

@Service("CdService")
public class CdServiceImpl extends AbstractServiceImpl implements CdService{
	private static final Logger logger = LoggerFactory.getLogger(CdServiceImpl.class);
	
	//@Autowired
	//private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<SmartopMap> selectCdList(Map param) throws Exception{
		logger.debug("selectBrndList");
		//List<SmartopMap> result = sqlSessionFactory.openSession().selectList("com.dasan.smartop.mapper.CdMapper.selectCdList", param);
		List<SmartopMap> result = sqlSessionTemplate.selectList("com.dasan.smartop.mapper.CdMapper.selectCdList", param);
		
		logger.debug("result : {}", result);
		return result;
	}
	
	@Override
	public List<SmartopMap> selectDealerList(Map param) throws Exception{
		logger.debug("selectBrndList");
		//List<SmartopMap> result = sqlSessionFactory.openSession().selectList("com.dasan.smartop.mapper.CdMapper.selectCdList", param);
		List<SmartopMap> result = sqlSessionTemplate.selectList("com.dasan.smartop.mapper.CdMapper.selectDealerList", param);
		
		logger.debug("result : {}", result);
		return result;
	}
	
	@Override
	public List<SmartopMap> selectBrandList(Map param) throws Exception{
		logger.debug("selectBrndList");
		//List<SmartopMap> result = sqlSessionFactory.openSession().selectList("com.dasan.smartop.mapper.CdMapper.selectCdList", param);
		List<SmartopMap> result = sqlSessionTemplate.selectList("com.dasan.smartop.mapper.CdMapper.selectBrandList", param);
		
		logger.debug("result : {}", result);
		return result;
	}
	
	@Override
	public List<SmartopMap> selectStorList(Map param) throws Exception{
		logger.debug("selectBrndList");
		//List<SmartopMap> result = sqlSessionFactory.openSession().selectList("com.dasan.smartop.mapper.CdMapper.selectCdList", param);
		List<SmartopMap> result = sqlSessionTemplate.selectList("com.dasan.smartop.mapper.CdMapper.selectStorList", param);
		
		logger.debug("result : {}", result);
		return result;
	}
}