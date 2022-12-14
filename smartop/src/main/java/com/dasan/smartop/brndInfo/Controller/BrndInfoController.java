package com.dasan.smartop.brndInfo.Controller;

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

import com.dasan.smartop.brndInfo.Service.BrndInfoService;
import com.dasan.smartop.cmmn.session.SessionUtil;
import com.dasan.smartop.dataAccess.util.SmartopMap;



@Controller
public class BrndInfoController {
	final static Logger logger = LogManager.getLogger(BrndInfoController.class);
	
	@Autowired
	BrndInfoService brndInfoService;
	
	@RequestMapping("/brndInfo/brndMgmt.do")
	public ModelAndView brndMgmtView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("brndInfo/brndMgmt");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/brndInfo/selectBrndList.ajax")
	@ResponseBody
	public List<SmartopMap> selectBrndList(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return brndInfoService.selectBrndList(data);
		
	}
	
	@RequestMapping("/brndInfo/brndReg.do")
	public ModelAndView brndRegView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("brndInfo/brndReg");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/brndInfo/brndInfo.do")
	public ModelAndView brndInfoView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("brndInfo/brndInfo");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/brndInfo/selectBrndInfo.ajax")
	@ResponseBody
	public SmartopMap selectBrndInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return brndInfoService.selectBrndInfo(data);
	}
	
	@RequestMapping("/brndInfo/insertBrndInfo.ajax")
	@ResponseBody
	public int insertBrndInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return brndInfoService.insertBrndInfo(data);
	}
	
	@RequestMapping("/brndInfo/updateBrndInfo.ajax")
	@ResponseBody
	public int updateBrndInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return brndInfoService.updateBrndInfo(data);
	}
	
	@RequestMapping("/brndInfo/deleteBrndList.ajax")
	@ResponseBody
	public int deleteBrndList(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return brndInfoService.deleteBrndList(data);
	}
}