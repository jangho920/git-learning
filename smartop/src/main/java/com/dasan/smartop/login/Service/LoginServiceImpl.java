package com.dasan.smartop.login.Service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasan.smartop.cmmn.AbstractServiceImpl;
import com.dasan.smartop.cmmn.crypto.CryptoUtil;

@Service("LoginService")
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService{
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	//@Autowired
	//private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Map<String, Object> selectUserInfo(Map param) throws Exception{
		logger.debug("pass:"+CryptoUtil.sha256((String)param.get("pass")));
		param.put("pass", CryptoUtil.sha256((String)param.get("pass")));
		//return sqlSessionFactory.openSession().selectOne("com.dasan.smartop.mapper.LoginMapper.selectUserInfo", param);
		return sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.LoginMapper.selectUserInfo", param);
	}
	
	@Override
	public Map<String, Object> selectUserId(Map param) throws Exception{
		//return sqlSessionFactory.openSession().selectOne("com.dasan.smartop.mapper.LoginMapper.selectUserId", param);
		return sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.LoginMapper.selectUserId", param);
	}
	
	@Override
	public Map<String, Object> selectUserPass(Map param) throws Exception{
		//return sqlSessionFactory.openSession().selectOne("com.dasan.smartop.mapper.LoginMapper.selectUserPass", param);
		return sqlSessionTemplate.selectOne("com.dasan.smartop.mapper.LoginMapper.selectUserPass", param);
	}

}