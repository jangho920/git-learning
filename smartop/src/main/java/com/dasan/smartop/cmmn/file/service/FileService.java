package com.dasan.smartop.cmmn.file.service;

import java.util.List;
import java.util.Map;

import com.dasan.smartop.dataAccess.util.SmartopMap;

public interface FileService {
	//final static Logger logger = LogManager.getLogger(BrndMmService.class);
	
	public Map uploadFileList(Map param) throws Exception;
	
	public Map deleteFileInfo(Map param) throws Exception;
	
	public List<SmartopMap> selectFileList(Map param) throws Exception;
}