package com.dasan.smartop.userInfo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dasan.smartop.cdMm.Service.CdService;
import com.dasan.smartop.cmmn.session.SessionUtil;
import com.dasan.smartop.dataAccess.util.SmartopMap;
import com.dasan.smartop.userInfo.Service.UserInfoService;



@Controller
public class UserInfoController {
	final static Logger logger = LogManager.getLogger(UserInfoController.class);
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	CdService cdMmService;
	
	@RequestMapping("/userInfo/dasanUserMgmt.do")
	public ModelAndView dasanUserMgmtView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userInfo/dasanUserMgmt");
		mv.addObject("data", data);
		return mv;
	}
	
	@RequestMapping("/userInfo/dealerUserMgmt.do")
	public ModelAndView dealerUserMgmtView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userInfo/dealerUserMgmt");
		mv.addObject("data", data);
		return mv;
	}
	
	@RequestMapping("/userInfo/storUserMgmt.do" )
	public ModelAndView storUserMgmtView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userInfo/storUserMgmt");
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping(value={"/userInfo/dasanUserReg.do", "/userInfo/dealerUserReg.do", "/userInfo/storUserReg.do"}, method = RequestMethod.POST)
	public ModelAndView userRegView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);
		
		ModelAndView mv  = new ModelAndView();
		String userSecCd = "";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cmmnCd", "user_sec_cd");
		
		logger.debug("request.getServletPath() {}", request.getServletPath());
		if("/userInfo/dasanUserReg.do".equals(request.getServletPath())) {
			param.put("cmmnDtlCd", "10");
			userSecCd = "10";
		}else if("/userInfo/dealerUserReg.do".equals(request.getServletPath())) {
			param.put("cmmnDtlCd", "20");
			userSecCd = "20";
		}else if("/userInfo/storUserReg.do".equals(request.getServletPath())) {
			String cmmnDtlCdList[] = {"40","41","42"};
			param.put("cmmnDtlCdList", cmmnDtlCdList);
		}
		
		
		List<SmartopMap>cdList = (List<SmartopMap>)cdMmService.selectCdList(param);
		logger.debug("storUserRegView.cdList {}", cdList);
		mv.setViewName("userInfo/userReg");
		mv.addObject("userSecCdList", cdList);
		mv.addObject("userSecCd", userSecCd);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping(value={"/userInfo/dasanUserInfo.do", "/userInfo/dealerUserInfo.do", "/userInfo/storUserInfo.do"})
	public ModelAndView userInfoView(@RequestParam HashMap data, HttpServletRequest request) throws Exception{
		logger.debug("data {}", data);

		ModelAndView mv  = new ModelAndView();
		String userSecCd = "";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cmmnCd", "user_sec_cd");
		
		logger.debug("request.getServletPath() {}", request.getServletPath());
		if("/userInfo/dasanUserInfo.do".equals(request.getServletPath())) {
			param.put("cmmnDtlCd", "10");
			userSecCd = "10";
		}else if("/userInfo/dealerUserInfo.do".equals(request.getServletPath())) {
			param.put("cmmnDtlCd", "20");
			userSecCd = "20";
		}else if("/userInfo/storUserInfo.do".equals(request.getServletPath())) {
			String cmmnDtlCdList[] = {"40","41","42"};
			param.put("cmmnDtlCdList", cmmnDtlCdList);
		}
		
		
		List<SmartopMap>cdList = (List<SmartopMap>)cdMmService.selectCdList(param);
		logger.debug("storUserRegView.cdList {}", cdList);
		mv.setViewName("userInfo/userInfo");
		mv.addObject("userSecCdList", cdList);
		mv.addObject("userSecCd", userSecCd);
		mv.addObject("data", data);
		
		return mv;
	}
	
	@RequestMapping("/userInfo/selectUserInfoList.ajax")
	@ResponseBody
	public List<SmartopMap> selectUserInfoList(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		
		if(data == null) {
			data = new HashMap();
		}
		
		String dealerId = (String)SessionUtil.getSessionValue(request, "dealerId");
		data.put("dealerId", dealerId);
		String brandId = (String)SessionUtil.getSessionValue(request, "brandId");
		data.put("brandId", brandId);
		String storId = (String)SessionUtil.getSessionValue(request, "storId");
		data.put("storId", storId);
		
		return userInfoService.selectUserInfoList(data);
	}
	
	@RequestMapping("/userInfo/insertUserInfo.ajax")
	@ResponseBody
	public int insertUserInfo(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		
		data.put("mode", "I");
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return userInfoService.saveUserInfo(data);
	}
	
	@RequestMapping("/userInfo/updateUserInfo.ajax")
	@ResponseBody
	public int updateUserInfo(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		data.put("mode", "U");
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return userInfoService.saveUserInfo(data);
	}
	
	@RequestMapping("/userInfo/deleteUserInfoList.ajax")
	@ResponseBody
	public int deleteUserInfoList(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		String userId = (String)SessionUtil.getSessionValue(request, "userId");
		data.put("regId", userId);
		data.put("modId", userId);
		return userInfoService.deleteUserInfoList(data);
	}
	
	@RequestMapping("/userInfo/selectUserInfo.ajax")
	@ResponseBody
	public SmartopMap selectUserInfo(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		
		return userInfoService.selectUserInfo(data);
	}
}