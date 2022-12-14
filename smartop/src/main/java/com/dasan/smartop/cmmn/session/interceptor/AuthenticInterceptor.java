package com.dasan.smartop.cmmn.session.interceptor;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dasan.smartop.cmmn.session.SessionVO;
import com.dasan.smartop.cmmn.session.util.UserDetailsHelper;

/**
 * 인증여부 체크 인터셉터
 */

public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	final static Logger logger = LogManager.getLogger(AuthenticInterceptor.class);
	
	private Set<String> permittedURL;
	
	public void setPermittedURL(Set<String> permittedURL) {
		logger.debug("setPermittedURL");
		
		this.permittedURL = permittedURL;
	}
	
	/**
	 * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다.
	 * 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {	
		
		String requestURI = request.getRequestURI(); //요청 URI
		boolean isPermittedURL = false; 
		
		SessionVO loginVO = (SessionVO) UserDetailsHelper.getAuthenticatedUser();
		
		logger.debug("preHandle {}", loginVO);
		if(loginVO != null){
			return true;
		}else{
			
			
			for(Iterator<String> it = this.permittedURL.iterator(); it.hasNext();){
				String urlPattern = request.getContextPath() + (String) it.next();
				
				logger.debug("urlPattern {}", urlPattern);

				if(Pattern.matches(urlPattern, requestURI)){// 정규표현식을 이용해서 요청 URI가 허용된 URL에 맞는지 점검함.
					isPermittedURL = true;
				}
				
				
			}
			
			if(!isPermittedURL){
				ModelAndView modelAndView = new ModelAndView("redirect:/login.do");			
				throw new ModelAndViewDefiningException(modelAndView);
			}else{
				return true;
			}
		}
	}

}