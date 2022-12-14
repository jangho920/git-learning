package com.dasan.smartop.login.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dasan.smartop.cmmn.session.SessionVO;
import com.dasan.smartop.login.Service.LoginService;

@Controller
public class LoginController {
	final static Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/login.do")
	public ModelAndView loginView(@RequestParam HashMap model, HttpServletRequest request) throws Exception{
		logger.debug("login model{}", model);
		HttpSession session = request.getSession(true);
		ModelAndView mv = new ModelAndView();
		if(session != null && session.getAttribute("sessionVO") != null) {
			mv.setViewName("main/main");
		}else {
			mv.setViewName("login");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/login.ajax")
	@ResponseBody
	public Map login(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		Map userInfo= loginService.selectUserInfo(data);
		
		HttpSession session = request.getSession(true);
		SessionVO sessionVO = new SessionVO();
		if(userInfo != null) {
			sessionVO.setUserId((String)userInfo.get("userId"));
			sessionVO.setUserNm((String)userInfo.get("userNm"));
			sessionVO.setTelNum((String)userInfo.get("telNum"));
			sessionVO.setEmail((String)userInfo.get("email"));
			sessionVO.setZipCd((String)userInfo.get("zipCd"));
			sessionVO.setAddr1((String)userInfo.get("addr1"));
			sessionVO.setAddr2((String)userInfo.get("addr2"));
			sessionVO.setDealerId((String)userInfo.get("dealerId"));
			sessionVO.setDealerNm((String)userInfo.get("dealerNm"));
			sessionVO.setBrandId((String)userInfo.get("brandId"));
			sessionVO.setBrandNm((String)userInfo.get("brandNm"));
			sessionVO.setStorId((String)userInfo.get("storId"));
			sessionVO.setStorNm((String)userInfo.get("storNm"));
			sessionVO.setUserSecCd((String)userInfo.get("userSecCd"));
			sessionVO.setUserSecNm((String)userInfo.get("userSecNm"));
		}
		session.setAttribute("sessionVO",   sessionVO);	
		
		return userInfo;
	}
	
	@RequestMapping(value="/logout.ajax")
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		session.removeAttribute("sessionVO");
		session.invalidate();
		return "login";
	}
	
	/*@RequestMapping("/findUserId.do")
	public String findUserIdView(Model model) throws Exception{
		logger.debug("login model{}", model);
		return "findUserId";
	}
	
	@RequestMapping(value="/selectId.ajax")
	@ResponseBody
	public Map selectId(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		
		if(StringUtils.isEmpty((CharSequence) data.get("userId")) ) {
			throw new BizException();
			
		}
		
		if(StringUtils.isEmpty((CharSequence) data.get("userNm")) ) {
			throw new BizException();
			
		}
		Map userInfo= loginService.selectUserInfo(data);
		
		return userInfo;
	}
	
	
	@RequestMapping("/findUserPass.do")
	public String userIdView(Model model) throws Exception{
		logger.debug("login model{}", model);
		return "findUserPass";
	}
	
	@RequestMapping(value="/selectPass.ajax")
	@ResponseBody
	public Map selectPass(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		
		Map userInfo= loginService.selectUserInfo(data);
		
		return userInfo;
	}*/
}