package com.dasan.smartop.dealerInfo.Controller;

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

import com.dasan.smartop.dealerInfo.Service.DealerInfoService;
import com.dasan.smartop.cmmn.session.SessionUtil;
import com.dasan.smartop.dataAccess.util.SmartopMap;



@Controller
public class DealerInfoController {
	final static Logger logger = LogManager.getLogger(DealerInfoController.class);
	
	@Autowired
	DealerInfoService dealerInfoService;
	
	@RequestMapping("/dealerInfo/dealerMgmt.do")
	public ModelAndView dealerMgmtView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dealerInfo/dealerMgmt");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/dealerInfo/selectDealerList.ajax")
	@ResponseBody
	public List<SmartopMap> selectDealerList(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return dealerInfoService.selectDealerList(data);
		
	}
	
	@RequestMapping("/dealerInfo/dealerReg.do")
	public ModelAndView dealerRegView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dealerInfo/dealerReg");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/dealerInfo/dealerInfo.do")
	public ModelAndView dealerInfoView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dealerInfo/dealerInfo");
		//mv.addAllObjects(data);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/dealerInfo/selectDealerInfo.ajax")
	@ResponseBody
	public SmartopMap selectDealerInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return dealerInfoService.selectDealerInfo(data);
	}
	
	@RequestMapping("/dealerInfo/insertDealerInfo.ajax")
	@ResponseBody
	public int insertDealerInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return dealerInfoService.insertDealerInfo(data);
	}
	
	@RequestMapping("/dealerInfo/updateDealerInfo.ajax")
	@ResponseBody
	public int updateDealerInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return dealerInfoService.updateDealerInfo(data);
	}
	
	@RequestMapping("/dealerInfo/deleteDealerList.ajax")
	@ResponseBody
	public int deleteDealerList(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		return dealerInfoService.deleteDealerList(data);
	}
}