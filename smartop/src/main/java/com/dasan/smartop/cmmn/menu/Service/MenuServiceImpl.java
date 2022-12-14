package com.dasan.smartop.cmmn.menu.Service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasan.smartop.cmmn.AbstractServiceImpl;

@Service("MenuService")
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService{
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	/*@Autowired
	private SqlSessionFactory sqlSessionFactory;*/
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Map<String, Object>> selectMenuList(Map param) throws Exception{
		//return sqlSessionFactory.openSession().selectList("com.dasan.smartop.mapper.MenuMapper.selectMenuList", param);
		return sqlSessionTemplate.selectList("com.dasan.smartop.mapper.MenuMapper.selectMenuList", param);
	}

}