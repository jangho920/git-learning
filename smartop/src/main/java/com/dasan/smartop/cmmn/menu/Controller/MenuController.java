package com.dasan.smartop.cmmn.menu.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasan.smartop.cmmn.menu.Service.MenuService;
import com.dasan.smartop.cmmn.session.SessionUtil;

@Controller
public class MenuController {
	final static Logger logger = LogManager.getLogger(MenuController.class);
	
	@Autowired
	MenuService menuService;
	
	@RequestMapping("/menu.do")
	public String loginView(Model model, HttpServletRequest request) throws Exception{
		logger.debug("login model{}", model);
		return "menu";
	}
	
	@RequestMapping(value="/menuList.ajax")
	@ResponseBody
	public List<Map<String, Object>> menuList(HttpServletRequest request) throws Exception{
		
		Map data = new HashMap<>();
		
		String userSecCd = (String)SessionUtil.getSessionValue(request, "userSecCd");
		data.put("userSecCd", userSecCd);
		
		
		//data.put("menu", menuService.selectMenuList(data));
		return menuService.selectMenuList(data);
	}
	
}