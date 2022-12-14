package com.dasan.smartop.cmmn.file.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasan.smartop.cmmn.AbstractServiceImpl;
import com.dasan.smartop.dataAccess.util.SmartopMap;

@Service("FileService")
public class FileServiceImpl extends AbstractServiceImpl implements FileService{
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Map uploadFileList(Map param) throws Exception {
		// TODO Auto-generated method stub
		sqlSessionFactory.openSession().insert("com.dasan.smartop.mapper.FileMapper.insertFileInfo", param);
		return param;
	}
	
	@Override
	public Map deleteFileInfo(Map param) throws Exception {
		// TODO Auto-generated method stub
		sqlSessionFactory.openSession().insert("com.dasan.smartop.mapper.FileMapper.deleteFileInfo", param);
		
		return param;
	}

	@Override
	public List<SmartopMap> selectFileList(Map param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSessionFactory.openSession().selectList("com.dasan.smartop.mapper.FileMapper.selectFileList", param);
	}
	

}