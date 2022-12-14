package com.dasan.smartop.storInfo.Controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dasan.smartop.storInfo.Service.StorInfoService;
import com.dasan.smartop.cmmn.session.SessionUtil;
import com.dasan.smartop.dataAccess.util.SmartopMap;



@Controller
public class StorInfoController {
	final static Logger logger = LogManager.getLogger(StorInfoController.class);
	
	@Autowired
	StorInfoService storInfoService;
	
	@RequestMapping("/storInfo/storMgmt.do")
	public ModelAndView storMgmtView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("storInfo/storMgmt");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/storInfo/selectStorList.ajax")
	@ResponseBody
	public List<SmartopMap> selectStorList(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return storInfoService.selectStorList(data);
		
	}
	
	@RequestMapping("/storInfo/storReg.do")
	public ModelAndView storRegView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("storInfo/storReg");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/storInfo/storInfo.do")
	public ModelAndView storInfoView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("storInfo/storInfo");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/storInfo/selectStorInfo.ajax")
	@ResponseBody
	public SmartopMap selectStorInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return storInfoService.selectStorInfo(data);
	}
	
	@RequestMapping("/storInfo/insertStorInfo.ajax")
	@ResponseBody
	public int insertStorInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return storInfoService.insertStorInfo(data);
	}
	
	@RequestMapping("/storInfo/updateStorInfo.ajax")
	@ResponseBody
	public int updateStorInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return storInfoService.updateStorInfo(data);
	}
	
	@RequestMapping("/storInfo/deleteStorList.ajax")
	@ResponseBody
	public int deleteStorList(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return storInfoService.deleteStorList(data);
	}
}