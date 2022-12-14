package com.dasan.smartop.cdMm.Controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasan.smartop.cdMm.Service.CdService;
import com.dasan.smartop.dataAccess.util.SmartopMap;



@Controller
public class CdMmController {
	@Autowired
	CdService cdMmService;
	
	@RequestMapping("/cdMm/selectCdList.ajax")
	@ResponseBody
	public List<SmartopMap> selectCdList(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		
		return cdMmService.selectCdList(data);
	}
	
	@RequestMapping("/cdMm/selectDealerList.ajax")
	@ResponseBody
	public List<SmartopMap> selectDealerList(@RequestBody(required = false) HashMap data, HttpServletRequest request) throws Exception {
		
		return cdMmService.selectDealerList(data);
	}
	
	@RequestMapping("/cdMm/selectBrandList.ajax")
	@ResponseBody
	public List<SmartopMap> selectBrandList(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		
		return cdMmService.selectBrandList(data);
	}
	
	@RequestMapping("/cdMm/selectStorList.ajax")
	@ResponseBody
	public List<SmartopMap> selectStorList(@RequestBody HashMap data, HttpServletRequest request) throws Exception {
		
		return cdMmService.selectStorList(data);
	}
}