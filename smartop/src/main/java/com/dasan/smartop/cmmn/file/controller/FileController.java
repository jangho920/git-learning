package com.dasan.smartop.cmmn.file.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.dasan.smartop.cmmn.file.service.FileService;
import com.dasan.smartop.cmmn.session.SessionVO;
import com.dasan.smartop.dataAccess.util.SmartopMap;



@Controller
@PropertySource("${globals-properties}")
public class FileController {
	final static Logger logger = LogManager.getLogger(FileController.class);
	
	@Value("${file.path}")
    private String filePath;
	
	@Autowired
	FileService fileService;
	
	@RequestMapping("/uploadFile.do")
	//public List<SmartopMap> uploadFile(MultipartHttpServletRequest request) throws Exception {
	public List<SmartopMap> uploadFile(@RequestPart("image") List<MultipartFile> files, HttpServletRequest request) throws Exception {
		
		logger.debug("filePath {}", filePath);
		
		List<SmartopMap> resultList = new ArrayList<SmartopMap>(); 

		for(MultipartFile mFile : files) {
			
			String orgFileName = mFile.getOriginalFilename();
					
			String extension = orgFileName.substring(orgFileName.lastIndexOf("."), orgFileName.length());

			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + extension;

			SessionVO sessionVO = (SessionVO)request.getSession().getAttribute("sessionVO");
			
			logger.debug("sessionVO {}", sessionVO);
			if(!StringUtils.isEmpty(sessionVO.getDealerId())) {
				filePath += "/" + sessionVO.getDealerId();
			}
			
			if(!StringUtils.isEmpty(sessionVO.getBrandId())) {
				filePath += "/" + sessionVO.getBrandId();
			}
			
			if(!StringUtils.isEmpty(sessionVO.getStorId())) {
				filePath += "/" + sessionVO.getStorId();
			}
			
			File file = new File(filePath+"/"+fileName);
			
			FileUtils.forceMkdirParent(file);
			
			mFile.transferTo(file);
			
			Map param = new HashMap();
			param.put("filePath",  filePath);
			param.put("fileNm",    fileName);
			param.put("orgFileNm", orgFileName);
			param.put("regId",     sessionVO.getUserId());
			param.put("modId",     sessionVO.getUserId());
			
			param = fileService.uploadFileList(param);
			
			resultList = fileService.selectFileList(param);
		
		}

		return resultList;
	}
	
	@RequestMapping("/deleteFile.do")
	public Map deleteFileInfo(@RequestBody Map data, HttpServletRequest request) throws Exception {
		
		data = fileService.deleteFileInfo(data);

		return data;
	}
	
	@RequestMapping("/selectFileList.do")
	public List<SmartopMap> selectFileList(@RequestBody Map data, HttpServletRequest request) throws Exception {
		
		return fileService.selectFileList(data);
	}
	
}